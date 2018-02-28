package cn.likole.jwxt;

import cn.likole.jwxt.entity.Exam;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import us.codecraft.xsoup.Xsoup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by likole on 12/21/17.
 */
public class Jwxt {

    /**
     * cookie manager
     */
    private CookieJar cookieJar = new CookieJar() {
        private final Map<String, List<Cookie>> cookiesMap = new HashMap<String, List<Cookie>>();

        public void saveFromResponse(HttpUrl arg0, List<Cookie> arg1) {
            String host = arg0.host();
            List<Cookie> cookiesList = cookiesMap.get(host);
            if (cookiesList != null) {
                cookiesMap.remove(host);
            }
            cookiesMap.put(host, arg1);
        }

        public List<Cookie> loadForRequest(HttpUrl arg0) {
            List<Cookie> cookiesList = cookiesMap.get(arg0.host());
            return cookiesList != null ? cookiesList : new ArrayList<Cookie>();
        }
    };

    /**
     * 基础地址
     */
    private String serverAddress = "http://jwxt.imu.edu.cn";
    private OkHttpClient okHttpClient = new OkHttpClient.Builder().cookieJar(cookieJar).build();
    /**
     * 课程方案号
     */
    private String idFA;
    /**
     * 学号
     */
    private String sid;
    /**
     * 选课结果地址
     */
    private String rediskey;
    /**
     * 选课数量
     */
    private String kcNum;

    /**
     * 更改教务系统基础地址
     *
     * @param url 地址,例如 "http://jwxt.imu.edu.cn"
     */
    public void setBaseUrl(String url) {
        serverAddress = url;
        System.out.println("教务系统基础地址已更改为" + url);
    }

    /**
     * 将验证码下载到本地
     *
     * @return 是否下载成功
     * @throws IOException
     */
    public boolean getCaptcha() throws IOException {
        return getCaptcha("captcha.jpg");
    }

