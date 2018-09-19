package com.barriaza.backendninja.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.barriaza.backendninja.constant.ViewConstant;

@Controller("loginController")
public class LoginController {

	private static final Log LOG = LogFactory.getLog(LoginController.class);

	@GetMapping("/login")
	public String showLoginForm(Model model, @RequestParam(name = "error", required = false) String _error,
			@RequestParam(name = "logout", required = false) String _logout) {
		LOG.info("METHOD: showLoginForm() -- PARAMS: error=" + _error + ", logout=" + _logout);
		model.addAttribute("error", _error);
		model.addAttribute("logout", _logout);
		LOG.info("Return to login view");
		return ViewConstant.LOGIN;
	}

	@GetMapping({ "/loginsuccess", "/" })
	public String loginCheck() {
		LOG.info("METHOD: loginCheck()");
		LOG.info("Return to contacts view");
		return "redirect:/contacts/showcontacts";
	}

}