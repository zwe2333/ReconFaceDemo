package com.zwe.reconfacedemo;

import com.baidu.aip.face.AipFace;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Asus on 2017/4/18.
 */

public class DetectUtils {
    //设计单例
    private static volatile DetectUtils instance;

    private DetectUtils(){}

    public static DetectUtils getInstance(){
        if (instance==null){
            synchronized (DetectUtils.class){
                if (instance==null){
                    instance=new DetectUtils();
                }
            }
        }
        return instance;
    }
    //设置APPID/AK/SK
    public static final String APP_ID = "9535658";
    public static final String API_KEY = "23BjWNFM4TSxhtcFvhpZRlTb";
    public static final String SECRET_KEY = "KE7SGkYvjxGeyglgriWLnjosN0lLbNY5";

    public JSONObject getJson(String path,String param1,String param2,String param3){
        // 初始化一个FaceClient
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 调用API
        HashMap<String,String> map=new HashMap<>();
        map.put("face_fields","gender");
/*        map.put("face_fields",param2);
        map.put("face_fields",param3);*/
        JSONObject res = client.detect(path, map);
        return res;
    };
}
