package com.barriaza.backendninja.converter;

import org.springframework.stereotype.Component;

import com.barriaza.backendninja.entity.Contact;
import com.barriaza.backendninja.model.ContactModel;

@Component("contactConverter")
public class ContactConverter {

	public Contact convertContactModelToContact(ContactModel _contactModel) {
		Contact contact = new Contact();
		contact.setId(_contactModel.getId());
		contact.setFirstname(_contactModel.getFirstname());
		contact.setLastname(_contactModel.getLastname());
		contact.setPhonenumber(_contactModel.getPhonenumber());
		contact.setCity(_contactModel.getCity());
		return contact;
	}

	public ContactModel convertContactToContactModel(Contact _contact) {
		ContactModel contactModel = new ContactModel();
		contactModel.setId(_contact.getId());
		contactModel.setFirstname(_contact.getFirstname());
		contactModel.setLastname(_contact.getLastname());
		contactModel.setPhonenumber(_contact.getPhonenumber());
		contactModel.setCity(_contact.getCity());
		return contactModel;
	}

}
