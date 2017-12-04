package mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Sample {

	public static String myEmailAccount = "18331446114@163.com";
	public static String myEmailPassword = "wangxiaoting923";
	public static String myEmailSMTPHost = "smtp.163.com";
	public static String receiveMailAccount = "18395094047@163.com";
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "smtp"); // 使用的协议（JavaMail规范要求）
		props.setProperty("mail.smtp.host", myEmailSMTPHost); // 发件人的邮箱的 SMTP
																// 服务器地址
		props.setProperty("mail.smtp.auth", "true"); // 需要请求认证

		Session session = Session.getInstance(props);
		MimeMessage message = createMimeMessage(session, myEmailAccount,
				receiveMailAccount);
		Transport transport = session.getTransport();
		transport.connect(myEmailAccount, myEmailPassword);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}

	
	public static MimeMessage createMimeMessage(Session session,
			String sendMail, String receiveMail) throws Exception {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(sendMail, "圣诞爷爷", "UTF-8"));
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(
				receiveMail, "", "UTF-8"));
		message.setSubject("关怀问候", "UTF-8");
		message.setContent("你好，今天是12月24号，圣诞之夜马上到来。",
				"text/html;charset=UTF-8");
		message.setSentDate(new Date());
		message.saveChanges();
		return message;
	}

}
