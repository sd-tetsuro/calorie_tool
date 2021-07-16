package com.example.demo;

import java.util.List;

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
			@RequestParam("menu") String menu,
			@RequestParam( name="kcalall",defaultValue="0") Integer kcalall,
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
			}else {
				mv.addObject("message", "未入力の項目があります。");

				mv.setViewName("custom");

			}
		return mv;
	}


}