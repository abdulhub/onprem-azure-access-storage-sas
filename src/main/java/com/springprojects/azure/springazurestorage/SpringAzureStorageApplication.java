package com.springprojects.azure.springazurestorage;

import java.net.Authenticator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springprojects.azure.httproxyauth.ProxyAuthenticator;

@SpringBootApplication
public class SpringAzureStorageApplication {

	@Value("${USERID:noproxyuser}")
	private String USERID;

	@Value("${PASSWORD:noproxypassword}")
	private String PASSWORD;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringAzureStorageApplication.class, args);
	}
	 @Bean
	 CommandLineRunner initParam() {
		 return (args) -> {
	            
			   // Authenticator.setDefault(new ProxyAuthenticator(USERID, PASSWORD));
				System.setProperty("http.proxyHost", "httpserver");
				System.setProperty("http.proxyPort", "8080");
				System.setProperty("https.proxyHost", "httpsserver");
				System.setProperty("https.proxyPort", "8080"); 
				System.setProperty("http.proxyUser", USERID);
		                System.setProperty("http.proxyPassword", PASSWORD);
				System.setProperty("https.proxyUser", USERID);
		                System.setProperty("https.proxyPassword", PASSWORD);
		 
	 };
	 }
}
