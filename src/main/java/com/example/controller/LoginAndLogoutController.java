package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//import com.example.domain.User;
import com.example.form.LoginForm;
//import com.example.service.LoginAndLogoutService;

import jakarta.servlet.http.HttpSession;

/**
 * ログイン・ログアウトを行うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Controller
@RequestMapping("/loginAndLogout")

public class LoginAndLogoutController {
//	@Autowired
//	private LoginAndLogoutService service;

	@Autowired
	private HttpSession session;

	@GetMapping("")
	public String toLogin(LoginForm form, Model model, String error) {
		if (error != null) {
			model.addAttribute("loginErrorMessage", "メールアドレスまたはパスワードが不正です。");
		}
//		String key,
//		model.addAttribute("key", key);
		return "login";

	}

//	/**
//	 * ログインを行う.
//	 * 
//	 * @param form  ログイン情報を受け取るフォーム
//	 * @param model モデル
//	 * @return 該当ユーザーがある場合：商品一覧画面。該当ユーザーがない場合：ログイン画面(エラーメッセージ表示)
//	 */
//	@PostMapping("/login")
//	public String login(LoginForm form, Model model) {

//
//		Integer tentativeUserId = session.getId().hashCode();
//		User user = service.login(form, tentativeUserId);
//
//		if (user == null) {
//			model.addAttribute("loginErrorMessage", "メールアドレスまたはパスワードが不正です。");
//			model.addAttribute("Key",form.getKey());
//			return toLogin(form, form.getKey(), model);
//		}
//
//		session.setAttribute("user", user);
//
//		Integer orderId = service.pickUpOrderId(tentativeUserId);
//
//		
//		if ("toOrderConfirm".equals(form.getKey())) {
//			return "redirect:/orderConfirm" + "?orderId=" + orderId;
//		} else if (form.getKey() == null || form.getKey() == "") {
//			return "redirect:/showList";
//
//		} else {
//			throw new RuntimeException();
//		}
//
//	}

	@GetMapping("/logout")
	public String logout() {
		session.removeAttribute(null);
		return "redirect:/showList";
	}
}
