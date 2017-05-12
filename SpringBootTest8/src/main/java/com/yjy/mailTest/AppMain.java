package com.yjy.mailTest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafTemplateAvailabilityProvider;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.expression.Themes;

import freemarker.template.Template;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class AppMain {
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	/* 测试发送内容 */
	@Test
	public void sendSimpleEmail() {
		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("326015540@qq.com");// 发送者.
		message.setTo("549346275@qq.com");// 接收者.
		message.setSubject("测试邮件（邮件主题）");// 邮件主题.
		message.setText("这是邮件内容");// 邮件内容.

		mailSender.send(message);// 发送邮件
	}

	/* 测试发送附件.(这里发送图片.) */
	@Test
	public void sendAttachmentsEmail() throws MessagingException {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		// 基本设置.
		helper.setFrom("326015540@qq.com");// 发送者.
		helper.setTo("1518010550@qq.com");// 接收者.
		helper.setSubject("测试附件（邮件主题）");// 邮件主题.
		helper.setText("这是邮件内容（有附件哦.）");// 邮件内容.

		// org.springframework.core.io.FileSystemResource下的:
		// 附件1,获取文件对象.
		FileSystemResource file1 = new FileSystemResource(new File("C:/Users/suerfly/Desktop/images/1.jpg"));
		// 添加附件，这里第一个参数是在邮件中显示的名称，也可以直接是head.jpg，但是一定要有文件后缀，不然就无法显示图片了。
		helper.addAttachment("头像1.jpg", file1);
		// 附件2
		FileSystemResource file2 = new FileSystemResource(new File("C:/Users/suerfly/Desktop/images/2.jpg"));
		helper.addAttachment("头像2.jpg", file2);

		mailSender.send(mimeMessage);
	}

	/* 邮件中使用静态资源. */
	@Test
	public void sendInlineMail() throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		// 基本设置.
		helper.setFrom("326015540@qq.com");// 发送者.
		helper.setTo("1518010550@qq.com");// 接收者.
		helper.setSubject("测试静态资源（邮件主题）");// 邮件主题.
		// 邮件内容，第二个参数指定发送的是HTML格式
		// 说明：嵌入图片<img src='cid:head'/>，其中cid:是固定的写法，而aaa是一个contentId。
		helper.setText("<body>这是图片：<img src='cid:head' /></body>", true);

		FileSystemResource file = new FileSystemResource(new File("C:/Users/suerfly/Desktop/images/2.jpg"));
		helper.addInline("head", file);

		mailSender.send(mimeMessage);
	}

	/* 测试模板邮件:freemarker */
	@Test
	public void sendTemplateMail() throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		// 基本设置.
		helper.setFrom("326015540@qq.com");// 发送者.
		helper.setTo("447865628@qq.com");// 接收者.
		helper.setSubject("模板邮件（邮件主题）");// 邮件主题.

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("username", "严景云");

		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
		configurer.setTemplateLoaderPath("classpath:templates");
		Template template = freeMarkerConfigurer.getConfiguration().getTemplate("email.ftl");// 加载资源文件

		String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
		helper.setText(html, true);

		mailSender.send(mimeMessage);
	}
}
