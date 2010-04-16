package com.scenomania;

import java.util.Locale;
import java.util.ResourceBundle;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("lng")
@Scope("session")

public class LanguageBean {

  public LanguageBean() {
  }

  public String m(String key) {

    ResourceClassLoader loader = new ResourceClassLoader();

    ResourceBundle bundle = ResourceBundle.getBundle("messages", Locale.getDefault(), loader);
    return bundle.getString(key);
  }
}
