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
	private foodRepository foodRepository;


	@Autowired
	private menuRepository menuRepository;

	//マイメニューへ移動
	@RequestMapping("/myMenu")
	public ModelAndView myMenu(
			ModelAndView mv) {
		Integer userid = (Integer) session.getAttribute("code");

		List<menu> m2 = menuRepository.findByUserid(userid);
		mv.addObject("list", m2);

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
			@RequestParam("kcalall") double kcalall,

			ModelAndView mv) {
		Integer userid = (Integer) session.getAttribute("code");
		if (!menu.equals("")&&kcalall>0) {
			int dishcode = (int) session.getAttribute("dishcode");
			dishcode = dishcode++;

			menu m = new menu(menu, dishcode, (int) kcalall, userid);

			menuRepository.saveAndFlush(m);

			List<menu> m2 = menuRepository.findByUserid(userid);
			mv.addObject("list", m2);
			mv.setViewName("myMenu");
		} else {
			int dishcode = (int) session.getAttribute("dishcode");

			session.setAttribute("dishcode", dishcode);
			List<SelectedFood> selectedFoods = selectedFoodRepository.findAllByDishCode(dishcode);

			int gramsSum=0;
			double kcalSum = 0;
			for (SelectedFood data :selectedFoods) {
				gramsSum += data.getGrams();
				kcalSum +=data.getCalResult();
			}

			mv.addObject("gramsSum", gramsSum);
			mv.addObject("kcalSum", kcalSum);



			mv.addObject("SelectedFood", selectedFoods);

			mv.addObject("message", "料理名を入力し、食材を追加してください。");
			mv.setViewName("kcalCal");
			List<food> list = foodRepository.findAll();
			mv.addObject("list", list);


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


	//マイメニュー登録（登録ボタン押下）
	@RequestMapping(value = "/Mymenu/regi", method = RequestMethod.POST)
	public ModelAndView menuConfirm(
			ModelAndView mv) {

		mv.setViewName("myMenu");
		return mv;
	}

	@PostMapping("/del")
	public ModelAndView del(
			ModelAndView mv,
			@RequestParam("code") int code

	) {
		menuRepository.deleteById(code);

		Integer userid = (Integer) session.getAttribute("code");

		List<menu> m2 = menuRepository.findByUserid(userid);
		mv.addObject("list", m2);
		mv.setViewName("myMenu");

		return mv;
	}

	@PostMapping("/deladd")
	public ModelAndView deladd(
			ModelAndView mv,
			@RequestParam("code") int code

	) {
		int dishcode = (int) session.getAttribute("dishcode");

		selectedFoodRepository.deleteById(code);

		List<SelectedFood> selectedFoods = selectedFoodRepository.findAllByDishCode(dishcode);

		int gramsSum = 0;
		double kcalSum = 0;
		for (SelectedFood data : selectedFoods) {
			gramsSum += data.getGrams();
			kcalSum += data.getCalResult();
		}
		List<food> list = foodRepository.findAll();


		mv.addObject("list", list);

		mv.addObject("gramsSum", gramsSum);
		mv.addObject("kcalSum", kcalSum);

		mv.addObject("SelectedFood", selectedFoods);

		mv.setViewName("kcalCal");

		return mv;
	}

}