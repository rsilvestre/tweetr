package com.ephec.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ephec.beans.User;
import com.ephec.dao.DAOFollow;
import com.ephec.dao.DAOIFollow;
import com.ephec.dao.DAOITweet;
import com.ephec.dao.DAOIUser;
import com.ephec.dao.DAOTweet;
import com.ephec.dao.DAOUser;

public class DeleteAccountForm {
	private String result;
	private Map<String, String> erreurs = new HashMap<String, String>();
	private DAOUser daoUser;
	private DAOTweet daoTweet;
	private DAOFollow daoFollow;
	private User user;
	private final String USER_SESSION ="userSession" ;
	
	public DeleteAccountForm(DAOIUser daoIUser, DAOIFollow daoIFollow, DAOITweet daoITweet) {
		super();
		this.daoUser = (DAOUser) daoIUser;
		this.daoTweet = (DAOTweet) daoITweet;
		this.daoFollow = (DAOFollow) daoIFollow;
	}
	
	
	public void deleteAccount(HttpServletRequest request){
		HttpSession session = request.getSession();
		user = (User) session.getAttribute(USER_SESSION);
		int userId = user.getUserId();
		
		daoTweet.deleteReTweet(userId);
		daoTweet.deleteTweet(userId);
		daoFollow.deleteUserFollower(userId);
		daoFollow.deleteUserFollowing(userId);
		daoUser.delete(userId);
		
	}
	
	
	
	
}
