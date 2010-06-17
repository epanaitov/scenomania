package com.scenomania.utils;

import java.util.Random;

public class SaltGenerator {
	public static String getSalt(int length){
		Random rand = new Random();
		byte bytes[] = new byte[length];
		for (int i=0; i < bytes.length; ++i){
			bytes[i] = (byte) (32 + rand.nextInt(94));
		}
		return new String(bytes);
	}
	public static String getSalt(){
		return getSalt(20);
	}
}
