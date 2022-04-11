package net.codejava.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.google.zxing.WriterException;
import static net.codejava.servlet.Utils.getTOTPCode;

@WebServlet("/aug")
public class AuthenticateServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// read form fields
		//String username = request.getParameter("username");
		//String password = request.getParameter("password");
		
		//System.out.println("username: " + username);
		//System.out.println("password: " + password);

		// do some processing here...
		
		// get response writer
		PrintWriter writer = response.getWriter();
		
		// build HTML code
		
		String htmlRespone = "<html>";
		//htmlRespone += "<h2>Your username is: " + username + "<br/>";		
		//htmlRespone += "Your password is: " + password + "</h2>";		
		
		String secretKey = "QDWSM3OYBPGTEVSPB5FKVDM3CSNCWHVK";
        Scanner scanner = new Scanner(System.in);
        String code = request.getParameter("vCode");
        if (code.equals(getTOTPCode(secretKey))) {
            System.out.println("Logged in successfully");
            htmlRespone += "<h2> Logged in successfully <br/>";		
        } 
        else {
            System.out.println("Invalid 2FA Code");
            htmlRespone += "<h2> Invalid 2FA Code <br/>";
        }
        
        
		htmlRespone += "</html>";
		
		
		// return response
		writer.println(htmlRespone);
		

		
	}

}