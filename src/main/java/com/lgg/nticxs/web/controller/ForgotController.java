package com.lgg.nticxs.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForgotController {
	@GetMapping("/forgot")
    public String pageForgot(Model model) {
		return "forgot-password";	
	}

}
