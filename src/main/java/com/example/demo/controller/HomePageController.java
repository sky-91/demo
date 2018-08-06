package com.example.demo.controller;

import com.example.demo.service.ExportService;
import com.example.demo.service.UserService;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author qsky on 2018/6/19
 */
@Controller
@RequestMapping("/index")
public class HomePageController {

	@Resource
	private UserService userService;

	@Resource
	private ExportService exportService;

	@RequestMapping("")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView();
		view.setViewName("index");
		view.addObject("name", "ZhangSan");
		view.addObject("count", 100);
		view.addObject("users", userService.listAll());
		view.addObject("exportList", exportService.getAll());
		return view;
	}
}
