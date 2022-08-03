package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Updatecommand {
	public static void main(String[] args) throws SQLException{
	
	Connection myConn = null;
	Statement myStmt = null;
	ResultSet myRs = null;
	
	try {
		// 1. Get a connection to database
		myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root" , "");
		
		System.out.println("Database connection successful!\n");
		
		// 2. Create a statement
		myStmt = myConn.createStatement();
		
		System.out.println("Alterar dado da tabela...\n");
		
		String sql = "update empregados set email ='maria@mmm.com' where nome='Maria' and morada='Lisboa'";
		myStmt.executeUpdate(sql);
		
		// 3. Execute SQL query - Verificar se o registo foi inserido
		myRs = myStmt.executeQuery("select * from empregados order by nome");
		
		// 4. Process the result set
		while (myRs.next()) {
			System.out.println(myRs.getString("nome") + ", " + myRs.getString("morada") + ", " + myRs.getString("email"));
		}
	}
	catch (Exception exc) {
		exc.printStackTrace();
	}
	finally {
		if (myRs != null) {
			myRs.close();
		}
		
		if (myStmt != null) {
			myStmt.close();
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}
}
}
