package com.scenomania.beans;

import java.util.Locale;
import java.util.ResourceBundle;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("lng")
@Scope("session")

public class LanguageBean {

  public String m(String key) {
    ResourceBundle bundle = ResourceBundle.getBundle("messages.messages", Locale.getDefault());
    return bundle.getString(key);
  }
}