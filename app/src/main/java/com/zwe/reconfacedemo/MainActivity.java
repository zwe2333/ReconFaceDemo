package com.zwe.reconfacedemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnDetect,btnCompare,btnSelect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化View
        initViews();
    }

    private void initViews() {
        //绑定控件
        btnDetect= (Button) findViewById(R.id.btnDetect);
        btnCompare= (Button) findViewById(R.id.btnCompare);
        btnSelect= (Button) findViewById(R.id.btnSelect);
        //设置点击事件
        btnDetect.setOnClickListener(this);
        btnCompare.setOnClickListener(this);
        btnSelect.setOnClickListener(this);

    }

    //跳转页面的方法
    private void actionStart(Context context,Class activity){
        Intent intent=new Intent(context,activity);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnDetect:
                //跳转至脸部检测功能页面
                actionStart(MainActivity.this,DetectActivity.class);
                break;
            case R.id.btnCompare:
                break;
            case R.id.btnSelect:
                break;
        }
    }
}
