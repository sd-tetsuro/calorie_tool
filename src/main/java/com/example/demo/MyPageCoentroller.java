package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyPageCoentroller {



	@RequestMapping("/myPage")
	public ModelAndView MyPage(
			ModelAndView mv) {
		mv.setViewName("myPage");

		return mv;
	}

	@RequestMapping(value = "dish/confirm", method = RequestMethod.POST)
	public ModelAndView confirm(
			ModelAndView mv) {

			mv.setViewName("myPage");
			return mv;
	}

}