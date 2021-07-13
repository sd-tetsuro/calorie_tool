package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuRegiCoentroller {

	@Autowired
	HttpSession session;

	@Autowired
	private SelectedFoodRepository selectedFoodRepository;

	@Autowired
	private mylistsRepository mylistsRepository;

	@Autowired
	private menuRepository menuRepository;



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

	//登録ボタン押下
	@PostMapping("/myMenu")
	public ModelAndView confirm(
			@RequestParam("menu") String menu,
			ModelAndView mv) {
		if (!menu.equals("")) {

		int dishcode = (int) session.getAttribute("dishcode");
		dishcode=dishcode+1;

		menu m = new menu(menu,dishcode);

		menuRepository.saveAndFlush(m);

		List<menu> m2= menuRepository.findAll();
		mv.addObject("list",m2);
		mv.setViewName("myMenu");
		}else {
			mv.addObject("message", "料理名を入力してください。");
			mv.setViewName("kcalCal");

		}
		return mv;

	}
	//メニュー登録（登録ボタン押下）
/*	@RequestMapping(value = "/myMenu", method = RequestMethod.POST)
	public ModelAndView confirm(
			@RequestParam("dishname") String dishname,
			@RequestParam("uname") String uname,
			@RequestParam("kcalall") Integer kcalall,
			@RequestParam("grams") Integer grams,
			@RequestParam("kcal") Integer kcal,
			ModelAndView mv) {

		mylists mylists = new mylists(dishname,uname,kcalall,grams,kcal);

		mylistsRepository.saveAndFlush(mylists);

		mv.addObject("list", mylists);

		mv.setViewName("myMenu");
		return mv;
	}*/


	//カスタム入力（登録ボタン押下）
	@RequestMapping(value = "/custom/regi", method = RequestMethod.POST)
	public ModelAndView customRegi(
			@RequestParam("dishname") String dishname,
			@RequestParam("kcal") String kcal,
			ModelAndView mv
			) {
		if (!dishname.equals("") && !kcal.equals("")) {

			//ユーザーインスタンスの生成
			mylists mylists = new mylists(dishname, Integer.parseInt(kcal));

			mylistsRepository.saveAndFlush(mylists);


			mv.addObject("message", "登録が完了しました。");

			mv.setViewName("custom");
		} else {
			mv.addObject("message", "未入力の項目があります。");
			mv.setViewName("custom");
		}
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