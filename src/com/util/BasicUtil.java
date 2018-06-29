package com.util;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class BasicUtil {
	
	/**
	 * Null check on an object
	 * 
	 * @param obj
	 * @return true if object null
	 */
	public static boolean isNull(Object obj) {
		return obj == null ? true : false;
	}

	/**
	 * Empty check on the object
	 * 
	 * @param obj
	 * @return true if object empty
	 */
	public static boolean isEmpty(Object obj) {
		return "".equals(obj) ? true : false;
	}
	
	/**
	 * Generate random access token with userName of the user and using
	 * JwtBuilder api
	 * 
	 * @param username
	 * @return token
	 */
	public static String createAccessToken(String userName) {

		long ttlMillis = 108000;
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		Key apiKey = MacProvider.generateKey(signatureAlgorithm);

		JwtBuilder builder = Jwts.builder().setId(userName).setIssuedAt(now).setSubject("AUTH-TOKEN")
				.setIssuer("MyApp").signWith(signatureAlgorithm, apiKey);

		if (ttlMillis >= 0) {
			long expMillis = nowMillis + ttlMillis;
			Date exp = new Date(expMillis);
			builder.setExpiration(exp);
		}

		return builder.compact();
	}
	
	/**
	 * Generate refresh token
	 * 
	 * @return refresh token (String)
	 */
	public static String createRefreshToken() {
		UUID uuid = UUID.randomUUID();
		String refToken = uuid.toString();
		return refToken;

	}
}
