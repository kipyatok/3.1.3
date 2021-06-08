package web.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import web.models.Mail;
import web.models.User;
import web.service.EmailService;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/sendEmail")
    public ResponseEntity<?> sendEmail(User user, Mail mail){
        emailService.sendSimpleMessage(user, mail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sendEmailAll")
    public ResponseEntity<?> sendEmailAll(List<User> users, Mail mail) {
        emailService.sendSimpleMessageAll(users, mail);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
