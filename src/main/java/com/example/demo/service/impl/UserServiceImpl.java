package com.example.demo.service.impl;

import com.example.demo.data.UserData;
import com.example.demo.exception.SourceNotFoundException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.UserModel;
import com.example.demo.service.UserService;
import com.example.demo.util.CglibBeanUtil;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author qsky on 2018/6/26
 */
@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	@Override
	public List<UserData> listAll() {
		return CglibBeanUtil.converterList(userMapper.findAll(), UserData.class);
	}

	@Override
	public UserData getByPk(Long pk) {
		UserModel userModel = userMapper.findByPk(pk);
		if (userModel == null) {
			throw new SourceNotFoundException();
		}
		return CglibBeanUtil.copyProperties(userModel, UserData.class);
	}

	@Override
	public UserData getByUid(String uid) {
		UserModel userModel = userMapper.findByUid(uid);
		if (userModel == null) {
			throw new SourceNotFoundException();
		}
		return CglibBeanUtil.copyProperties(userModel, UserData.class);
	}

	@Override
	public boolean insert(UserData user) {
		Integer result = userMapper.insert(CglibBeanUtil.copyProperties(user, UserModel.class));
		return result == 1;
	}

	@Override
	public boolean update(UserData user) {
		Integer result = userMapper.update(CglibBeanUtil.copyProperties(user, UserModel.class));
		return result == 1;
	}

	@Override
	public boolean delete(Long pk) {
		Integer result = userMapper.delete(pk);
		return result == 1;
	}
}
