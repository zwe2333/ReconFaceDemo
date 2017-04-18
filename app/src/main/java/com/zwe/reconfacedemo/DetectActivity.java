package com.zwe.reconfacedemo;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Asus on 2017/4/18.
 */

public class DetectActivity extends AppCompatActivity implements View.OnClickListener{
    private static final int PICK_CODE = 1001;
    private static final String TAG = "DetectActivity";
    private FaceView imgSrc;
    private ImageView imgGender;
    private Button btnSelectImage;
    private Button btnDetectFace;
    private String path=null;//获取图片路径
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detect);
        //初始化控件
        initViews();
    }

    private void initViews() {
        //绑定控件
        imgSrc= (FaceView) findViewById(R.id.imgSrc);
        imgGender= (ImageView) findViewById(R.id.imgGender);
        btnSelectImage= (Button) findViewById(R.id.btnSelectImage);
        btnDetectFace= (Button) findViewById(R.id.btnDetectFace);
        //设置监听事件
        btnSelectImage.setOnClickListener(this);
        btnDetectFace.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSelectImage:
                //打开本地图库
                openLocationGallery();
                break;
            case R.id.btnDetectFace:
                //开始识别
                startToDetect();
                break;
        }
    }

    private void startToDetect() {
        if (path==null){
            Toast.makeText(DetectActivity.this,"图片路径为空",Toast.LENGTH_SHORT).show();
            return;
        }
        netToGetJson();
    }

    private void netToGetJson() {
        Observable observable=Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter e) throws Exception {
                JSONObject object=DetectUtils.getInstance().getJson(path,null,null,null);
                e.onNext(object);
            }
        });
        Consumer consumer=new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                JSONObject object= (JSONObject) o;
                parseJson(object);
                System.out.println(object.toString());
                Toast.makeText(DetectActivity.this,object.toString()+"****",Toast.LENGTH_LONG).show();
            }
        };
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer);
    }

    private void parseJson(JSONObject object) {
        Gson gson=new Gson();
        FaceMsg msg=gson.fromJson(object.toString(),FaceMsg.class);
        int left=msg.getResult().get(0).getLocation().getLeft();
        int top=msg.getResult().get(0).getLocation().getTop();
        int width=msg.getResult().get(0).getLocation().getWidth();
        int height=msg.getResult().get(0).getLocation().getHeight();
        //应有设置方法去适配屏幕的大小，从而获取正常的矩形，0.70f纯粹为测试出来的数据，不同手机要适配不同的数据
        imgSrc.drawFace(new Rect((int)(left/0.70f),(int)(top/0.70f),(int)((width+left)/0.70f),(int)((height+top)/0.70f)));
    }

    private void openLocationGallery() {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,PICK_CODE);
    }

    //intent请求响应
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if(requestCode==PICK_CODE)
        {
            if(intent!=null)
            {
                //获取所有图片资源
                Uri uri=intent.getData();
                //设置指针获得一个ContentResolver的实例
                Cursor cursor=getContentResolver().query(uri,null,null,null,null);
                cursor.moveToFirst();
                //返回索引项位置
                int index=cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                //返回索引项路径
                path=cursor.getString(index);
                if (path!=null){
                    Glide.with(DetectActivity.this).load(path).into(imgSrc);
                }
                cursor.close();
            }
        }
    }
}
