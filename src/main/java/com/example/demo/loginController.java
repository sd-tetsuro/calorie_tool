package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class loginController {



	@RequestMapping("/login")
	public String log() {
		return "log";
	}

	@RequestMapping(value = "/log", method = RequestMethod.POST)
	public ModelAndView doLogin(
			@RequestParam("email") String email,
			@RequestParam("password") String password,
			ModelAndView mv) {
		// 名前が空の場合にエラーとする
		if (email == null || email.length() == 0) {
			mv.addObject("message", "メールアドレスを入力してください");
			mv.setViewName("log");
			return mv;
		}else if (password == null || password.length() == 0) {
			mv.addObject("message", "パスワードを入力してください");
			mv.setViewName("log");
			return mv;
		}else if (password == null || password.length() == 0&&email == null || email.length() == 0) {
		mv.addObject("message", "情報を入力してください");
		mv.setViewName("log");
		return mv;
	}

			mv.setViewName("kcalCal");
			return mv;
	}

	//マイメニュー詳細画面へ移動
		@RequestMapping("/accountPage")
		public ModelAndView MyPage(
				ModelAndView mv) {
			mv.setViewName("accountPage");

			return mv;
		}

		//マイメニュー（EATボタン押下）
		@RequestMapping(value = "/regi", method = RequestMethod.POST)
		public ModelAndView confirm(
				ModelAndView mv) {

				mv.setViewName("login");
				return mv;
		}
		//アカウント新規作成
		@PostMapping("/newUser")
		public ModelAndView usersadd(
				ModelAndView mv,
				@RequestParam(name = "name") String name,
				@RequestParam(name = "email") String email,
				@RequestParam(name = "password") String pass) {

			//登録するユーザのエンティティのインスタンスを生成
			User user = new User(name, email, pass);

			//UserエンティティをUserテーブルに登録

			UserRepository.saveAndFlush(user);

			return users(mv);
		}


}
