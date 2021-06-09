package web.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="mail")
@Data
public class Mail {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String toMail;
    private String fromMail;
    private String bodyMail;
    private String subjectMail;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Mail() {
    }

    public static Mail activationMail(String email, String name) {
        Mail mail = new Mail();
        String message = String.format(
                "Здравствуйте, %s! \n" +
                        "Добро пожаловать на наш сайт по продаже товаров!", name
        );
        mail.setToMail(email);
        mail.setSubjectMail("Hello!");
        mail.setBodyMail(message);
        return mail;
    }

    public static Mail changeFieldUser(String email, String name) {
        Mail mail = new Mail();
        String message = String.format("Уважаемый, %s! \n" +
                "Ваши данные измененны!", name
        );
        mail.setToMail(email);
        mail.setSubjectMail("Изменение данных");
        mail.setBodyMail(message);
        return mail;
    }

    public static Mail deleteUser(String email, String name) {
        Mail mail = new Mail();
        String message = String.format("Уважаемый, %s! \n" +
                "Ваш аккаунт удален!", name
        );
        mail.setToMail(email);
        mail.setSubjectMail("Удаление аккаунта");
        mail.setBodyMail(message);
        return mail;
    }
}
