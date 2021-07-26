package com.example.demo;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserInfoCoentroller {

	@Autowired
	private userRepository userRepository;
	@Autowired
	HttpSession session;

	//ユーザ情報画面へ移動
	@RequestMapping("/userPage")
	public ModelAndView userPage(
			ModelAndView mv) {
		Integer userid = (Integer) session.getAttribute("code");

		int code = userid;
		Optional<user> record = userRepository.findById(code);

		user user = record.get();

		mv.addObject("user", user);
		return mv;
	}

}