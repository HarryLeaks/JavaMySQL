package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrepareStatement {
	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try{
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
			
			myStmt = myConn.createStatement();
			
			String[] nomes = {"Maria", "Chico", "Diogo"};
			String[] email = {"maria@gmail.com", "chico@gmail.com", "diogo@gmail.com"};
			
			for(int i = 0; i < nomes.length; i++) {
				String sql = "insert into Clientes (nome, email) values (" + nomes[i] + "," + email[i] + ")";
				myStmt.executeUpdate(sql);
				display(myRs);
			}
		}
		
		catch(Exception exc) {
			exc.printStackTrace();
		}finally {
			if(myRs != null) {
				myRs.close();
			}
			if(myStmt != null) {
				myStmt.close();
			}
			if(myConn != null) {
				myConn.close();
			}
		}
	}
	
	private static void display(ResultSet myRs) throws SQLException{
		while(myRs.next()) {
			String Nome = myRs.getString("nome");
			String Email = myRs.getString("email");
			
			System.out.printf("%s, %s\n", Nome, Email);
		}
	}
}
