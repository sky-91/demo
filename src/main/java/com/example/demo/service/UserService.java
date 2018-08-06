package com.example.demo.service;

import com.example.demo.data.UserData;
import java.util.List;

/**
 * @author qsky on 2018/6/26
 */
public interface UserService {

	List<UserData> listAll();

	UserData getByPk(Long pk);

	UserData getByUid(String uid);

	boolean insert(UserData user);

	boolean update(UserData user);

	boolean delete(Long pk);
}