    /**
     * 将验证码下载到本地
     *
     * @param filename 文件名
     * @return 是否下载成功
     * @throws IOException
     */
    public boolean getCaptcha(String filename) throws IOException {

        //send request
        Request request = new Request.Builder().url(serverAddress + "/img/captcha.jpg").get().build();
        Response response = okHttpClient.newCall(request).execute();

        //error
        if (!response.isSuccessful()) return false;

        //save captcha
        InputStream inputStream = response.body().byteStream();
        FileOutputStream fileOutputStream = new FileOutputStream(new File(filename));
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(bytes)) != -1) fileOutputStream.write(bytes, 0, len);
        fileOutputStream.close();
        inputStream.close();
        System.out.println("验证码下载成功");
        System.out.print("请输入验证码:");
        return true;
    }

    /**
     * 下载验证码
     *
     * @return 二进制流
     * @throws IOException
     */
    public InputStream getCaptchaStream() throws IOException {

        //send request
        Request request = new Request.Builder().url(serverAddress + "/img/captcha.jpg").get().build();
        Response response = okHttpClient.newCall(request).execute();
        System.out.println("验证码获取成功");
        return response.body().byteStream();
    }

    public boolean autoLogin(String url, String username, String password) throws IOException {

        //get captcha
        getCaptcha();
        File file = new File("captcha.jpg");

        //recognize captcha
        RequestBody formBody1 = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file))
                .build();
        Request request1 = new Request.Builder()
                .url(url + "/predict")
                .post(formBody1)
                .build();
        Response response1 = okHttpClient.newCall(request1).execute();

        String captcha = response1.body().string();
        System.out.println(captcha);

        return login(username, password, captcha);
    }


    /**
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     * @param captcha  验证码
     * @return 是否登陆成功
     * @throws IOException
     */
    public boolean login(String username, String password, String captcha) throws IOException {
        return login(username, password, captcha, true);
    }

    /**
     * 登陆
     *
     * @param username 用户名
     * @param password 密码
     * @param captcha  验证码
     * @param strict   严格模式,可确保选课成功
     * @return 是否登陆成功
     * @throws IOException
     */
    public boolean login(String username, String password, String captcha, boolean strict) throws IOException {

        //send request
        RequestBody formBody = new FormBody.Builder()
                .add("j_username", username)
                .add("j_password", password)
                .add("j_captcha", captcha)
                .build();
        Request request = new Request.Builder()
                .url(serverAddress + "/j_spring_security_check")
                .post(formBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()) return false;

        //check
        if (response.body().string().contains("学业信息")) {
            sid = username;
            //反反爬虫
            if (!preToken() && strict) {
                System.out.println("登陆成功,但反反爬虫失败,请重新登陆~");
                return false;
            }
            //获取方案号
            idFA = getScheme();
            if (idFA == null && strict) {
                System.out.println("登陆成功,但主修方案号获取失败,请重新登陆~");
                return false;
            } else if (idFA != null) {
                System.out.println("主修方案号:" + idFA);
            }
            System.out.println("登录成功");
            return true;
        }
        return false;
    }

    /**
     * 解决获取选课Token时的反爬虫
     *
     * @throws IOException
     */
    private boolean preToken() throws IOException {
        //send request
        Request request = new Request.Builder()
                .url(serverAddress + "/main/checkSelectCourseStatus")
                .get()
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response.isSuccessful();
    }

    /**
     * 获取选课Token
     *
     * @return
     * @throws IOException
     */
    private String getToken() throws IOException {
        //send request
        Request request = new Request.Builder()
                .url(serverAddress + "/student/courseSelect/planCourse/index?fajhh=" + idFA)
                .get()
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()) return null;
        Document document = Jsoup.parse(response.body().string());
        String token = Xsoup.select(document, "//input[@name='tokenValue']/@value").get();
        System.out.println("选课token:" + token);
        return token;
    }


    /**
     * 获取主修方案号
     *
     * @return 主修方案号
     * @throws IOException
     */
    private String getScheme() throws IOException {
        Request request = new Request.Builder()
                .url(serverAddress + "/student/rollManagement/rollInfo/index")
                .get()
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()) return null;
        Document document = Jsoup.parse(response.body().string());
        return Xsoup.select(document, "//input[@id='zx']/@value").get();
    }

    /**
     * 选课(批量以逗号隔开)
     *
     * @param id   课程编号 组成:课程号_课序号_方案计划号
     * @param name 课程名  组成:课程名_课序号
     * @return 是否成功, 仅表示请求是否成功, 不代表选课结果
     * @throws IOException
     */
    public boolean selectLesson(String id, String name) throws IOException {

        String token = getToken();
        if (token == null) return false;

        //send request
        RequestBody formBody = new FormBody.Builder()
                .add("fajhh", idFA)
                .add("sj", "0_0")
                .add("tokenValue", token)
                .add("kcIds", id)
                .add("kcms", name)
                .build();
        Request request = new Request.Builder()
                .url(serverAddress + "/student/courseSelect/planCourse/waitingfor?dealType=2")
                .post(formBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()) return false;
        String html = response.body().string();
        if (!html.contains("redisKey")) return false;
        kcNum = html.substring(html.indexOf("kcNum") + 9, html.indexOf("kcNum") + 10);
        rediskey = html.substring(html.indexOf("redisKey") + 12, html.indexOf("redisKey") + 23);

        return true;
    }

    /**
     * 选课
     *
     * @param courseId 课程号
     * @param classId  课序号
     * @param termCode 方案计划号,例如秋季学期"2017-2018-1-2",春季学期"2017-2018-2-2",夏季学期"2017-2018-3-2"
     * @return 是否成功, 仅表示请求是否成功, 不代表选课结果
     * @throws IOException
     */
    public boolean selectLesson(String courseId, String classId, String termCode) throws IOException {
        return selectLesson(courseId + "_" + classId + "_" + termCode, "_" + classId);
    }

    /**
     * 最近一次调用的选课函数的选课结果(仅适用于选课数小于10)
     * 调用函数前最好延时一段时间,或者使用循环
     *
     * @return
     * @throws IOException
     */
    public String selectResult() throws IOException {
        if (rediskey == null) return "selectLesson()尚未成功,无法查询~";
        //send request
        RequestBody formBody = new FormBody.Builder()
                .add("kcNum", kcNum)
                .add("redisKey", rediskey)
                .build();
        Request request = new Request.Builder()
                .url(serverAddress + "/student/courseSelect/selectResult/query")
                .post(formBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 获取方案成绩
     *
     * @return 教务系统原始json数据
     * @throws IOException
     */
    public String schemeScores() throws IOException {
        Request request = new Request.Builder()
                .url(serverAddress + "/student/integratedQuery/scoreQuery/schemeScores/callback")
                .get()
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()) return null;
        return response.body().string();
    }

    /**
     * 获取不及格成绩
     *
     * @return 教务系统原始json数据
     * @throws IOException
     */
    public String unpassedScores() throws IOException {
        Request request = new Request.Builder()
                .url(serverAddress + "/student/integratedQuery/scoreQuery/unpassedScores/callback")
                .get()
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()) return null;
        return response.body().string();
    }

    /**
     * 获取及格成绩
     *
     * @return 教务系统原始json数据
     * @throws IOException
     */
    public String passedScores() throws IOException {
        Request request = new Request.Builder()
                .url(serverAddress + "/student/integratedQuery/scoreQuery/allPassingScores/callback")
                .get()
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()) return null;
        return response.body().string();
    }

    /**
     * 获取指定学期课表
     *
     * @param planCode 方案计划号,例如秋季学期"2017-2018-1-2",春季学期"2017-2018-2-2",夏季学期"2017-2018-3-2"
     * @return 教务系统原始json数据
     * @throws IOException
     */
    public String schedule(String planCode) throws IOException {
        RequestBody formBody = new FormBody.Builder()
                .add("planCode", planCode)
                .build();
        Request request = new Request.Builder()
                .url(serverAddress + "/student/courseSelect/thisSemesterCurriculum/ajaxStudentSchedule/callback")
                .post(formBody)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }

    /**
     * 获取本学期课表
     *
     * @return 教务系统原始json数据
     * @throws IOException
     */
    public String schedule() throws IOException {
        Request request = new Request.Builder()
                .url(serverAddress + "/student/courseSelect/thisSemesterCurriculum/ajaxStudentSchedule/callback")
                .get()
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()) return null;
        return response.body().string();
    }

    /**
     * 获取考试安排
     *
     * @return
     * @throws IOException
     */
    public List<Exam> examPlan() throws IOException {
        Request request = new Request.Builder()
                .url(serverAddress + "/student/examinationManagement/examPlan/index")
                .get()
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()) return null;

        Document document = Jsoup.parse(response.body().string());
        List<String> times = Xsoup.select(document, "//div[@class='timeline-item clearfix']//font/text()").list();
        List<String> names = Xsoup.select(document, "//div[@class='timeline-item clearfix']//h5/text()").list();
        List<String> infos = Xsoup.select(document, "//div[@class='timeline-item clearfix']//div[@class='widget-main']/text()").list();

        List<Exam> result = new ArrayList<Exam>();
        for (int i = 0; i < times.size(); i++) {
            Exam exam = new Exam();
            exam.setTime(times.get(i));
            exam.setName(names.get(i).trim());
            exam.setInfo(infos.get(i));
            result.add(exam);
        }

        return result;
    }

    /**
     * 获取学籍信息
     *
     * @return
     * @throws IOException
     */
    public List<String> getInfo() throws IOException {
        Request request = new Request.Builder()
                .url(serverAddress + "/student/rollManagement/rollInfo/index")
                .get()
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()) return null;
        Document document = Jsoup.parse(response.body().string());
        List<String> names = Xsoup.select(document, "//div[@class='col-xs-8']//div[@class='profile-info-name']/text()").list();
        List<String> valuse = Xsoup.select(document, "//div[@class='col-xs-8']//div[@class='profile-info-value']/text()").list();
        List<String> infos = new ArrayList<String>();
        for (int i = 0; i < names.size(); i++) {
            String key = names.get(i).trim();
            if (!"".equals(key))
                infos.add(key + ":" + valuse.get(i).trim());
        }
        return infos;
    }

    /**
     * 获取首页的学业信息
     *
     * @return 原始json数据, 太简单了, 就不提供解析工具了
     * @throws IOException
     */
    public String getAcademicInfo() throws IOException {
        //http://jwxt.imu.edu.cn/main/academicInfo?flag=refresh
        Request request = new Request.Builder()
                .url(serverAddress + "/main/academicInfo?flag=refresh")
                .get()
                .build();
        Response response = okHttpClient.newCall(request).execute();
        if (!response.isSuccessful()) return null;
        return response.body().string();
    }
}