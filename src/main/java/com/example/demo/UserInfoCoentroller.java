package com.example.demo;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		mv.setViewName("userPage");
		return mv;
	}

	@RequestMapping("/userEdit")
	public ModelAndView userEdit(
			ModelAndView mv) {
		Integer userid = (Integer) session.getAttribute("code");

		int code = userid;
		Optional<user> record = userRepository.findById(code);

		user user = record.get();

		mv.addObject("user", user);
		mv.setViewName("userEdit");

		return mv;
	}

	@PostMapping("/userEditComp")
	public ModelAndView EditComp(
			ModelAndView mv,
			@RequestParam("NAME") String name,
			@RequestParam("height") Integer height,
			@RequestParam("weight") Integer weight,
			@RequestParam("age") Integer age,
			@RequestParam("gender") Integer gender
) {
Integer code=(Integer) session.getAttribute("code");
user user2 = (user) session.getAttribute("user");

String email = user2.getEmail();
String password = user2.getPassword();
		user user = new user(code, name, email, password, height,weight,age,gender);
		userRepository.saveAndFlush(user);



		mv.setViewName("redirect:/userPage");

		return mv;

	}

}