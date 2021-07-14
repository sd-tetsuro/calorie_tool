package com.example.demo;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class KcalCalCoentroller {

	@Autowired
	HttpSession session;

	@Autowired
	private foodRepository foodRepository;

	@Autowired
	private SelectedFoodRepository selectedFoodRepository;

	@Autowired
	private mylistsRepository mylistsRepository;

	@Autowired
	private menuRepository menuRepository;

	//メニュー登録（カロリー計算へ移動）
	//全件表示
	@RequestMapping("/kcalCal")
	public ModelAndView kcalCal(
			ModelAndView mv) {
		List<food> list = foodRepository.findAll();

		List<menu> d = menuRepository.findAllByOrderByDishcodeDesc();
		if (d.size() > 0) {

			menu m = d.get(0);

			Integer dishcode = m.getDishcode();
			System.out.println(dishcode);
			dishcode++;
			session.setAttribute("dishcode", dishcode);
		} else {
			session.setAttribute("dishcode", 1);
		}

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
			@RequestParam("calResult") double calResult,
			@RequestParam("grams") int grams

	) {
/////////////リストに合計値を入力////////////////////////////
//		List<grams> list = new ArrayList<grams>();
//
//		Integer g = list.get(grams);
//
//((List<food .add(grams);
//		list.add(calResult);
//
//		for (int i = 0 ; i < list.size(); i++) {
//
//		}
//
////////////////////////////////////////////////////////

//		List<Integer> gramsSum = new ArrayList<Integer>();
//		gramsSum.add(grams);
//		for (int i = 0 ; i < gramsSum.size(); i++) {
//			List<Integer> gramsResult = gramsSum;
//			System.out.println(gramsResult);
//		}
		//1この情報をDBに登録
		int dishcode = (int) session.getAttribute("dishcode");

		SelectedFood selectedFood = new SelectedFood(uname, (int) calResult, grams, dishcode);

		selectedFoodRepository.saveAndFlush(selectedFood);
		//2DBから登録食材の一覧を取得
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
		mv.addObject("uname", uname);
		mv.addObject("calResult", calResult);
		mv.addObject("grams", grams);
		mv.setViewName("kcalCal");
		return kcalCal(mv);
	}

}