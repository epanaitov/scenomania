/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.scenomania.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.Calendar;

/**
 *
 * @author eugene
 */
@Component(value="DateTime")
@Scope("singleton")
public class DateTimeBean {
	public Integer getYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}
}
