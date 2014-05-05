package com.ephec.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ephec.beans.User;
import com.ephec.dao.DAOIUser;
import com.ephec.dao.DAOUser;
import com.ephec.utility.UserUtility;

public class LoginForm {

	private static final String USERNAME = "userName";
	private static final String PASSWORD = "password";

	private Map<String, String> erreurs = new HashMap<String, String>();
	
	private DAOUser daoUser;
	
	private String result;
	
	public String getResult() {
		return result;
	}
	
	public LoginForm(DAOIUser daoIUser) {
		this.daoUser = (DAOUser) daoIUser;
	}	

	public Map<String, String> getErreurs() {
		return erreurs;
	}

	public User loginValidation(HttpServletRequest request) {

		String userName = UserUtility.getFieldValue(request, USERNAME);
		String password = UserUtility.getFieldValue(request, PASSWORD);
		password = UserUtility.sha256(password);
		
		User user = null;
		user = daoUser.searchByUserName(userName);

		if (user == null) {
			erreurs.put(USERNAME, "Invalid username.");
		} else {
			
			if (!(user.getPassword().equals(password))) {

				erreurs.put(PASSWORD, "Invalid password.");
			}
			else result = "Login SuccessFull";
		}

		return user;
	}
	
}
