package com.barriaza.backendninja.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.barriaza.backendninja.model.ContactModel;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/rest")
public class RestController {

	@GetMapping("/checkrest")
	public ResponseEntity<ContactModel> checkRest() {
		ContactModel contactModel = new ContactModel(1, "Juan", "Perez", 2132323265, "Gt");
		return new ResponseEntity<ContactModel>(contactModel, HttpStatus.OK);
	}

}
