package com.ephec.forms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ephec.beans.User;
import com.ephec.dao.DAOFollow;
import com.ephec.dao.DAOIFollow;
import com.ephec.dao.DAOIUser;
import com.ephec.dao.DAOUser;
import com.ephec.utility.UserUtility;

public class FollowUnfollowForm {

	private static final String KEYWORD_SESSION = "keywordSession";
	private static final String USER_SESSION = "userSession";
	
	private Map<String, String> erreurs = new HashMap<String, String>();
	
	private DAOUser daoUser;
	private DAOFollow daoFollow;

	public FollowUnfollowForm(DAOIUser daoIUser,DAOIFollow daoIFollow) {
		this.daoUser = (DAOUser)daoIUser;
		this.daoFollow = (DAOFollow)daoIFollow;
	}
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	public List<User> searchFollowingByAnyNameLike(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(USER_SESSION);
		String keyword = (String) session.getAttribute(KEYWORD_SESSION);
		List<User> userFollowingList = new ArrayList<User>();
		userFollowingList = daoUser.searchFollowingByAnyNameLike(keyword, user.getUserId());
		
		return userFollowingList;
	}
	
	public List<User> searchNotFollowingByAnyNameLike(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(USER_SESSION);
		String keyword = (String) session.getAttribute(KEYWORD_SESSION);
		List<User> userNotFollowingList = new ArrayList<User>();
		userNotFollowingList = daoUser.searchNotFollowingByAnyNameLike(keyword, user.getUserId());
		
		return userNotFollowingList;
	}
	
	public void createFollow(HttpServletRequest request)
	{
		String followingId = UserUtility.getFieldValue(request, "follow");
		
		if(followingId != null){
			HttpSession session = request.getSession();	
			User user = (User) session.getAttribute(USER_SESSION);	
			daoFollow.create(user, followingId);
			
		}
	}
	
	public void deleteFollow(HttpServletRequest request)
	{
		String followingId = UserUtility.getFieldValue(request, "stopfollow");
		if(followingId != null){
			HttpSession session = request.getSession();	
			User user = (User) session.getAttribute(USER_SESSION);	
			daoFollow.delete(user, followingId);
		}		
	}
	
	
	
	
}
