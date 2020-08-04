package com.jay.biz_java.o2_annotation.annotation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jay.biz_java.R;
import com.jay.biz_java.o2_annotation.annotation.inject.InjectUtils;
import com.jay.biz_java.o2_annotation.annotation.inject.InjectView;

@Lance(value = 1, id = "2")
public class MainActivity extends AppCompatActivity {
    int i;


    int j;


    @InjectView(R.id.tv)
    private TextView tv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InjectUtils.injectView(this);

        tv.setText("lance真tm帅！！！");


        Intent intent = new Intent(this, SecondActivity.class)
                .putExtra("name", "23"); //传10个参数
        startActivity(intent);
    }


    static class SecondActivity extends Activity {

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }
    }
}
