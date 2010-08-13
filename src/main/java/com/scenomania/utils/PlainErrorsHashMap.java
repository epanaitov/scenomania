package com.scenomania.utils;

import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author eugene
 */
public class PlainErrorsHashMap extends HashMap<String, LinkedList> {

	public boolean addError(String key, String message) {
		if (!this.containsKey(key)) this.put(key, new LinkedList());
		return this.get(key).add(message);
	}

}
