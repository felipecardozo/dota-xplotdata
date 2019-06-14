package com.consumer.dota.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consumer.dota.service.AccountFileService;

@RestController
public class CSVController {
	
	private AccountFileService accountFileService;
	
	@Autowired
	public CSVController(AccountFileService accountFileService) {
		this.accountFileService = accountFileService;
	}
	
	@GetMapping("/csv/create")
	public String createFiles() {
		return "finished " + accountFileService.createFileByAccount();
	}

}
