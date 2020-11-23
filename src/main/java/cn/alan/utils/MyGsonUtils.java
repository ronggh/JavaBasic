package cn.alan.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class MyGsonUtils {
    /**
     * 解析一条JSON数据
     *
     * @param jsonDataString
     * @param type
     * @return
     */
    public static <T> T parseJsonObject(String jsonDataString, Class<T> type) {
        Gson gson = new Gson();
        try {
            T result = gson.fromJson(jsonDataString, type);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析JSON数组
     *
     * @param jsonArrayString
     * @param clazz
     * @return
     */
    public static <T> List<T> parseJsonArray(String jsonArrayString, Class<T> clazz) {
        Type type = (Type)new TypeToken<ArrayList<JsonObject>>() {}.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(jsonArrayString, type);

        ArrayList<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            arrayList.add(new Gson().fromJson(jsonObject, clazz));
        }
        return arrayList;
    }

    /**
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

}
