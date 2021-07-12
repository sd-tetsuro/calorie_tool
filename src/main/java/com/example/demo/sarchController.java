package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class sarchController {

	@Autowired
	foodRepository foodRepository;

	//食材検索（検索ボタン押下）
		@RequestMapping(value = "/sarch", method = RequestMethod.POST)
		public ModelAndView sarch(
				@RequestParam("name") String keyword,
				ModelAndView mv) {

			if (!keyword.equals("")) {

			List<food>food = foodRepository.findByNameLike("%" + keyword + "%");
			mv.addObject("list", food);
			mv.addObject("keyword",keyword);
			mv.setViewName("sarch");
			}else {

				mv.addObject("message", "検索が未入力です");
				mv.setViewName("kcalCal");
			}


			return mv;
		}

		//マイメニュー登録（登録ボタン押下）
		@RequestMapping(value = "/OK", method = RequestMethod.POST)
		public ModelAndView OKbottun(
				@RequestParam("keyword") String keyword,
				ModelAndView mv) {
			List<food>food = foodRepository.findByNameLike("%" + keyword + "%");
			mv.addObject("list", food);

			mv.setViewName("sarch");
			return mv;
		}
}
