package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.AccountsReg;

@WebServlet("/AccountsData")
public class AccountsData extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	//DAOクラスを通してデータベースのデータ取得
		AccountsReg reg = new AccountsReg();
		List<Account>list = reg.findAll();
		
	//レスポンス用のインスタンスを生成,JSON文字列に変換
		DBData result = new DBData(list);
		String json = new Gson().toJson(result);
		

		// レスポンス内容(JSON形式でレスポンス)
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(json);
	
		
	
	}

}
