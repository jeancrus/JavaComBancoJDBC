package application;

import java.sql.Connection;

import db.DB;

public class Program {

	public static void main(String[] args) {
		Connection con = DB.getConnection();
		
		DB.closeConnection();
	}

}
