package com.revature.service;

import reim.model.Authorization;
import reim.model.dao.AuthorizationDao;
import reim.model.dao.impl.AuthorizationDaoImpl;

public class AuthorizationService {
	
	AuthorizationDao aud = new AuthorizationDaoImpl();
	
	public Authorization getAuthorizationById(int id) {
		return aud.getAuthorizationById(id);
	}

}
