package com.ghm.giftcardfleamarket.user.service;

import org.springframework.stereotype.Service;

import com.ghm.giftcardfleamarket.common.utils.constants.SessionNames;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SessionLoginService implements LoginService {

	private final HttpSession session;

	@Override
	public void login(long id) {
		session.setAttribute(SessionNames.LOGIN_USER, id);
	}

	@Override
	public void logout() {
		session.removeAttribute(SessionNames.LOGIN_USER);
	}

	@Override
	public Long getLoginUser() {
		return (Long)session.getAttribute(SessionNames.LOGIN_USER);
	}
}
