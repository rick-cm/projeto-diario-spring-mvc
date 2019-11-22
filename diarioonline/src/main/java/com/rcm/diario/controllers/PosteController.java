package com.rcm.diario.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PosteController {
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}
}
