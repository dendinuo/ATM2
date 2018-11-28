package com.testATM.ATM2;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class test 
{
    public static void main( String[] args ) throws SQLException
    {
    	/***预先创建 目标数据库***/
    	Connection conn = null;
        String sql;
        String url = "jdbc:mysql://localhost:3306/user_info?"
                + "user=root&password=root&useUnicode=true&amp&characterEncoding=utf-8";
        // URL = "jdbc:mysql://localhost:3306/数据库名"  、USER = "用户名"  、PASS = "密码" 必须要有
        try {
			// 动态加载mysql驱动
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("成功加载MySQL驱动程序");
			// Connection代表一个数据库连接， 
			//conn = DriverManager.getConnection( URL , USER , PASS );  
            conn = DriverManager.getConnection(url);
         // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
            sql = " select username from user where username = 'sss'  ";//查询语句
            //预加载方法实例化 声明 对象
            PreparedStatement stmt =  conn.prepareStatement(sql);
        ResultSet result = stmt.executeQuery(sql);//执行sql并返回结果
        int i=0;
                 while(i<3) {
     	   //使用循环轮询可返回所有结果
     	   String str = result.getString(1);  	  
     	   System.out.println(">>"+str+"<<");
     	   if(str==null) {
     		  System.out.println("str = null");
     	   }
     	   i++;
     	  
        }
    /**
            int result2 = stmt.executeUpdate(sql);//执行sql并返回受影响行数，执行失败返回-1
            
            if(result2==-1) {
       
        	   System.out.println("执行失败");
           }
            **/
            
            	System.out.println("sql执行完成！");        
        
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
