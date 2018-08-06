package com.example.demo.controller;

import com.example.demo.data.UserData;
import com.example.demo.service.UserService;
import java.util.List;
import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qsky on 2018/6/26
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;

	@RequestMapping("/list")
	public List<UserData> listAll() {
		return userService.listAll();
	}

	@RequestMapping("/getByPk/{pk}")
	public UserData getByPk(@NotBlank @PathVariable Long pk) {
		return userService.getByPk(pk);
	}

	@RequestMapping("/getByUid/{uid}")
	public UserData getByUid(@NotBlank @PathVariable String uid) {
		return userService.getByUid(uid);
	}

	@PostMapping("/insert")
	public boolean insert(@Valid @RequestBody UserData user) {
		return userService.insert(user);
	}

	@PutMapping("/update")
	public boolean update(UserData user) {
		return userService.update(user);
	}

	@DeleteMapping("/delete/{pk}")
	public boolean delete(@NotBlank @PathVariable Long pk) {
		return userService.delete(pk);
	}
}
