package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyMenuCoentroller {


//マイメニュー詳細画面へ移動
	@RequestMapping("/myMenuDetail")
	public ModelAndView MyPage(
			ModelAndView mv) {
//		mv.setViewName("redirect:/myMenu");
//	mv.setViewName("myMenuDetail");

		return mv;
	}

}