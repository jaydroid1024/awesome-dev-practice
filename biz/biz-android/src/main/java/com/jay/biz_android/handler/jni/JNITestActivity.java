package com.jay.biz_android.handler.jni;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jay.biz_android.R;

public class JNITestActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.biz_android_activity_jnitest);

    String text = NDKTools.getStringFromNDK();
    Log.i("gebilaolitou", "text=" + text);
    ((TextView) findViewById(R.id.tv)).setText(text);

  }
}
