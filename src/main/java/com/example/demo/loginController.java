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
public class loginController {

	@Autowired
	private HttpSession session;

	@Autowired
	userRepository userRepository;

	@Autowired
	KcalRepository kcalRepository;

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

			List<user> users = userRepository.findByEmailAndPassword(email,password);
	if (users.size() > 0) {
				// メールアドレスとパスワードが存在したらログインOK
				// リストの1件目をログインユーザとして取得する
				user user = users.get(0);
Date d = Date.valueOf(LocalDate.now().toString());

					 SimpleDateFormat format = new SimpleDateFormat( "yyyy/MM/dd" );


					 String date = format.format(d);


						List<Kcal> cal = kcalRepository.findByDate(d);


						int total=0;
						for (Kcal data :cal) {
							total +=data.getKcalall();
						}

			session.setAttribute("user", user);

				//ログイン成功
				String name = user.getName();
				Integer code =user.getCode();


				session.setAttribute("login", name);
				session.setAttribute("code", code);
				mv.addObject("login", name);
				mv.addObject("code", code);
				mv.setViewName("myPage");
				mv.addObject("list", cal);
				mv.addObject("date", d);
				mv.addObject("total", total);






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

				userRepository.saveAndFlush(user);


				mv.addObject("message", "登録が完了しました。");

				mv.setViewName("log");
			} else {
				mv.addObject("message", "未入力の項目があります。");
				mv.setViewName("accountPage");
			}

			mv.setViewName("log");

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
