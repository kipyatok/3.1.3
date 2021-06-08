package web.service;


import web.models.Mail;
import web.models.StandartMail;
import web.models.User;

import java.util.List;

public interface EmailService {
   void sendSimpleMessage(Mail mail);
   void sendSimpleMessage(User user, Mail mail);
   void sendSimpleMessageAll(List<User> users, Mail mail);
//   void sendMimeMessage(String to, String subject, MimeMessage message);
//    void sendMimeMessage(List<String> to, String subject, MimeMessage message);
}
