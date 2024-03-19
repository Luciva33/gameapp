package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Login;
import model.LoginLogic;

@WebServlet("/LoginLoginTest")
public class LoginLoginTest extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		testExecuteOK();
		testExecuteNG();
	}
	
	public void testExecuteOK() {
		Login login = new Login("1","1234");
		LoginLogic bo = new LoginLogic();
		boolean result = bo.execute(login);
		if(!result) {
			System.out.println();
		}if(result) {
			System.out.println("testExecuteOK:成功しました");
		}else {
			System.out.println("testExecuteOK:失敗しました");
		}
	}
		
	public void testExecuteNG() {
		Login login = new Login("1","12345");
		LoginLogic bo = new LoginLogic();
		boolean result = bo.execute(login);
		if(!result) {
			System.out.println("testExecuteNG:成功しました");
		}else {
			System.out.println("testExecuteNG:失敗しました");
		}
	}
}