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

	static void Html_report() {
		StringBuilder buf = new StringBuilder();
		buf.append("<html>" + "<body>" + "<table style=\"border: 1px solid black;\">"
				+ "<tr style=\"background-color: #f5f0f0; color: #fff; font-family: Arial; font-size: 14px; font-weight: bold;\">"
				+ "<th style=\"padding: 10px;border: 1px solid black;\">Request_Name</th>"
				+ "<th style=\"padding: 10px; border: 1px solid black; \">Iterations</th>"
				+ "<th style=\"padding: 10px; border: 1px solid black; \">AVG_Time</th>"
				+ "<th style=\"padding: 10px; border: 1px solid black; \">90%_Time</th>"
				+ "<th style=\"padding: 10px; border: 1px solid black; \">Max_Time</th>" + "</tr>");

		for (int i = 0; i < Runner.API_name.size(); i++) {
			buf.append(
					"<tr style=\"font-family: Arial; font-size: 12px; border: 1px solid black; \"><td style=\"padding: 10px; border: 1px solid black; \">")
					.append(Runner.API_name.get(i))
					.append("</td><td style=\"padding: 10px; border: 1px solid black;\">")
					.append(Runner.Iteration.get(i))
					.append("</td><td style=\"padding: 10px; border: 1px solid black;\">")
					.append(Runner.Avg_time.get(i))
					.append("</td><td style=\"padding: 10px; border: 1px solid black; \">")
					.append(Runner.ninety_perecent_time.get(i))
					.append("</td><td style=\"padding: 10px; border: 1px solid black; \">")
					.append(Runner.Max_time.get(i)).append("</td></tr>");
		}
		buf.append("</table>" + "</body>" + "</html>");

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

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject("Load Tesing Report");

			message.setText("Hi,\n" + "Greetings of the day\n" + "Performance Testing Report ");

			Html_report();

			message.setContent(emailMessage, "text/html; charset=utf-8");

//		   message.setText("Best Regards");
			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
