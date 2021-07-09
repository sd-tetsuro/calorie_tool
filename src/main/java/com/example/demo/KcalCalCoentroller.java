package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class KcalCalCoentroller {

	@Autowired
	private foodRepository foodRepository;

	//メニュー登録（カロリー計算へ移動）
	//全件表示
	@RequestMapping("/kcalCal")
	public ModelAndView kcalCal(
			ModelAndView mv) {
		List<food> list = foodRepository.findAll();

		mv.addObject("list", list);

		mv.setViewName("kcalCal");

		return mv;
	}

	//
	@RequestMapping("/findByCode")
	public ModelAndView foodsByCode(
			ModelAndView mv,
			@RequestParam("value") int categorycode

	) {
		List<food> food = foodRepository.findByCategorycode(categorycode);
		mv.addObject("list", food);



		mv.setViewName("kcalCal");
		return mv;
	}


	//マイメニューへ移動
	@RequestMapping("/myMenu")
	public ModelAndView myMenu(
			ModelAndView mv) {
		mv.setViewName("myMenu");

		return mv;
	}

	//カスタム入力画面へ移動
	@RequestMapping("/custom")
	public ModelAndView custom(
			ModelAndView mv) {
		mv.setViewName("custom");

		return mv;
	}

	//メニュー登録（登録ボタン押下）
	@RequestMapping(value = "/myMenu", method = RequestMethod.POST)
	public ModelAndView confirm(
			ModelAndView mv) {

		mv.setViewName("myMenu");
		return mv;
	}

	//食材検索（検索ボタン押下）
	@RequestMapping(value = "/sarch", method = RequestMethod.POST)
	public ModelAndView sarch(
			ModelAndView mv) {

		mv.setViewName("sarch");
		return mv;
	}

	//カスタム入力（登録ボタン押下）
	@RequestMapping(value = "/custom/regi", method = RequestMethod.POST)
	public ModelAndView customRegi(
			ModelAndView mv) {

		mv.setViewName("custom");
		return mv;
	}
	//カスタム登録（EATボタン押下）
	@RequestMapping(value = "/custom/confirm", method = RequestMethod.POST)
	public ModelAndView customConfirm(
			ModelAndView mv) {

		mv.setViewName("confirm");
		return mv;
	}
	//マイメニュー登録（登録ボタン押下）
	@RequestMapping(value = "/Mymenu/regi", method = RequestMethod.POST)
	public ModelAndView menuConfirm(
			ModelAndView mv) {

		mv.setViewName("myMenu");
		return mv;
	}

}