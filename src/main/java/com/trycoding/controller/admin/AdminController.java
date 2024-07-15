package com.trycoding.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/")
	public String index() {
		
		return  "/admin/index";
	}
	@GetMapping("/addProduct")
	public String addProduct() {
		
		return  "/admin/add-product";
	}
	@GetMapping("/addCategory")
	public String addCategory() {
		
		return  "redirect:/admin-category/";
	}
	@GetMapping("/viewProducts")
	public String viewProducts() {
		
		return  "/admin/view-product";
	}
	@GetMapping("/orders")
	public String orders() {
		
		return  "/admin/orders";
	}
	@GetMapping("/users")
	public String user() {
		
		return  "/admin/users";
	}
	@GetMapping("/addAdmin")
	public String addAdmin() {
		
		return  "/admin/add-admin";
	}

}
