package com.jsp.jdbc_preparedstatement_crud.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.cj.jdbc.Driver;

public class ProductInsertController {

	public static void main(String[] args) {
		Connection con =null; 
		try {
			Driver dr = new Driver();
			DriverManager.registerDriver(dr);
			System.out.println("Driver loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_preparedstatement", "root",
					"1234");
			if (con != null) {
				System.out.println("Connection created");
			}
			boolean f=false;
			Scanner sc=new Scanner(System.in);
			
//			do while code for getting n number of data input
			
			do {
				System.out.println("Enter product id : ");
				int id=sc.nextInt();
				sc.nextLine();
				System.out.println("Enter product name : ");
				String name=sc.nextLine();
				System.out.println("Enter product price : ");
				double price=sc.nextDouble();
				sc.nextLine();
				System.out.println("Enter product color : ");
				String color=sc.next();	
				
//				Sql jdbc code
				
				
				String sql="insert into product (productid,productname,productprice,productcolor) values(?,?,?,?)";
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setInt(1, id);
				ps.setString(2, name);
				ps.setDouble(3, price);
				ps.setString(4, color);
				int i=ps.executeUpdate();
				if(i==1) {
					System.out.println("Data inserted");
				}else {
					System.out.println("Data not inserted");
				}
				
				
//				asking for data input again
				
				System.out.print("Enter 'yes' for continue inserting data else press any key  : ");
				String ans=sc.next();
				if(ans.equalsIgnoreCase("yes")) {
					f=true;
					System.out.println("+++++++++ Ok, Insert data again +++++++++");
				}else {
					f=false;
					System.out.println("+++++++++ Ok, good bye +++++++++");
				}
				
			}
				while(f);
//			End of while loop
			
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
