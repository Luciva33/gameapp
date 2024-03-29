package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Account;
import model.Login;

public class AccountsDAO {

	Connection con;
	PreparedStatement stmt;
	ResultSet rs;
	
	//接続処理
	private void connect() throws NamingException,SQLException{
		Context context = new InitialContext();
		DataSource ds = (DataSource)context.lookup("java:comp/env/mariadb");
		this.con = ds.getConnection();
	}
	// 切断処理
		private void disconnect() {
			try {
				if(rs != null)rs.close();
				if(stmt != null)stmt.close();
				if(con != null)con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	
	// 接続テスト
		public void connectCheck() {
			try {
				connect();
				System.out.println("接続OK");
			} catch (NamingException | SQLException e) {
				e.printStackTrace();
			}finally {
				disconnect();
			}
		}
	
	
	
	
	public Account findByLogin(Login login) {
		Account account =null;
		try {
			this.connect();
			this.stmt = con.prepareStatement
					("SELECT * FROM ACCOUNTS WHERE USER_ID =? AND PASS =?");
			this.stmt.setString(1, login.getUserId());
			this.stmt.setString(2,login.getPass());
			this.rs = this.stmt.executeQuery();    //SELECT文の実行、結果表を取得
			
			if(rs.next()) {
				String userId =rs.getString("USER_ID");
				String pass = rs.getString("PASS");
				String mail = rs.getString("MAIL");
				String name = rs.getString("NAME");
				account = new Account(userId,pass,mail,name);
				
			}
		
		
		}catch(NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			this.disconnect();
		}
		
		
		return account;
		
	}
}
