package com.example.demo;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DetailCoentroller {

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

	@RequestMapping("/detail/{code}")
	public ModelAndView menudetail(
			@PathVariable(name = "code") Integer code,
			ModelAndView mv) {

		Optional<menu> m = menuRepository.findById(code);
		menu m2 = m.get();
		Integer dishcode = m2.getDishcode();

		List<SelectedFood> list = selectedFoodRepository.findByDishCode(dishcode);
		mv.addObject("m2", m2);
		mv.addObject("menu", list);
		mv.setViewName("myMenuDetail");
		return mv;
	}

}