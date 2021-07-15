package com.example.demo;


import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyPageCoentroller {

	@Autowired
	HttpSession session;

	@Autowired
	private KcalRepository kcalRepository;

	@RequestMapping("/myPage")
	public ModelAndView MyPage(
			ModelAndView mv) {
		mv.setViewName("myPage");

		return mv;
	}
	@RequestMapping(value = "/myPage", method = RequestMethod.POST)
	public ModelAndView kakutei(
			@RequestParam("dishname") String dishname,
			@RequestParam("kcalall") Integer kcalall,
			@RequestParam("time")  String time,
			@RequestParam("calendar") String date,
			ModelAndView mv) {


		time+=":00";
		Time t=Time.valueOf(time);

		Date d=Date.valueOf(date);

		Kcal kcal=new Kcal(d,t,dishname,kcalall);

		// 登録処理date d
		kcalRepository.saveAndFlush(kcal);

///
		List<Kcal> cal = kcalRepository.findByDate(d);







			mv.addObject("list", cal);
			mv.addObject("date", d);
			mv.setViewName("myPage");


		mv.setViewName("myPage");

		return mv;
	}

}