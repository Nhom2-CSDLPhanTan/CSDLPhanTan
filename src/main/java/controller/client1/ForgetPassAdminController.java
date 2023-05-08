package controller.client1;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Person;
import model.dao.PersonDAO;

public class ForgetPassAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private PersonDAO personDAO;
    public ForgetPassAdminController() {
        super();
        personDAO = new PersonDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/client1/login/forget.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String email = request.getParameter("email");
		
		Person item = personDAO.getItemByEmail(email);
		
		if(item != null) {
			final String fromEmail = "tranphunguyenle30@gmail.com";
	        // Mat khai email cua ban
	        final String password = "ijkfnqphuoecxkgi";
	     
	        final String toEmail = email;
	        final String subject = "Forget Password";
	        final String body ="Code: " + item.getUser_Forget();
	        Properties props = new Properties();
	        props.setProperty("mail.smtp.starttls.enable", "true");
	        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
	        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
	        props.put("mail.smtp.port", "587"); //TLS Port
	        props.put("mail.smtp.auth", "true"); //enable authentication
	        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
	        Authenticator auth = new Authenticator() {
	            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(fromEmail, password);
	            }
	        };
	        javax.mail.Session session = javax.mail.Session.getInstance(props, auth);
	        MimeMessage msg = new MimeMessage(session);
	        //set message headers
	        try {
				msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
				msg.addHeader("format", "flowed");
		        msg.addHeader("Content-Transfer-Encoding", "8bit");
		        msg.setFrom(new InternetAddress(fromEmail, "NoReply-JD"));
		        msg.setReplyTo(InternetAddress.parse(fromEmail, false));
		        msg.setSubject(subject, "UTF-8");
		        msg.setText(body, "UTF-8");
		        msg.setSentDate(new Date());
		        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
		        Transport.send(msg);
		        
		        response.sendRedirect(request.getContextPath() + "/client1/admin/forget/login");
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
