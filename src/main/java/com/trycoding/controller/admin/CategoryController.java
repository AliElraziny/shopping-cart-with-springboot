package com.trycoding.controller.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.trycoding.model.Category;
import com.trycoding.service.CategoryService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin-category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService; 
	
	@GetMapping("/")
	public String addCategory(Model model) {
		List<Category> cats = categoryService.getAllCategory();
		model.addAttribute("category" ,cats );
		return  "/admin/category/add-category";
	}
	
	@PostMapping("/saveCategory")
	public String saveCategory(@ModelAttribute Category category ,@RequestParam("file") MultipartFile file 
			, HttpSession session) throws IOException {
		
		String imageName = file!=null ? file.getOriginalFilename() : "default.jpg" ;
		category.setImgName(imageName);
		
		Boolean existCategory = categoryService.existCategory(category.getName());
		if(existCategory) {
			session.setAttribute("errorMsg", "Category Name already exist");
		}else {
			Category saveCategory = categoryService.saveCategory(category);
			if(ObjectUtils.isEmpty(saveCategory)) {
				session.setAttribute("errorMsg", "Not saved !! internal server error ");
			}else {
				
				File saveFile = new ClassPathResource("static/img").getFile();
				Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+"category_img"+File.separator+file.getOriginalFilename());
				System.out.println(path);
				System.out.println(file.getInputStream());
				Files.copy(file.getInputStream(), path ,StandardCopyOption.REPLACE_EXISTING);
				session.setAttribute("succMsg", "Saved Successfully");
			}
		}
		return "redirect:/admin-category/";
	}
	

}
