package br.quixada.ufc.web.ninjatypr.service;

import java.math.BigInteger;
import java.security.MessageDigest;

import br.quixada.ufc.web.ninjatypr.model.User;
import br.quixada.ufc.web.ninjatypr.model.dao.UserDAO;
public class Security {
	public static String encrypt(String password) throws Exception {		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(password.getBytes("latin1"));
			return new BigInteger(1, bytes).toString(32);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	public static boolean authenticate(UserDAO dao, User user) throws Exception {
		String ps = encrypt(user.getPassword());
		User user2 = dao.findByUsername(user.getUsername());
		if (user2 != null) {
			return user2.getPassword().equals(ps);
		}
		return false;
	}
}
