package com.consumer.dota;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DotaConsumerApplication {

	public static void main(String[] args) {
		System.out.println("*********** BEGIN ARGS ***************");
		for(String arg:args) {
            System.out.println(arg);
        }
		System.out.println("*********** END ARGS ***************");
		SpringApplication.run(DotaConsumerApplication.class, args);
	}
}
