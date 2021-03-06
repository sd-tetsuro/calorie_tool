package com.example.demo;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

					 Integer usercord = (Integer) session.getAttribute("code");

						List<Kcal> cal = kcalRepository.findByDateAndUsercode(d,usercord);

						Integer userid = (Integer) session.getAttribute("code");

						int code1 = userid;
						Optional<user> record = userRepository.findById(code1);

						user user1 = record.get();

						Integer weight=user.getWeight();
						Integer height=user.getHeight();
						Integer age=user.getAge();
						Integer gender=user.getGender();

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

			session.setAttribute("user", user);

				//ログイン成功
				String name = user.getName();
				Integer code =user.getCode();

				mv.addObject("user", user1);
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
