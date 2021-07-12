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

	@Autowired
	private SelectedFoodRepository selectedFoodRepository;

	@Autowired
	private mylistsRepository mylistsRepository;

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

	//カテゴリー検索
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

	//食材をリストに追加
	@RequestMapping("/addFood")
	public ModelAndView add(
			ModelAndView mv,
			@RequestParam("uname") String uname,
			@RequestParam("calResult") int calResult,
			@RequestParam("grams") int grams


	) {/*System.out.println(uname);
	System.out.println(calResult);
	System.out.println(grams);*/

//1この情報をDBに登録
		//2DBから登録食材の一覧を取得
		SelectedFood selectedFood = new SelectedFood(uname, calResult, grams);

		selectedFoodRepository.saveAndFlush(selectedFood);

		mv.addObject("SelectedFood", selectedFood);
		mv.addObject("uName",uname);
		mv.addObject("calResult",calResult);
		mv.addObject("grams",grams);
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