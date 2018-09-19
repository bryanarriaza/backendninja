package com.barriaza.backendninja.model;

public class ContactModel {

	private int id;
	private String firstname;
	private String lastname;
	private int phonenumber;
	private String city;

	public ContactModel() {
		super();
	}

	public ContactModel(int id, String firstname, String lastname, int phonenumber, String city) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phonenumber = phonenumber;
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(int phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Contact [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", phonenumber="
				+ phonenumber + ", city=" + city + "]";
	}

}
