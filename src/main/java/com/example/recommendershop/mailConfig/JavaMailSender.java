//package com.example.recommendershop.mailConfig;
//
//import java.io.InputStream;
//
//import jakarta.mail.internet.MimeMessage;
//import org.springframework.mail.MailException;
//import org.springframework.mail.MailSender;
//import org.springframework.mail.javamail.MimeMessagePreparator;
//
//public interface JavaMailSender extends MailSender {
//    MimeMessage createMimeMessage();
//
//    MimeMessage createMimeMessage(InputStream contentStream) throws MailException;
//
//    void send(MimeMessage mimeMessage) throws MailException;
//
//    void send(MimeMessage... mimeMessages) throws MailException;
//
//    void send(MimeMessagePreparator mimeMessagePreparator) throws MailException;
//
//    void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException;
//}
