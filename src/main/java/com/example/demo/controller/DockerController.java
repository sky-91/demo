package com.example.demo.controller;

import com.example.demo.data.UserData;
import com.example.demo.data.UserDetailData;
import com.example.demo.export.TableToCSV;
import com.example.demo.queue.HelloSenderOne;
import com.example.demo.queue.HelloSenderTwo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qsky on 2018/4/9
 */
@RestController
public class DockerController {

	@Resource
	private HelloSenderOne sender1;

	@Resource
	private HelloSenderTwo sender2;

	@Resource
	private TableToCSV tableToCSV;

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

	@RequestMapping("/mq")
	public void mq() {
		UserData data = new UserData();
		data.setUid("444");
		data.setName("李四");
		sender1.send(data);
		UserData data1 = new UserData();
		data1.setUid("555");
		data1.setName("王五");
		sender1.send2(data1);
		UserDetailData user = new UserDetailData();
		user.setAge(12);
		user.setName("张三年");
		user.setUid("124123123");
		user.setAddress("kjasdjaskhdk");
		sender1.send1(user);
	}

	@RequestMapping("/csv")
	public void csvExport() {
		String sql = "SELECT * FROM RPT_RELATION_IMPL";
		try {
			Connection conn = tableToCSV.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			tableToCSV.jdbcExport("c:\\approot1\\csv\\1.csv", "c:\\approot1\\zip\\1.csv", "", result);
			tableToCSV.closeConnection(result, statement, conn);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/fanout")
	public void fanoutExchange() {
		sender1.send3("fanout test");
	}
}
