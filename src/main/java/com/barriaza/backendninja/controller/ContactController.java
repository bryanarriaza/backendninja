package com.barriaza.backendninja.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.barriaza.backendninja.constant.ViewConstant;
import com.barriaza.backendninja.model.ContactModel;
import com.barriaza.backendninja.service.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {

	private static final Log LOG = LogFactory.getLog(ContactController.class);

	@Autowired
	@Qualifier("contactServiceImpl")
	private ContactService contactService;

	@GetMapping("/")
	public String redirectToShowContacts() {
		return "redirect:/contacts/showcontacts";
	}

	@GetMapping("/cancel")
	public String cancelContactForm() {
		return "redirect:/contacts/showcontacts";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	@GetMapping("/contactform")
	public String redirectContactForm(@RequestParam(name = "id", required = false) int _id, Model _model) {
		ContactModel contact = new ContactModel();
		if (_id != 0) {
			contact = contactService.findContactModelById(_id);
		}
		_model.addAttribute("contactModel", contact);
		return ViewConstant.CONTACT_FORM;
	}

	@PostMapping("/addcontact")
	public String saveContact(@ModelAttribute(name = "contactModel") ContactModel _contactModel, Model _model) {
		LOG.info("METHOD: saveContact() -- PARAMS: _contactModel=" + _contactModel.toString());
		if (null != contactService.saveContact(_contactModel)) {
			_model.addAttribute("result", 1);
		} else {
			_model.addAttribute("result", 0);
		}
		return "redirect:/contacts/showcontacts";
	}

	@GetMapping("/showcontacts")
	public ModelAndView showContacts() {
		ModelAndView mav = new ModelAndView(ViewConstant.CONTACTS);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		mav.addObject("username", user.getUsername());
		mav.addObject("contacts", contactService.getAllContacts());
		return mav;
	}

	@GetMapping("/removecontact")
	public ModelAndView removeContact(@RequestParam(name = "id", required = true) int _id) {
		contactService.deleteContact(_id);
		return showContacts();
	}

}
