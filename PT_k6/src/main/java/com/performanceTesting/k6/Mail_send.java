package com.performanceTesting.k6;

import java.io.File;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.sql.DataSource;

import org.apache.poi.poifs.nio.FileBackedDataSource;

@SuppressWarnings("unused")
public class Mail_send {
	static String emailMessage;
	
	static String Body="Hi,\r\n<br>";
			static String Body1	="\nGreetings of the day \r\n <br>";
			static String body2 = "\nPerformance Testing Report \n";

	static void Html_report() {
		StringBuilder buf = new StringBuilder();
		buf.append(Body);
		buf.append(Body1);
		buf.append(body2);
		buf.append("<html>" + "<body>" + "<table style=\"border: 1px solid black; border-collapse: collapse;\">"
				+ "<tr style=\"background-color: #f5f0f0; color: #fff; font-family: Arial; font-size: 14px; font-weight: bold;\">"
				+ "<th style=\"padding: 10px;border: 1px solid black;  color: black; border-collapse: collapse;\">Request_Name</th>"
				+ "<th style=\"padding: 10px; border: 1px solid black; color: black; border-collapse: collapse; \">Iterations</th>"
				+ "<th style=\"padding: 10px; border: 1px solid black; color: black; border-collapse: collapse; \">AVG_Time</th>"
				+ "<th style=\"padding: 10px; border: 1px solid black; color: black; border-collapse: collapse; \">90%_Time</th>"
				+ "<th style=\"padding: 10px; border: 1px solid black; color: black; border-collapse: collapse; \">Max_Time</th>" + "</tr>");

		for (int i = 0; i < Runner.API_name.size(); i++) {
			buf.append(
					"<tr style=\"font-family: Arial; font-size: 12px; border: 1px solid black; border-collapse: collapse; \"><td style=\"padding: 10px; border: 1px solid black; \">")
					.append(Runner.API_name.get(i))
					.append("</td><td style=\"padding: 10px; border: 1px solid black; border-collapse: collapse;\">")
					.append(Runner.Iteration.get(i))
					.append("</td><td style=\"padding: 10px; border: 1px solid black; border-collapse: collapse;\">")
					.append(Runner.Avg_time.get(i))
					.append("</td><td style=\"padding: 10px; border: 1px solid black; border-collapse: collapse; \">")
					.append(Runner.ninety_perecent_time.get(i))
					.append("</td><td style=\"padding: 10px; border: 1px solid black; border-collapse: collapse; \">")
					.append(Runner.Max_time.get(i)).append("</td></tr>");
		}
		buf.append("</table>" + "</body>" + "</html>");
		buf.append("<br> Regards <br>");
		buf.append("Vamshi Lagishetty<br>");
		emailMessage = buf.toString();

	}

	static void mail() {

		String to = controller.to_mail;
		String from = controller.from_mail;

		final String username = controller.username;
		final String password = controller.password;

		Properties properties = System.getProperties();

		properties.setProperty("mail.smtp.host", controller.Host);
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.port", controller.port);
		properties.put("mail.smtp.ssl.enable", "true");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress(from));

			String[] recipient1 = to.split(",");
	         for (String recipient : recipient1) {
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
	         }
			message.setSubject("Load Tesing Report "+Runner.Date);
			
			Html_report();

			message.setContent(emailMessage, "text/html; charset=utf-8");

			// Send message
			Transport.send(message);

			System.out.println("Sent sucessfully Kindly check your mail");

		} 
		catch (MessagingException e) {
			
			System.out.println("Error :"+e.getMessage());
		}
	}
	

}
