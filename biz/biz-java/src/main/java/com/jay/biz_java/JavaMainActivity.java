package com.jay.biz_java;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jay.base_component.arouter.ARPath;

@Route(path = ARPath.PathJava.JAVA_ACTIVITY_PATH)
public class JavaMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_main);
    }
}
