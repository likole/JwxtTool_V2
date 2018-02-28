package cn.likole.jwxt;

import cn.likole.jwxt.entity.ScoreList;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by likole on 1/23/18.
 * 成绩列表解析器
 */
public class ScoreListParser {
    /**
     * 解析成绩查询返回的json数据,适用于有多个列表的情况,例如全部及格成绩,课程属性成绩
     *
     * @param json 返回的原始json数据
     * @return
     */
    public static List<ScoreList> parseAll(String json) {
        List<ScoreList> scoreLists = new Gson().fromJson(json, new TypeToken<List<ScoreList>>() {
        }.getType());
        return scoreLists;
    }

    /**
     * 解析成绩查询返回的json数据,适用于有只有一个列表的情况,例如方案成绩
     *
     * @param json 返回的原始json数据
     * @return
     */
    public static ScoreList parse(String json) {
        JsonArray root = new JsonParser().parse(json).getAsJsonArray();
        JsonObject scoreList = (JsonObject) root.get(0);
        return new Gson().fromJson(scoreList, new TypeToken<ScoreList>() {
        }.getType());
    }
}
