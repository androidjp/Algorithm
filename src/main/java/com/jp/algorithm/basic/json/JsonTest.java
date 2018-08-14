package com.jp.algorithm.basic.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.readFileToString;

/**
 * Created by androidjp on 16/9/9.
 */
public class JsonTest {


    public static void main(String[] args) throws IOException {
        File file = new File(JsonTest.class.getResource("/main/basic/json/test.json").getFile());
        String content = readFileToString(file);
        System.out.println(content);
        JSONObject jsonObject = new JSONObject(content);
        if (!jsonObject.isNull("name")) {
            System.out.println(jsonObject.getString("name"));
        }
        JSONArray array = jsonObject.getJSONArray("major");
        for(int i=0;i<array.length();i++){
            System.out.println("major: "+ array.get(i));
        }
    }

}
