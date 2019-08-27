package com.springprojects.azure.httproxyauth;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 * 
 * @author GAF5KOR
 * Used for proxy authentication if this app is called behind proxy
 *
 */
public class ProxyAuthenticator  extends Authenticator {

	
	
	 private String userid, password;

	    public ProxyAuthenticator(String userid, String password) {
	        this.userid = userid;
	        this.password = password;
	    }

	    protected PasswordAuthentication getPasswordAuthentication() {
	        return new PasswordAuthentication(userid, password.toCharArray());
	    }
	    
	    
}
