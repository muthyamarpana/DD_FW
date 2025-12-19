package JavaEmail;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MonitoringMail
{
	
	public void sendMail(String mailServer, String from, String to, String subject, String messageBody) throws MessagingException, AddressException
	{
   
 		Properties props = new Properties();
		props.put("mail.smtp.host", mailServer);
		props.put("mail.smtp.port", String.valueOf(587));
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		// optional timeouts
		props.put("mail.smtp.connectiontimeout", "10000");
		props.put("mail.smtp.timeout",           "10000");

		// 4. Create session
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(from, TestConfig.password);
			}
		});

		try {
			// 5. Create message
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			msg.setSubject("Test Email from Java");
			msg.setText("Hello!\n\nThis is a test email sent from Java code.");

			// 6. Send message
			javax.mail.Transport.send(msg);

			System.out.println("Email sent successfully.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		
	}
	
}
