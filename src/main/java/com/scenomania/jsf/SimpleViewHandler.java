package com.scenomania.jsf;

import java.io.IOException;
import java.util.Locale;

import javax.faces.FacesException;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

public class SimpleViewHandler extends ViewHandler {
	ViewHandler defaultHandler;
	
	public SimpleViewHandler(ViewHandler defaultHandler) {
	    this.defaultHandler = defaultHandler;
	}
	@Override
	public Locale calculateLocale(FacesContext context) {
		 return defaultHandler.calculateLocale(context);
	}

	@Override
	public String calculateRenderKitId(FacesContext context) {
		return defaultHandler.calculateRenderKitId(context);
	}

	@Override
	public UIViewRoot createView(FacesContext context, String viewId) {
		 return defaultHandler.createView(context, viewId);
	}

	@Override
	public String getActionURL(FacesContext context, String viewId) {
		ApplicationContext ctx  = FacesContextUtils.getWebApplicationContext(FacesContext.getCurrentInstance());
		
		if (! ctx.containsBean("faceletsViewResolver")){
			return viewId;
		}
		
		Object bean = (Object)ctx.getBean("faceletsViewResolver"); 
		ctx = null;
		
		if (bean instanceof SimpleUrlBasedViewResolver){			
			SimpleUrlBasedViewResolver subvr = (SimpleUrlBasedViewResolver)bean;
			String reqContext = context.getExternalContext().getRequestContextPath();
			
			String prefix = subvr.getPrefix();
			String suffix = subvr.getSuffix();
			String extension = subvr.getExtension();
			String resultURL = new String(viewId);
			
			if (prefix != null){
				if (prefix.endsWith("/") && prefix.length() > 1){
					prefix = prefix.substring(0, prefix.length() - 1);
				}
				
				if (resultURL.toLowerCase().startsWith(prefix.toLowerCase())){
					resultURL = resultURL.substring(prefix.length());
				}
			}
			
			if (suffix != null){
				if (resultURL.toLowerCase().endsWith(suffix.toLowerCase())){
					resultURL = resultURL.substring(0, resultURL.length() - suffix.length());
				}
			}

			StringBuilder result = new StringBuilder(reqContext).append(resultURL);
			if (extension != null && !resultURL.endsWith("/")){
					result.append(extension);
			}
			return result.toString();
		
		}
		
		return viewId;
	}

	@Override
	public String getResourceURL(FacesContext context, String path) {
		  return defaultHandler.getResourceURL(context, path);
	}

	@Override
	public void renderView(FacesContext context, UIViewRoot viewToRender)
			throws IOException, FacesException {
		 defaultHandler.renderView(context, viewToRender);
	}

	@Override
	public UIViewRoot restoreView(FacesContext context, String viewId) {
		 return defaultHandler.restoreView(context, viewId);
	}

	@Override
	public void writeState(FacesContext context) throws IOException {
		  defaultHandler.writeState(context);
	}

}
