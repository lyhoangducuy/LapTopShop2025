package com.latptop.flexuy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class DashBoardController {
	@GetMapping
	public String getMethodName() {
		return "redirect:/admin/";
	} 
	

	@GetMapping("/")
	public String getDashBoard() {
		return "admin/dashboard/dashBoard";
	}
}
