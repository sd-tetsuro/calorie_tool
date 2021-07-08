package com.example.demo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class loginController {

	@Autowired
	private HttpSession session;

	@RequestMapping("/login")
	public String log() {
		return "log";
	}

	@RequestMapping(value = "/log", method = RequestMethod.POST)
	public ModelAndView doLogin(
			@RequestParam(name="email",defaultValue="") String email,
			@RequestParam(name="password",defaultValue="") String password,
			ModelAndView mv) {

		// 名前が空の場合にエラーとする
		if (email.equals("") || password.equals("")) {
			mv.addObject("message", "未入力の項目があります。");
			mv.setViewName("log");
		} else {
			user user = (user)session.getAttribute("user");

			String _email = user.getEmail();
			String _password = user.getPassword();

			if (email.equals(_email) && password.equals(_password)) {
				//ログイン成功
				String name = user.getName();

				session.setAttribute("login", name);

				mv.addObject("login", name);
				mv.addObject("user", user);

				mv.setViewName("kcalCal");
			} else {
				mv.addObject("message", "ユーザIDとパスワードが一致しませんでした。");
				mv.setViewName("log");
			}
		}
		return mv;
		}


	//マイメニュー詳細画面へ移動
		@RequestMapping("/accountPage")
		public ModelAndView MyPage(
				ModelAndView mv) {
			mv.setViewName("accountPage");

			return mv;
		}

		//アカウント作成ボタン
		@RequestMapping(value="/newUser",method = RequestMethod.POST)
		public ModelAndView newUser(
				@RequestParam("name") String name,
				@RequestParam("email") String email,
				@RequestParam("password") String password,
				ModelAndView mv) {

			if (!name.equals("") && !email.equals("") && !password.equals("")) {

				//ユーザーインスタンスの生成
				user user = new user(name, email, password);

				session.setAttribute("user", user);

				mv.addObject("message", "登録が完了しました。");

				mv.setViewName("log");
			} else {
				mv.addObject("message", "未入力の項目があります。");
				mv.setViewName("accountPage");
			}

			mv.setViewName("accountpage");

			return mv;
		}



		//マイメニュー（EATボタン押下）
		@RequestMapping(value = "/regi", method = RequestMethod.POST)
		public ModelAndView confirm(
				ModelAndView mv) {

				mv.setViewName("login");
				return mv;
		}

}
