package com.example.springbootsecuritycourse.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class TemplateController {

	@GetMapping({ "/login", "/" })
	public String getLoginView() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:dashboard";
		}
		return "login";
	}

	@GetMapping("/dashboard")
	public String getDashboardView() {
		return "dashboard";
	}
}
