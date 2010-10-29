/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scenomania.controllers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 *
 * @author eugene
 */
public class ControllerBase {
	
	protected Map<String, List> hashErrors(List<FieldError> bindingErrors) {

		Map<String, List> errorsHash = new HashMap();

		for (int i=0; i < bindingErrors.size(); i++) {
			FieldError error = bindingErrors.get(i);

			List tmp = new LinkedList();

			if (errorsHash.containsKey(error.getField())) {
				tmp = errorsHash.get(error.getField());
			} 

			tmp.add(error.getDefaultMessage());
			errorsHash.put(error.getField(), tmp);
		}

		return errorsHash;
	}

}
