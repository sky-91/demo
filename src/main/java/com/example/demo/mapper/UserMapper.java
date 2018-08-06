package com.example.demo.mapper;

import com.example.demo.model.UserModel;
import java.util.List;

/**
 * @author qsky on 2018/6/26
 */
public interface UserMapper {

	List<UserModel> findAll();

	UserModel findByPk(Long pk);

	UserModel findByUid(String uid);

	Integer insert(UserModel user);

	Integer update(UserModel user);

	Integer delete(Long pk);
}
