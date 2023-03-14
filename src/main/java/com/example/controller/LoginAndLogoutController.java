package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.LoginForm;


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

	@Autowired
	private HttpSession session;

	/**
	 * 入力値チェックを行う.
	 * @param form　ログインフォーム
	 * @param model　モデル
	 * @param error　エラー
	 * @return　ログインページ
	 */
	@GetMapping("")

	public String toLogin(LoginForm form, Model model, String error) {

		if (error != null) {
			model.addAttribute("loginErrorMessage", "メールアドレスまたはパスワードが不正です。");
			
		}
		model.addAttribute("sessionId", session.getId());
		return "login";
	}

}
