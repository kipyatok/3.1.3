package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import web.models.Mail;
import web.models.User;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username")
    private String fromEmail;

    @Override
    public void sendSimpleMessage(Mail mail) {
        javaMailSender.send(newSimpleMessage(mail));
    }

    @Override
    public void sendSimpleMessage(User user, Mail mail) {
        mail.setToMail(user.getEmail());
        String message = String.format("Уважаемый, %s!" + mail.getBodyMail(), user.getName());
        mail.setBodyMail(message);
        javaMailSender.send(newSimpleMessage(mail));
    }

    @Override
    public void sendSimpleMessageAll(List<User> users, Mail mail) {
        for(User user : users) {
            javaMailSender.send(newSimpleMessage(mail));
        }
    }

    private SimpleMailMessage newSimpleMessage(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(mail.getToMail());
        message.setSubject(mail.getSubjectMail());
        message.setText(mail.getBodyMail());
        return message;
    }
}
