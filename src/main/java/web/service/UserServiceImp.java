package web.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import web.dao.RoleDao;
import web.dao.UserDao;
import web.models.Mail;
import web.models.Role;
import web.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService;


    @Override
    public void add(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.add(user);
        if (!StringUtils.isEmpty(user.getEmail())){
            emailService.sendSimpleMessage(Mail.activationMail(user.getEmail(), user.getName()));
        }
    }

    @Override
    public void delete(Long id) {
        User user = userDao.getUser(id);
        userDao.delete(id);
        if (!StringUtils.isEmpty(user.getEmail())){
            emailService.sendSimpleMessage(Mail.deleteUser(user.getEmail(), user.getName()));
        }
    }

    @Override
    public void update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.update(user);
        if (!StringUtils.isEmpty(user.getEmail())){
            emailService.sendSimpleMessage(Mail.changeFieldUser(user.getEmail(), user.getName()));
        }
    }

    @Override
    public User getUserById(Long id) {
        return userDao.getUser(id);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public List<Role> roleUser() {
        return roleDao.getAllRoles();
    }

    @Override
    public List<User> listUser() {
        return userDao.getAllUser();
    }

    @Override
    public User getUser(Long id) {
        return userDao.getUser(id);
    }
}
