package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.LoginUser;
import com.example.domain.User;
import com.example.service.LoginAndLogoutService;

import jakarta.servlet.http.HttpSession;

/**
 * カートを表示するコントローラ.
 * 
 * @author sugaharatakamasa
 *
 */
@Controller
@RequestMapping("/toShowList")
public class ToShowListController {

	@Autowired
	private HttpSession session;
	@Autowired
	private LoginAndLogoutService loginAndLogoutService;

	/**
	 * カート内を表示する.
	 * @param loginUser 
	 * @return カート情報
	 */
	@GetMapping("")
	public String toShowList(@AuthenticationPrincipal LoginUser loginUser) {


		User user = loginUser.getUser();
		Integer tentativeUserId = (Integer) session.getAttribute("userId");
		Integer tentativeOrderId = loginAndLogoutService.pickUpOrderId(tentativeUserId);
		Integer orderId = loginAndLogoutService.pickUpOrderId(user.getId());
		if (tentativeOrderId != orderId) {

			if (orderId != null) {
				loginAndLogoutService.updateOrderItemId(tentativeOrderId, orderId);
				loginAndLogoutService.deleteOrderByOrderId(tentativeOrderId);
			} else {
				loginAndLogoutService.updateUserId(tentativeUserId, user.getId());
				orderId = tentativeOrderId;
			}
		}
		
		return "redirect:/showList";

	}
}
