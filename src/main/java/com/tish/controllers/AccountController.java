package com.tish.controllers;

import com.tish.models.Account;
import com.tish.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Controller
@RequestMapping(path = {"/account"})
public class AccountController {

	private final AccountService accountService;

	public AccountController(@Autowired AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping("/register")
	public String openRegisterPage(Model model) {
		model.addAttribute("account", new Account());
		return "register-page";
	}

	@PostMapping("/register")
	public String register(@ModelAttribute("account") Account account, BindingResult result, Model model) {

		Account existingAccount = accountService.checkIfAccountExists(account.getLogin());
		if (existingAccount != null && existingAccount.getLogin() != null && !existingAccount.getLogin().isEmpty()) {
			result.rejectValue("login", null,
					"There is already an account registered with the same email");
		}

		if (result.hasErrors()) {
			model.addAttribute("account", account);
			return "register-page";
		}

		accountService.registerAccount(account);

		return "redirect:/account/login";
	}

	@GetMapping("/login")
	public String openLoginPage(Model model) {
		model.addAttribute("account", new Account());
		return "login-page";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute("account") Account account, BindingResult result, Model model) {

		Account existingAccount = accountService.checkIfAccountExists(account.getLogin());
		if (existingAccount == null) {
			result.rejectValue("login", null,
					"This account is not exists or your login is wrong");
		} else if (!accountService.checkPassword(account.getPassword(), existingAccount.getPassword())) {
			result.rejectValue("password", null,
					"Wrong password");
		}

		if (result.hasErrors()) {
			model.addAttribute("account", account);
			return "login-page";
		}

		accountService.loginByAccount(existingAccount);

		return "redirect:/conversion";
	}

	@GetMapping("/logout")
	public String logout(@RequestParam String login) {

		accountService.logoutByEmail(login);

		return "redirect:/welcome/index";
	}

	@GetMapping("/profile")
	public String openAccountPage(Model model, @RequestParam String login) {
		Account account = accountService.checkIfAccountExists(login);
		model.addAttribute("account", account);

		return "account-page";
	}


}
