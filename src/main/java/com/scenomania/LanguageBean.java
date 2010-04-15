package com.scenomania;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component("lng")
@Scope("session")

public class LanguageBean {
  private ApplicationContext context = null;

  public LanguageBean() {
    context = new ClassPathXmlApplicationContext(new String[] {java.util.ResourceBundle.getBundle("com/scenomania/messages").getString("LOCALE.XML")});
  }

  
}
