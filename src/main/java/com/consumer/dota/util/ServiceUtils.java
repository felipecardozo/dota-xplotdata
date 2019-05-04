package com.consumer.dota.util;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class ServiceUtils {
	
	public static final long SLEEP = 5000; 
	
	public static void sleep() {
		try {
			Thread.sleep(SLEEP);
		} catch (InterruptedException e) {
			System.err.println(e.getMessage());
		}
	}
	
	public static Boolean isMatchInDB(Long id, List<Long> ids) {
		if(ids.contains(id)) return true;
		return Boolean.FALSE;
	}
	
	public static HttpEntity<String> getEntity() {
		HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        return entity;
	}

}
