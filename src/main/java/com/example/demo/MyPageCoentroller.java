package com.example.demo;


import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
public class MyPageCoentroller {

	@Autowired
	HttpSession session;

	@Autowired
	private KcalRepository kcalRepository;

	@RequestMapping("/myPage")
	public ModelAndView MyPage(
			ModelAndView mv) {

		Date d = Date.valueOf(LocalDate.now().toString());

		 SimpleDateFormat format = new SimpleDateFormat( "yyyy/MM/dd" );

		 String date = format.format(d);
		 Integer usercord = (Integer) session.getAttribute("code");

		List<Kcal> cal = kcalRepository.findByDateAndUsercode(d,usercord);


		int total=0;
		for (Kcal data :cal) {
			total +=data.getKcalall();
		}

			mv.addObject("list", cal);
			mv.addObject("date", d);
			mv.addObject("total", total);
			mv.setViewName("myPage");

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

		if(!time.equals("")&&!date.equals("")) {
		time+=":00";
		Time t=Time.valueOf(time);

		Date d=Date.valueOf(date);

		Integer usercord = (Integer) session.getAttribute("code");

		Kcal kcal=new Kcal(d,t,dishname,kcalall,usercord);

		// 登録処理date d
		kcalRepository.saveAndFlush(kcal);



		List<Kcal> cal = kcalRepository.findByDateAndUsercode(d,usercord);


		int total=0;
		for (Kcal data :cal) {
			total +=data.getKcalall();
		}

			mv.addObject("list", cal);
			mv.addObject("date", d);
			mv.addObject("total", total);
			mv.setViewName("myPage");


		mv.setViewName("myPage");

		}
		else {
			mv.addObject("message", "日時が未入力です。");
			mv.addObject("kcalall",kcalall);
			mv.addObject("dishname",dishname);
			mv.addObject("time",time);
			mv.addObject("date",date);
			mv.setViewName("confirm");
		}




		return mv;
	}

	@RequestMapping(value = "/day", method = RequestMethod.POST)
	public ModelAndView kakutei(
			@RequestParam("day") String date,
			ModelAndView mv) {

		if(!date.equals("")) {
		Date d=Date.valueOf(date);

		Integer usercord = (Integer) session.getAttribute("code");

		List<Kcal> cal = kcalRepository.findByDateAndUsercode(d,usercord);

		int total=0;
		for (Kcal data :cal) {
			total +=data.getKcalall();
		}

			mv.addObject("list", cal);
			mv.addObject("date", d);
			mv.addObject("total", total);
			mv.setViewName("myPage");
			mv.setViewName("myPage");
		}
		else {

			mv.addObject("message", "日付が未入力です。");

			mv.setViewName("myPage");
		}
		return mv;
	}

	@RequestMapping(value = "/sokutei", method = RequestMethod.POST)
	public ModelAndView sokutei(
			@RequestParam("taijyuu") String taijyuu,
			@RequestParam("total") double Total,
			@RequestParam("date") String date ,

			ModelAndView mv) {
		if(!taijyuu.equals("")) {
			int num = Integer.parseInt(taijyuu);

		Date d=Date.valueOf(date);

		Integer usercord = (Integer) session.getAttribute("code");

		List<Kcal> cal = kcalRepository.findByDateAndUsercode(d,usercord);


		int total=0;
		for (Kcal data :cal) {
			total +=data.getKcalall();
		}
		double kekka=0;

		kekka=Total/num;

		mv.addObject("list", cal);
		mv.addObject("date", d);
		mv.addObject("kekka",String.format("%.2f",kekka ));
		mv.addObject("total",total);
			mv.setViewName("myPage");
		}
		else {
			Date d=Date.valueOf(date);

			Integer usercord = (Integer) session.getAttribute("code");

			List<Kcal> cal = kcalRepository.findByDateAndUsercode(d,usercord);


			int total=0;
			for (Kcal data :cal) {
				total +=data.getKcalall();
			}

			mv.addObject("taijyuu", "体重を入力してください");

			mv.addObject("list", cal);
			mv.addObject("date", d);
			mv.addObject("total",total);
				mv.setViewName("myPage");
		}

		return mv;
	}

	@PostMapping("/delPage")
	public ModelAndView del(
			ModelAndView mv,
			@RequestParam("code")  int code,
			@RequestParam("date") String date

	) {
		kcalRepository.deleteById(code);

		Integer usercord = (Integer) session.getAttribute("code");

		Date d=Date.valueOf(date);

		List<Kcal> cal = kcalRepository.findByDateAndUsercode(d,usercord);

		int total=0;
		for (Kcal data :cal) {
			total +=data.getKcalall();
		}

			mv.addObject("list", cal);
			mv.addObject("date", d);
			mv.addObject("total", total);
			mv.setViewName("myPage");

		return mv;
	}
}
