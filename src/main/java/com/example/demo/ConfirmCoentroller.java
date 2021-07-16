package com.example.demo;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConfirmCoentroller {

	@Autowired
	HttpSession session;

	@Autowired
	private SelectedFoodRepository selectedFoodRepository;

	@Autowired
	private mylistsRepository mylistsRepository;

	@Autowired
	private menuRepository menuRepository;


	//マイメニュー（EATボタン押下）
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public ModelAndView Confirm(
			@RequestParam("dishname") String dishname,
			@RequestParam("kcalall") Integer kcalall,
			ModelAndView mv) {

		List<menu> menu = menuRepository.findByDishnameAndKcalall(dishname,kcalall);


		//Time t=Time.valueOf(LocalTime.now().toString());

		  //SimpleDateFormat format1 = new SimpleDateFormat("h/m");

		Date d = Date.valueOf(LocalDate.now().toString());

		 SimpleDateFormat format = new SimpleDateFormat( "yyyy/MM/dd" );

		 String date = format.format(d);

		 //String time = format1.format(t);

		//mv.addObject("time",t);
		mv.addObject("date", d);
		mv.addObject("dishname", dishname);
		mv.addObject("kcalall", kcalall);
		mv.setViewName("confirm");
		return mv;
	}


	//カスタム（EATボタン押下）
	@RequestMapping(value = "/custom/confirm", method = RequestMethod.POST)
	public ModelAndView customConfirm(
			ModelAndView mv) {

		mv.setViewName("confirm");
		return mv;
	}

}