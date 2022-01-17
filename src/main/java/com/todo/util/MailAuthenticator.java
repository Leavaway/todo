package com.todo.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 发件人账号密码
 * @author zhangdi
 *
 */
public class MailAuthenticator extends Authenticator {

    public static String USERNAME = "15757610036@163.com";
    public static String PASSWORD = "TLTWNSKXWDZWBFSJ";

    public MailAuthenticator() {
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(USERNAME, PASSWORD);
    }

}