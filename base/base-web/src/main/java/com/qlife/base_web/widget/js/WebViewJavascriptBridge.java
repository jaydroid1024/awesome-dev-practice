package com.qlife.base_web.widget.js;

public interface WebViewJavascriptBridge {

  public void send(String data);

  public void send(String data, CallBackFunction responseCallback);
}
