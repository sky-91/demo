package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qsky on 2018/4/9
 */
@RestController
public class DockerController {

	@RequestMapping("/")
	public String index() {
		return "Hello Docker!";
	}

	@RequestMapping("/filter")
	public String filter() {
		return "Filter OK!";
	}

	@RequestMapping("/set")
	public Map<String, Object> set(HttpServletRequest request, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		session.setAttribute("url", request.getRequestURL());
		map.put("url", request.getRequestURL());
		return map;
	}

	@RequestMapping("/session")
	public Object session(HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		map.put("sessionId", session.getId());
		map.put("url", session.getAttribute("url"));
		return map;
	}
}
