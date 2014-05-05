package com.ephec.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ephec.beans.User;
import com.ephec.dao.DAOIUser;
import com.ephec.dao.DAOUser;
import com.ephec.utility.UserUtility;

public final class CreateAccountForm {

	private static final String USERNAME = "userName";
	private static final String FIRSTNAME = "firstName";
	private static final String LASTNAME = "lastName";
	private static final String EMAIL = "email";
	private static final String MAIL = "mail";
	private static final String PASSWORD = "password";
	private static final String CONFIRMATION = "confirmation";

	private String result;
	private Map<String, String> erreurs = new HashMap<String, String>();
	private DAOUser daoUser;
	private User user;

	public CreateAccountForm(DAOIUser daoIUser) {
		this.daoUser = (DAOUser) daoIUser;
	}

	public String getResult() {
		return result;
	}

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	// Retrieve the data entered by the user

	public User createUserAccount(HttpServletRequest request) {

		String userName = UserUtility.getFieldValue(request, USERNAME);
		String firstName = UserUtility.getFieldValue(request, FIRSTNAME);
		String lastName = UserUtility.getFieldValue(request, LASTNAME);
		String email = UserUtility.getFieldValue(request, EMAIL);
		String mail = UserUtility.getFieldValue(request, MAIL);
		String password = UserUtility.getFieldValue(request, PASSWORD);
		String confirmation = UserUtility.getFieldValue(request, CONFIRMATION);

		User user = new User();
		
		try {
			mailValidation(mail);
			
		}catch (Exception e)
		{
			setErreur(MAIL, e.getMessage());
			return null;
		}
		
		// Data validation
		try {
			userNameValidation(userName);
		} catch (Exception e) {
			setErreur(USERNAME, e.getMessage());
		}
		try {
			firstNameValidation(firstName);
		} catch (Exception e) {
			setErreur(FIRSTNAME, e.getMessage());
		}
		try {
			lastNameValidation(lastName);
		} catch (Exception e) {
			setErreur(LASTNAME, e.getMessage());
		}

		try {
			emailValidation(email);
		} catch (Exception e) {
			setErreur(EMAIL, e.getMessage());
		}

		try {
			passwordValidation(password, confirmation);
			password = UserUtility.sha256(password);
		} catch (Exception e) {
			setErreur(PASSWORD, e.getMessage());
		}

		user.setUserName(userName);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword(password);
		user.setProfileImage("0");

		if (erreurs.isEmpty()) {
			result = "Your account has been created successfully.";
			daoUser.create(user);

		} else {
			result = "Your account has not been created, please verify the entered information...";
		}

		return user;

	}

	private void mailValidation(String mail) throws Exception{
		if (mail != null ){
			throw new Exception("Fuck u bot!");
		}
	}

	/**
	 * Email validation
	 */
	private void emailValidation(String email) throws Exception {
		if (email != null && email.trim().length() != 0) {
			if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("Please enter a valide email.");
			}
		} else {
			throw new Exception("Please enter your email.");
		}

		user = daoUser.searchByEmail(email);

		if (user != null) {
			throw new Exception("This email is already related to an account.");
		}
	}

	/**
	 * Password validation
	 */
	private void passwordValidation(String password, String confirmation)
			throws Exception {
		if (password != null && password.trim().length() != 0
				&& confirmation != null && confirmation.trim().length() != 0) {
			if (!password.equals(confirmation)) {
				throw new Exception("Password doesn't match confirmation.");
			} else if (password.trim().length() < 3) {
				throw new Exception(
						"Passwords must contain at least 3 characters.");
			}
		} else {
			throw new Exception("Please enter and confirm your password.");
		}
	}

	/**
	 * User name validation
	 */
	private void userNameValidation(String userName) throws Exception {
		if (userName != null && userName.trim().length() < 3) {
			throw new Exception(
					"The username must contain at least 3 characters.");
		}
		user = daoUser.searchByUserName(userName);

		if (user != null) {
			throw new Exception("The user name already exist.");
		}
	}

	private void firstNameValidation(String firstName) throws Exception {
		if (firstName != null && firstName.trim().length() < 3) {
			throw new Exception(
					"The username must contain at least 3 characters.");
		}
	}

	private void lastNameValidation(String lastName) throws Exception {
		if (lastName != null && lastName.trim().length() < 3) {
			throw new Exception(
					"The username must contain at least 3 characters.");
		}
	}
	


	/**
	 * Ajoute un message correspondant au champ spécifié à la map des erreurs.
	 */
	private void setErreur(String champ, String message) {
		erreurs.put(champ, message);
	}

}
