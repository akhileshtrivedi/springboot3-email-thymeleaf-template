package com.at.springboot3emailthymeleaftemplate.service;

import com.at.springboot3emailthymeleaftemplate.dto.MailRequest;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class EmailingService {
  private final JavaMailSender mailSender;
  private final TemplateEngine templateEngine;

  @Value("${spring.mail.username}")
  private String fromMail;

/*  @Async
  public void sendMail(MailRequest request) throws MessagingException {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

    mimeMessageHelper.setFrom(fromMail);
    mimeMessageHelper.setTo(request.getToEmail());
    mimeMessageHelper.setSubject(request.getSubject());

    if(request.isHTML()) {
      Context context = new Context();

      context.setVariable("content", request.getMessage());
      String processedString = templateEngine.process("template", context);

      mimeMessageHelper.setText(processedString, true);
    } else {
      mimeMessageHelper.setText(request.getMessage(), false);
    }

    mailSender.send(mimeMessage);
  }*/

@Async
  public void sendMail(MailRequest request) {
    Context ctx = new Context();
    MimeMessagePreparator prep =
        mimeMessage -> {
          MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
          messageHelper.setFrom("no-reply@recut.tv");
          //messageHelper.setTo("akhilesh.trivedi@amantechnologiesinc.com");
          messageHelper.setTo(request.getToEmail());
          messageHelper.setSubject(request.getSubject());
          ctx.setVariable("content", request.getMessage());
          setMessageBody(messageHelper, ctx, "template");
        };
    mailSender.send(prep);
  }
  private void setMessageBody(MimeMessageHelper messageHelper, Context ctx, String template)
      throws MessagingException {

    String plain = templateEngine.process(template, ctx);
    String html = templateEngine.process( template, ctx);
    messageHelper.setText(plain, html);
  }
}