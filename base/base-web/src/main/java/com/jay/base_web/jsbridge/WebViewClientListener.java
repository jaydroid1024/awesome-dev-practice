package com.jay.base_web.jsbridge;

import android.graphics.Bitmap;

import com.tencent.smtt.sdk.WebView;

/** WebViewClient回调接口 */
public interface WebViewClientListener {

  void shouldOverrideUrlLoading(WebView view, String url);

  void onPageStarted(WebView view, String url, Bitmap favicon);

  void onPageFinished(WebView view, String url);

  void onSwipLefted();

  void onReceivedError(WebView view, int errorCode, String description, String failingUrl);
}
