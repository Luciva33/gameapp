package dao;
	
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Account;


//アカウント登録
public class AccountsReg {
	Connection con;
	PreparedStatement stmt;
	ResultSet rs;
	
	// 接続処理
	private void connect() throws NamingException, SQLException {
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
	
	// 全件取得
	public List<Account> findAll(){
		List<Account> list = new ArrayList<>();
		try {
			connect();
			
			// SQL文の準備,実行
			this.stmt = this.con.prepareStatement("SELECT * FROM accounts");
			this.rs = this.stmt.executeQuery();

			while(rs.next()) {
				// 各カラムのデータ取得
				String userId = rs.getString("USER_ID");
				String pass = rs.getString("PASS");
				String name = rs.getString("NAME");
				String mail =rs.getString("MAIL");
				
				// データからインスタンス生成、Listに追加
				Account account = new Account(userId,pass,name,mail);
				list.add(account);
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return list;
	}
	
	// １件追加
	public void InsertOne(Account account) {
		try {
			connect();
			
			// SQL文準備、実行
			this.stmt = this.con.prepareStatement("INSERT INTO accounts(USER_ID,PASS,NAME,MAIL) VALUES(?,?,?,?)");
			this.stmt.setString(1, account.getUserId());
			this.stmt.setString(2, account.getPass());
			this.stmt.setString(3, account.getName());
			this.stmt.setString(4, account.getMail());
			
			int result = this.stmt.executeUpdate();
			
			if(result != 1) {
				System.out.println("追加に失敗しました");
			}
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	
	//アカウントチェック
	
	public Account findByReg(Account account ) {
		Account a = null;
		try {
			
			this.connect();
			this.stmt = con.prepareStatement
					("SELECT * FROM ACCOUNTS WHERE USER_ID =? AND PASS =?");
			this.stmt.setString(1, account.getUserId());
			this.stmt.setString(2, account.getPass());
			this.rs = this.stmt.executeQuery();    //SELECT文の実行、結果表を取得
			
			if(rs.next()) {
				String userId =rs.getString("USER_ID");
				String pass = rs.getString("PASS");
				String mail = rs.getString("MAIL");
				String name = rs.getString("NAME");
				a = new Account(userId,pass,mail,name);
				//System.out.println(a.getUserId()+"/"+a.getPass());
				
			}
		
			
		
		}catch(NamingException | SQLException e) {
			
			e.printStackTrace();
			return null;
			
		}finally {
			this.disconnect();
		}
		
		
		return a;
		
	}
}
	

