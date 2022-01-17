package com.todo.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class GetSqlSession {
    public static SqlSession createSqlSession(){
        SqlSessionFactory sqlSessionFactory = null;
        InputStream inputStream = null;
        SqlSession sqlSession = null;

        try{
            String resource = "mybatis-config.xml";
            inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
            return sqlSession;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        EmailService operation = new EmailService();
        String user = "15757610036@163.com";
        String password = "TLTWNSKXWDZWBFSJ";
        String host = "smtp.163.com";
        String from = "15757610036@163.com";
        String to = "505871488@qq.com";// 收件人
        String subject = "123";
        //邮箱内容
        StringBuffer sb = new StringBuffer();
        String yzm = "111122";
        sb.append("<!DOCTYPE>"+"<div bgcolor='#f1fcfa'   style='border:1px solid #d9f4ee; font-size:14px; line-height:22px; color:#005aa0;padding-left:1px;padding-top:5px;   padding-bottom:5px;'><span style='font-weight:bold;'>温馨提示：</span>"
                + "<div style='width:950px;font-family:arial;'>欢迎使用NET微活动，您的注册码为：<br/><h2 style='color:green'>"+yzm+"</h2><br/>本邮件由系统自动发出，请勿回复。<br/>感谢您的使用。<br/>Wedo 运营组</div>"
                +"</div>");
        try {
            String res = operation.sendMail(user, password, host, from, to,
                    subject, sb.toString(),new Date());
            System.out.println(res);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
