package com.study.product.servlet;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/SignUpPage")
public class SignUpPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public SignUpPage() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServletContext application = request.getServletContext();
		application.setAttribute("key1", "value1");
		application.setAttribute("key2", "value2");
		application.setAttribute("key3", "value3");
		
		request.setAttribute("key4", "value4");
		request.setAttribute("key5", "value5");
		request.setAttribute("key6", "value1");
		
		HttpSession Session = request.getSession();
		
		Session.setMaxInactiveInterval(1000 * 10 * 60);
		Session.setAttribute("key7", "value8");
		Session.setAttribute("key8", "value8");
		
		request.getRequestDispatcher("/WEB-INF/view/singup.jsp").forward(request, response);
		
		
		


	}



}
