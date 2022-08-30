package com.example.MyProject.controllers;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.MyProject.models.Project;
import com.example.MyProject.models.User;
import com.example.MyProject.repositories.MyProjectRepository;
import com.example.MyProject.services.CustomUserDetailsService;







@Controller 
public class NotesController {
	
	
	@Autowired
	private CustomUserDetailsService userService;
	
	@Autowired
	private MyProjectRepository noteRepository;
	
	@RequestMapping(value = "/notes")
	public ModelAndView notes() {
		
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("currentUser", user) ;
		modelAndView.addObject("notes",  noteRepository.findAll());
		modelAndView.addObject("fullName", "welcome "+ user.getFullname());
		modelAndView.addObject("adminMessage", "Content avilable only for users with admin");
		modelAndView.setViewName("notes");
		
		
		return modelAndView;
		
		
	}
	
	@RequestMapping(value = "/notes/create")
	public ModelAndView create() {
		
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("currentUser", user) ;
		modelAndView.addObject("fullName", "welcome "+ user.getFullname());
		modelAndView.addObject("adminMessage", "Content avilable only for users with admin");
		modelAndView.setViewName("create");
		
		
		return modelAndView;
		
		
	}
	
	@RequestMapping(value = "/notes/save")
	public String  save(@RequestParam String title,@RequestParam String content) {
		
	Project note = new Project();
	note.setTitle(title);
	note.setContent(content);
	note.setUpdated((java.sql.Date) new Date());
	noteRepository.save(note);	
	
		return "redirect:/notes/show" + note.getId();
		
		
	}
	
	@RequestMapping(value = "/notes/delete")
	public String  delete(@RequestParam Long id,@RequestParam String content) {
		
	Project note = noteRepository.findById(id).orElse(null);
	noteRepository.delete(note);
	
		return "redirect:/notes";
		
		
	}
	
	@RequestMapping(value="/notes/show/{id}")
	public ModelAndView show(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("currentUser", user) ;
		modelAndView.addObject("note", noteRepository.findById(id).orElse(null));
		modelAndView.addObject("fullName", "welcome "+ user.getFullname());
		modelAndView.addObject("adminMessage", "Content avilable only for users with admin");
		modelAndView.setViewName("show");
		
		

		return modelAndView;
		
		
	}	
	
	
	@RequestMapping(value = "/notes/edit")
	public ModelAndView edit(@PathVariable Long id) {
		
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("currentUser", user) ;
		modelAndView.addObject("note", noteRepository.findAll());
		modelAndView.addObject("fullName", "welcome "+ user.getFullname());
		modelAndView.addObject("adminMessage", "Content avilable only for users with admin");
		modelAndView.setViewName("edit");
		
		
		return modelAndView;
		
		
	}
	
	@RequestMapping(value = "/notes/update")
	public String  update(@RequestParam Long id ,@RequestParam String title,@RequestParam String content) {
		
	Project note =noteRepository.findById(id).orElse(null);
	note.setTitle(title);
	note.setContent(content);
	note.setUpdated((java.sql.Date) new Date());
	noteRepository.save(note);	
	
		return "redirect:/notes/show" + note.getId();
		
		
	}
	

}
