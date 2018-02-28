package cn.likole.jwxt;

import cn.likole.jwxt.entity.CourseList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by likole on 1/24/18.
 * 课程表解析器
 */
public class CourseListParser {
    /**
     * 解析
     *
     * @param json 返回的原始JSON数据
     * @return
     */
    public static List<CourseList> parse(String json) {
        JsonObject root = (JsonObject) new JsonParser().parse(json);
        JsonArray dateList = root.getAsJsonArray("dateList");
        List<CourseList> courseLists = new Gson().fromJson(dateList, new TypeToken<List<CourseList>>() {
        }.getType());
        return courseLists;
    }
}
