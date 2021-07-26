package com.example.demo;


import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

	@Autowired
	private userRepository userRepository;

	@RequestMapping("/myPage")
	public ModelAndView MyPage(
			ModelAndView mv) {

		Date d = Date.valueOf(LocalDate.now().toString());

		 SimpleDateFormat format = new SimpleDateFormat( "yyyy/MM/dd" );

		 String date = format.format(d);
		 Integer usercord = (Integer) session.getAttribute("code");

		List<Kcal> cal = kcalRepository.findByDateAndUsercode(d,usercord);


		Integer userid = (Integer) session.getAttribute("code");

		int code = userid;
		Optional<user> record = userRepository.findById(code);

		user user = record.get();



		int total=0;
		for (Kcal data :cal) {
			total +=data.getKcalall();
		}
			mv.addObject("user", user);
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


		Integer userid = (Integer) session.getAttribute("code");

		int code = userid;
		Optional<user> record = userRepository.findById(code);

		user user = record.get();



		int total=0;
		for (Kcal data :cal) {
			total +=data.getKcalall();
		}
			mv.addObject("user", user);
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

		Integer userid = (Integer) session.getAttribute("code");

		int code = userid;
		Optional<user> record = userRepository.findById(code);

		user user = record.get();



		int total=0;
		for (Kcal data :cal) {
			total +=data.getKcalall();
		}

			mv.addObject("user", user);
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
			@RequestParam("weight") int weight,
			@RequestParam("height") int height,
			@RequestParam("age") int age,
			@RequestParam("gender") int gender,
			@RequestParam("total") double Total,
			@RequestParam("date") String date ,

			ModelAndView mv) {



		Date d=Date.valueOf(date);

		Integer usercord = (Integer) session.getAttribute("code");

		List<Kcal> cal = kcalRepository.findByDateAndUsercode(d,usercord);


		Integer userid = (Integer) session.getAttribute("code");

		int code = userid;
		Optional<user> record = userRepository.findById(code);

		user user = record.get();



		int total=0;
		for (Kcal data :cal) {
			total +=data.getKcalall();
		}

		//男性： 13.397×体重kg＋4.799×身長cm−5.677×年齢+88.362
		//女性： 9.247×体重kg＋3.098×身長cm−4.33×年齢+447.593
if(gender==1) {
	double kekka=13.397*weight+4.799*height-5.677*age+88.362;
	mv.addObject("kekka",String.format("%.2f",kekka ));
}
else if(gender==2) {
	double kekka=9.247*weight+3.098*height-5.677*age+447.563;
	mv.addObject("kekka",String.format("%.2f",kekka ));
}

		mv.addObject("user", user);
		mv.addObject("list", cal);
		mv.addObject("date", d);
		mv.addObject("total",total);
			mv.setViewName("myPage");

		return mv;
	}

	@PostMapping("/delPage")
	public ModelAndView del(
			ModelAndView mv,
			@RequestParam("code")  int code1,
			@RequestParam("date") String date

	) {
		kcalRepository.deleteById(code1);

		Integer usercord = (Integer) session.getAttribute("code");

		Date d=Date.valueOf(date);

		List<Kcal> cal = kcalRepository.findByDateAndUsercode(d,usercord);

		Integer userid = (Integer) session.getAttribute("code");

		int code = userid;
		Optional<user> record = userRepository.findById(code);

		user user = record.get();



		int total=0;
		for (Kcal data :cal) {
			total +=data.getKcalall();
		}
			mv.addObject("user", user);
			mv.addObject("list", cal);
			mv.addObject("date", d);
			mv.addObject("total", total);
			mv.setViewName("myPage");

		return mv;
	}
}

