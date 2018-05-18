package com.fun4g.riqin.mail;

import java.io.File;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import com.fun4g.riqin.util.BeanGetter;
import com.fun4g.riqin.util.DateHelper;
import com.fun4g.riqin.util.DayHelper;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class AttachedFileMail
{
	public JavaMailSenderImpl getSenderImpl() {
		return senderImpl;
	}

	public void setSenderImpl(JavaMailSenderImpl senderImpl) {
		this.senderImpl = senderImpl;
	}

	private
	JavaMailSenderImpl senderImpl;
	/**
	 * 本类测试的是关于邮件中带有附件的例子
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception
	{
		AttachedFileMail am= (AttachedFileMail) BeanGetter.getBean("attachedFileMail");

		// 设定mail server
		//senderImpl.setHost("smtp.163.com");
		// 建立邮件消息,发送简单邮件和html邮件的区别
		MimeMessage mailMessage = am.getSenderImpl().createMimeMessage();
		// 注意这里的boolean,等于真的时候才能嵌套图片，在构建MimeMessageHelper时候，所给定的值是true表示启用，
		// multipart模式 为true时发送附件 可以设置html格式
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,
				true, "utf-8");

		// 设置收件人，寄件人

		messageHelper.setTo(new String[]{"sunzhiqiang.nj@jsoa.net","scottsun@189.cn"});
		messageHelper.setFrom("scottct@163.com");
		messageHelper.setSubject("政企客户支撑中心日清统计（"+ DateHelper.parseSimpleString(DayHelper.getYesDay())+")");
		// true 表示启动HTML格式的邮件
		messageHelper.setText(
				"<html><head></head><body><h1>你好：附件中有学习资料！</h1></body></html>",
				true);

		FileSystemResource file = new FileSystemResource(
				new File("g:/a.xls"));
		// 这里的方法调用和插入图片是不同的。
		messageHelper.addAttachment(DateHelper.getCurrentYYYYMMDD()+".xls", file);


		// 发送邮件
		am.getSenderImpl().send(mailMessage);

		System.out.println("邮件发送成功..");
	}
}