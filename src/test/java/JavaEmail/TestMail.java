package JavaEmail;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class TestMail {

	public static void main(String[] args) throws AddressException, MessagingException {
		// TODO Auto-generated method stub
		MonitoringMail mail = new MonitoringMail();
		mail.sendMail(TestConfig.server,TestConfig.from , TestConfig.to, TestConfig.subject, TestConfig.messageBody);
	}

}
