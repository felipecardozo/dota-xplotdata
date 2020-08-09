package com.dota.team.pro.utils;

public class ServiceUtils {

	public static final long SLEEP = 5000;

	public static void sleep() {
		try {
			Thread.sleep(SLEEP);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}

}
