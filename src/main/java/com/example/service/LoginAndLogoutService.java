package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Order;
import com.example.domain.User;
import com.example.form.LoginForm;
import com.example.repository.OrderItemRepository;
import com.example.repository.OrderRepository;
import com.example.repository.UserRepository;

/**
 * ログイン・ログアウトに関する業務を行うクラス.
 * 
 * @author sugaharatakamasa
 *
 */
@Service
@Transactional
public class LoginAndLogoutService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * ログインを行う.
	 * @param form　フォーム　
	 * @param tentativeUserId　仮ユーザID
	 * @return　ユーザ情報
	 */
	public User login(LoginForm form, Integer tentativeUserId) {
		User user = userRepository.findByEmail(form.getEmail());
		if (user == null) {
			return null;
		}

		// ハッシュ化されたパスワードとの整合確認
		if (passwordEncoder.matches(form.getPassword(), user.getPassword())) {

			return user;

		} else {
			return null;
		}
	}

	/**
	 * ユーザIDから注文情報を取得する.
	 * @param userId ユーザID
	 * @return　注文情報
	 */
	public Integer pickUpOrderId(Integer userId) {
		Integer status = 0;
		Order order = orderRepository.findByUserIdAndStatus(userId, status);
		if (order == null) {
			return null;
		} else {
			return order.getId();
		}
	}

	/**
	 * 仮ユーザIDとユーザIDを統合する.
	 * @param tentativeOrderId
	 * @param orderId
	 */
	public void updateOrderItemId(Integer tentativeOrderId, Integer orderId) {
		orderItemRepository.updateOrderId(tentativeOrderId, orderId);

	}

	/**
	 * 仮ユーザとユーザIDを統合する.
	 * @param tentativeUserId 仮ユーザID
	 * @param userId　ユーザID
	 */
	public void updateUserId(Integer tentativeUserId, Integer userId) {
		orderRepository.updateUserId(tentativeUserId, userId);
	}

	/**
	 * 注文情報を削除する.
	 * @param orderId 注文ID
	 */
	public void deleteOrderByOrderId(Integer orderId) {
		orderRepository.deleteByOrderId(orderId);
	}

}
