package com.at.springboot3emailthymeleaftemplate.controller;

import com.at.springboot3emailthymeleaftemplate.dto.MailRequest;
import com.at.springboot3emailthymeleaftemplate.service.EmailingService;

import jakarta.mail.MessagingException;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/mail")
public class EmailingController {
  private final EmailingService emailingService;

  @PostMapping
  @ResponseStatus(HttpStatus.OK)
  public void sendMail(@RequestBody MailRequest request) throws MessagingException {
    emailingService.sendMail(request);
  }
}