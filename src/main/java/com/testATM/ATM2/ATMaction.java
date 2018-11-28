package com.testATM.ATM2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.commons.lang3.ArrayUtils;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class ATMaction {
	static ATMaction atm= new ATMaction();
	static UserInput user = new UserInput();
	Connection conn = null;
    String sql;
    String url = "jdbc:mysql://localhost:3306/user_info?"
            + "user=root&password=root&useUnicode=true&amp&characterEncoding=utf-8";
	
			
//用户功能菜单处理
	public String input_doing(String login_name) throws IOException, SQLException {
		String result = user.Input("option");
		
		if(result.equals("1")) {
			result = atm.Query(3, login_name);
			System.out.println("您目前的余额为："+result+"\n");
		}
		
		if(result.equals("2")) {
			System.out.println("您目前的余额为："+atm.Query(3, login_name)+"\n");
			System.out.println("请输入取款金额："+"\n");			
			long user_money=Long.parseLong( atm.Query(3, login_name) );
			long draw_money=Long.parseLong( user.Input("in_money") );
			long money = user_money - draw_money;
			atm.Modify( 3, login_name, String.valueOf(money) );
			System.out.println("取款成功！"+"\n");		
			System.out.println("您目前的余额为："+atm.Query(3, login_name)+"\n");
		}
		
		if(result.equals("3")) {
			System.out.println("您目前的余额为："+atm.Query(3, login_name)+"\n");		
			long user_money=Long.parseLong( atm.Query(3, login_name) );
			String receiver = user.Input("in_payee");
			long receive_money=Long.parseLong( atm.Query( 3, receiver ) );
			System.out.println("请输入汇款金额："+"\n");	
			long pay_money=Long.parseLong( user.Input("in_money") );
			user_money  -= pay_money;
			receive_money += pay_money;
			atm.Modify(3, login_name, String.valueOf(user_money) );
			atm.Modify(3, receiver, String.valueOf(receive_money) );
			System.out.println("转账成功！！"+"\n");
			System.out.println("您目前的余额为："+atm.Query(3, login_name)+"\n");
		}
		
		if(result.equals("0")) {
			return  result;
		}
		return null;
	}	
	
//登录用户名、密码 校验	
	public  String user_check(String name,String pwd) throws IOException, SQLException {        
        String name_check = atm.Query( 1, name );
        String pwd_check = atm.Query( 2, name );
        if ( name.equals(name_check) && pwd.equals(pwd_check) ) {
        	   return name;       	   	
            }
        return null;
	}	
	
//查询 数据库用户数据
	public String Query(int column , String item) throws IOException, SQLException {
		String sql_result=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            if ( column==1 ) {
            	sql = " select "+ "username"+" from user where username = "+"'"+item+"'";
            }
            if ( column==2 ) {
            	sql = " select "+ "password"+" from user where username = "+"'"+item+"'";
            }
            if ( column==3 ) {
            	sql = " select "+ "money"+" from user where username = "+"'"+item+"'";
            }
            if ( column==4 ) {
            	sql = " select "+ "names"+" from user where username = "+"'"+item+"'";
            }
  
            PreparedStatement stmt =  conn.prepareStatement(sql);
            ResultSet result = stmt.executeQuery(sql);        
            while(result.next()) {
            sql_result = result.getString(1);       	   
            }
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return sql_result;
	}
//修改 数据库用户数据
	public boolean Modify(int column , String name , String modify_item ) throws IOException, SQLException {
		boolean sql_result=false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url);
            if ( column==1 ) {
            	sql = " update user set "+ "username ="+ "'"+modify_item+"'" +" where username = "+"'"+name+"'";
            }
            if ( column==2 ) {
            	sql = " update user set "+ "password ="+ "'"+modify_item+"'" +" where username = "+"'"+name+"'";
            }
            if ( column==3 ) {
            	sql = " update user set "+ "money ="+ "'"+modify_item+"'" +" where username = "+"'"+name+"'";
            }
           // sql="update user set password = 'hehe' where username = 'zhangsan9' ";
            PreparedStatement stmt =  conn.prepareStatement(sql);
            int result = stmt.executeUpdate(sql);        
            if(result==1) {
            sql_result = true;       	   
            }
   
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return sql_result;
	}
	
//测试用主方法
	public static void main(String[] args) throws IOException, SQLException {
	//	System.out.println(atm.pwd_check());
	//	System.out.println(">>"+atm.read_txt(0)+"<<");
	//	System.out.println( ">>"+atm.Query(4, "zhangsan9")+"<<" );
	}	
}
