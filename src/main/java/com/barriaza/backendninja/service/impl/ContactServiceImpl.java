package com.barriaza.backendninja.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.barriaza.backendninja.converter.ContactConverter;
import com.barriaza.backendninja.entity.Contact;
import com.barriaza.backendninja.model.ContactModel;
import com.barriaza.backendninja.repository.ContactRepository;
import com.barriaza.backendninja.service.ContactService;

@Service("contactServiceImpl")
public class ContactServiceImpl implements ContactService {

	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;

	@Autowired
	@Qualifier("contactConverter")
	private ContactConverter contactConverter;

	@Override
	public ContactModel saveContact(ContactModel _contactModel) {
		Contact contact = contactRepository.save(contactConverter.convertContactModelToContact(_contactModel));
		return contactConverter.convertContactToContactModel(contact);
	}

	@Override
	public List<ContactModel> getAllContacts() {
		List<Contact> listContacts = contactRepository.findAll();
		List<ContactModel> listContactsModel = new ArrayList<ContactModel>();
		for (Contact contact : listContacts) {
			listContactsModel.add(contactConverter.convertContactToContactModel(contact));
		}
		return listContactsModel;
	}

	@Override
	public Contact findContactById(int _id) {
		return contactRepository.findById(_id);
	}

	@Override
	public ContactModel findContactModelById(int _id) {
		return contactConverter.convertContactToContactModel(findContactById(_id));
	}

	@Override
	public void deleteContact(int _id) {
		Contact contact = findContactById(_id);
		if (null != contact) {
			contactRepository.delete(contact);
		}
	}

}
