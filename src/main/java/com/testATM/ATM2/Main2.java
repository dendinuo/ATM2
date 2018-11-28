package com.testATM.ATM2;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

/**

 */
public class Main2 
{	
    public static void main( String[] args ) throws IOException, SQLException
    {   	
    	System.out.println("欢迎您使用本系统！");
    	ATMaction atm = new ATMaction();
    	UserInput user = new UserInput();	
    	String login_name=null;
    	String doing_pass=null;
    	int num=1;
    	
    	while( login_name==null ) {
    		login_name=atm.user_check( user.Input("in_name") , user.Input("in_pwd") );
    		if(num>=3) {
    			System.out.println("\n 错误次数过多，退出！\n");
    			break;
    		}
    		if(login_name==null) {System.out.println("\n 用户名或密码错误，请重新输入！\n");}
    		num++;
    	}
    	if(login_name!=null) {
    		System.out.println("\n 登录成功！\n"+atm.Query(4, login_name)+" , 欢迎您!! \n");
    	}
    	if(login_name!=null) {
	    	while(doing_pass==null) {
	        	doing_pass = atm.input_doing(login_name);
	        	}
    	}
    	
    	System.out.println("\n 退出系统 , 欢迎再次使用！\n");
    }
}
