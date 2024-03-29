package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AccountsDAO;
import model.Account;
import model.Login;

@WebServlet("/AccountsRegTest")
public class AccountsRegTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		testFindByLoginOk();
		testFindByLoginNG();
	}
	
	public void testFindByLoginOk() {
		Login login = new Login("1", "1234");
		AccountsDAO dao = new AccountsDAO();
		Account result = dao.findByLogin(login);
		
		if (result != null &&
				result.getUserId().equals("1") &&
				result.getPass().equals("1234") &&
				result.getMail() == null &&
				result.getName().equals("mita")) {
			System.out.println("testFindByLoginOK:成功しました");

		} else {
			System.out.println("testFindByLoginOK:失敗しました");

		}
		
	}
	public void testFindByLoginNG() {
		Login login = new Login("1", "12345");
		AccountsDAO dao = new AccountsDAO();
		Account result = dao.findByLogin(login);

		if (result == null ) {
			System.out.println("testFindByLoginNG:成功しました");

		} else {
			System.out.println("testFindByLoginNG:失敗しました");

		}
		
	}

	

}
