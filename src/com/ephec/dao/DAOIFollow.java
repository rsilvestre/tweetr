package com.ephec.dao;

import com.ephec.beans.User;
import com.ephec.exceptions.DAOException;

public interface DAOIFollow {

	void create( User user, String followingId ) throws DAOException;

	void delete(User user,String followingId) throws DAOException;
	
	void deleteUserFollowing(int userId)throws DAOException;
	
	void deleteUserFollower(int userId)throws DAOException;
}
