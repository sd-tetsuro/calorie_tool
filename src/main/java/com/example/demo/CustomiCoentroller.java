package com.example.demo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CustomiCoentroller {

	@Autowired
	HttpSession session;

	@Autowired
	private SelectedFoodRepository selectedFoodRepository;

	@Autowired
	private mylistsRepository mylistsRepository;
	@Autowired
	private foodRepository foodRepository;


	@Autowired
	private menuRepository menuRepository;

	//カスタム入力（登録ボタン押下）
	@RequestMapping(value = "/custom/regi", method = RequestMethod.POST)
	public ModelAndView customRegi(
			@RequestParam("dishname") String dishname,
			@RequestParam("kcal") String kcal,
			ModelAndView mv) {
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


}