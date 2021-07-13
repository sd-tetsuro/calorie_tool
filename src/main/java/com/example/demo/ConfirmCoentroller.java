package com.example.demo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmCoentroller {

	@Autowired
	HttpSession session;

	@Autowired
	private SelectedFoodRepository selectedFoodRepository;

	@Autowired
	private mylistsRepository mylistsRepository;

	@Autowired
	private menuRepository menuRepository;


	//マイメニュー（EATボタン押下）
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public ModelAndView Confirm(
			ModelAndView mv) {

		mv.setViewName("confirm");
		return mv;
	}


	//カスタム（EATボタン押下）
	@RequestMapping(value = "/custom/confirm", method = RequestMethod.POST)
	public ModelAndView customConfirm(
			ModelAndView mv) {

		mv.setViewName("confirm");
		return mv;
	}

}