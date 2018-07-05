package com.mail;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailUtils {

	public static final String MAIL_HOST = "smtp.163.com";//"smtp1.cmc-xinnuo.com"
	public static final String MAIL_FROM = "yxjajl@163.com";
	public static final String MAIL_USER = "yxjajl@163.com";
	public static final String MAIL_PWD = "asdf1234";

	public static void send(MailBeanVO mailBeanVO) {

		Properties props = new Properties();

		// 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
		props.put("mail.smtp.host", MAIL_HOST);
		// 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.timeout", "25000");

		// 用刚刚设置好的props对象构建一个session
		Session session = Session.getDefaultInstance(props);

		// 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
		// 用（你可以在控制台（console)上看到发送邮件的过程）
		session.setDebug(true);

		// 用session为参数定义消息对象
		MimeMessage message = new MimeMessage(session);
		try {
			// 加载发件人地址
			message.setFrom(new InternetAddress(MAIL_FROM));
			// 加载收件人地址
			message.addRecipients(Message.RecipientType.TO, strToAddress(mailBeanVO.getTo()));
			// 加载标题
			message.setSubject(mailBeanVO.getSubject());

			// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
			Multipart multipart = new MimeMultipart();

			// 设置邮件的文本内容
			BodyPart contentPart = new MimeBodyPart();
			contentPart.setText("邮件的具体内容在此");
			multipart.addBodyPart(contentPart);
			// 添加附件
//			BodyPart messageBodyPart = new MimeBodyPart();
//			DataSource source = new FileDataSource(mailBeanVO.getAffix());
			// 添加附件的内容
//			messageBodyPart.setDataHandler(new DataHandler(source));
			// 添加附件的标题
			// 这里很重要，通过下面的Base64编码的转换可以保证你的中文附件标题名在发送时不会变成乱码
//			sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
//			messageBodyPart.setFileName("=?GBK?B?" + enc.encode(mailBeanVO.getAffixName().getBytes()) + "?=");
//			multipart.addBodyPart(messageBodyPart);

			// 将multipart对象放到message中
			message.setContent(multipart);
			// 保存邮件
			message.saveChanges();
			// 发送邮件
			Transport transport = session.getTransport("smtp");
			// 连接服务器的邮箱
			transport.connect(MAIL_HOST, MAIL_USER, MAIL_PWD);
			// 把邮件发送出去
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static InternetAddress[] strToAddress(String[] to) throws Exception {
		if (to == null) {
			return null;
		}

		InternetAddress[] arr = new InternetAddress[to.length];
		for (int i = 0; i < to.length; i++) {
			arr[i] = new InternetAddress(to[i]);
		}
		return arr;
	}

	public static class MailBeanVO {
		private String[] to; // 收件人地址
		private String affix; // 附件地址
		private String affixName; // 附件名称
		private String subject; // 邮件标题

		public String[] getTo() {
			return to;
		}

		public void setTo(String[] to) {
			this.to = to;
		}

		public String getAffix() {
			return affix;
		}

		public void setAffix(String affix) {
			this.affix = affix;
		}

		public String getAffixName() {
			return affixName;
		}

		public void setAffixName(String affixName) {
			this.affixName = affixName;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

	}

	public static void main(String[] args) {
		MailBeanVO vo = new MailBeanVO();
		vo.setTo(new String[] { "rick.yu@cignacmb.com" });
		vo.setSubject("一个带附件的JavaMail邮件");

		MailUtils.send(vo);
	}
}