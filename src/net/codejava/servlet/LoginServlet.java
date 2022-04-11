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

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

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
		//htmlRespone += "<h2>Your username is: " + username + "<br/>";		
		//htmlRespone += "Your password is: " + password + "</h2>";		
		String secretKey = "QDWSM3OYBPGTEVSPB5FKVDM3CSNCWHVK";
        String email = "test@gmail.com";
        String companyName = "Awesome Company";
        try {
        String barCodeUrl = Utils.getGoogleAuthenticatorBarCode(secretKey, email, companyName);
        System.out.println(barCodeUrl);
        	Utils.createQRCode(barCodeUrl, "C:\\Users\\ling1\\eclipse-workspace2\\FormHandlingServlet\\WebContent\\aaa.png", 400, 400);
        } catch(WriterException e){
        	e.printStackTrace();
        }

		String htmlResponse = "<html> <head> <meta charset=\"ISO-8859-1\"> <title>Authentication</title> </head>";
		
        htmlResponse += "<body>";
        htmlResponse += "<div align=\"center\">";
        htmlResponse += "<p>Scan the QR below using Google Authenticator to get Authentication Code!<p>";
        htmlResponse += "   <img src=\"/FormHandlingServlet/qrc.jpg\" alt=\"if you can not see the code, contact (phone number)\"> ";
        htmlResponse += "   <form name=\"authenticator\" method=\"post\" action=\"aug\"> ";
        htmlResponse += "       Verification Code: <input type=text name=\"vCode\"/> <br/> ";
        htmlResponse += "       <input type=\"submit\" value=\"Login\" /> ";
        htmlResponse += "  </form> ";
        htmlResponse += "</div>";
        htmlResponse += "</body>";
        htmlResponse += "</html>";
        
		htmlResponse += "</html>";
		
		
		// return response
		writer.println(htmlResponse);
		

		
	}

}