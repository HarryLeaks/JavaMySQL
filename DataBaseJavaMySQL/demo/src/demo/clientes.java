package demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class clientes {
	public static void main(String[] args) throws SQLException {
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try{
			myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "");
			
			myStmt = myConn.prepareStatement("select * from empregados where salario > ? and departamento=?");
			
			myStmt.setDouble(1, 80000);
			myStmt.setString(2, "Juridico");
			
			myRs = myStmt.executeQuery();
			display(myRs);
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
			String Morada = myRs.getString("morada");
			double Salario = myRs.getDouble("salario");
			String Departamento = myRs.getString("departamento");
			
			System.out.printf("%s, %s, %.2f, %s \n", Nome, Morada, Salario, Departamento);
		}
	}	
	

}
