/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scenomania;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author eugene
 */
public class ResourceClassLoader extends ClassLoader{

  protected URL findResource(String name) {

    //String classPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    //File f = new File("/home/eugene/www/bundles/" + name);

    File f = new File(name);

    File ttt = new File("..");
    File[] ddd = ttt.listFiles();

    try
    {
      return f.toURL();
    }
    catch (MalformedURLException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return super.findResource(name);
  }

}
