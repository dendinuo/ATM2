package com.testATM.ATM2;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class UserInput {
	static ATMaction atm= new ATMaction();
	static UserInput user = new UserInput();
	public String Input(String n) throws IOException, SQLException{
		String str=null;
		
		if(n=="in_name") {
		System.out.println("请输入用户名：");
		Scanner sc_name = new Scanner(System.in);
		str = sc_name.next();
		}
		if(n=="in_pwd") {
			System.out.println("请输入密码：");
		Scanner sc_pwd = new Scanner(System.in);
	    str = sc_pwd.next();
	    }		
		
		if(n=="in_payee") {
			while(true) {
				System.out.println("请输入收款人用户名：");
				Scanner sc_name = new Scanner(System.in);
				str = sc_name.next();
				if( atm.Query(1, str) ==null ) {
					System.out.println("用户不存在，请重新输入！\n");
					}else {
						break;
					}
			}
		}
		if(n=="in_money") {
			while(true) {
				Scanner sc_name = new Scanner(System.in);
				str = sc_name.next();
				if( Long.parseLong(str)%100!=0 || Long.parseLong(str)<=0 ) {
					System.out.println("金额必须为100的整数倍，请重新输入！\n");
				}else {
					break;
				}
			}
		}		
		if(n=="option") {
			System.out.println("请选择服务类型：1-查询余额 2-取款 3-转账 0-退出：");
			Scanner sc_name = new Scanner(System.in);
			str = sc_name.next();
			}
		
		
		return str ;
	}
	
}
