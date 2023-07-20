package com.jsp.jdbc_preparedstatement_crud.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ProductDeleteController {
	public static void main(String[] args) {
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/jdbc_preparedstatement";
			String user = "root";
			String pass = "1234";
			con = DriverManager.getConnection(url, user, pass);
			
			
			String sqlShow="select * from product";
			PreparedStatement ps1=con.prepareStatement(sqlShow);
			ResultSet rs=ps1.executeQuery();
			while(rs.next()) {
				System.out.println("product Id : "+rs.getInt(1)+
											   ", product name : "+rs.getString(2)+
											   ", product price : "+rs.getDouble(3)+
											   ", product color : "+rs.getString(4));
				System.out.println();
			}
			
			System.out.println();
			
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter product id : ");
			int id=sc.nextInt();
			String sql="delete from product where productid=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, id);
			int i=ps.executeUpdate();
			
			if(i==1) {
				System.out.println("Data deleted");
			}else {
				System.out.println("Data not deleted");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		finally {
			if(con!=null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
