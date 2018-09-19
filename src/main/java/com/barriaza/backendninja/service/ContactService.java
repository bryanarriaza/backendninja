package com.barriaza.backendninja.service;

import java.util.List;

import com.barriaza.backendninja.entity.Contact;
import com.barriaza.backendninja.model.ContactModel;

public interface ContactService {

	public abstract ContactModel saveContact(ContactModel _contactModel);

	public abstract List<ContactModel> getAllContacts();

	public abstract Contact findContactById(int _id);

	public abstract ContactModel findContactModelById(int _id);

	public abstract void deleteContact(int _id);

}
