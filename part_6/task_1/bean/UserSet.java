package part_6.task_1.bean;

import part_6.task_1.util.FileReaderAndWriter;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.Set;
/*
Общие требования к заданию:
• Система учитывает книги как в электронном, так и в бумажном варианте.
• Существующие роли: пользователь, администратор.
• Пользователь может просматривать книги в каталоге книг, осуществлять поиск книг в каталоге.
• Администратор может модифицировать каталог.
• *При добавлении описания книги в каталог оповещение о ней рассылается на e-mail всем пользователям
• **При просмотре каталога желательно реализовать постраничный просмотр
• ***Пользователь может предложить добавить книгу в библиотеку, переслав её администратору на e-mail.
• Каталог книг хранится в текстовом файле.
• Данные аутентификации пользователей хранятся в текстовом файле. Пароль не хранится в открытом виде
 */
public class UserSet {
    static String from = "catalogBooks@mail.ru";
    private static Set<User> userSet;

    static {
        try {
            userSet = FileReaderAndWriter.getUserFromFile();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public UserSet() {
    }

    public UserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public static void sendEmail(Book textEmail) {
        String[] addresses = new String[userSet.size()];
//        Iterator<User> i = userSet.iterator();
        for (User user : userSet) {
            for (int i = 0; i < userSet.size(); ) {
                addresses[i] = user.getEmail();
                i++;
            }
        }


        String host = "localhost";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smpt.host", host);
        Session session = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            message.addRecipients(Message.RecipientType.TO, String.valueOf(addresses));
            message.setSubject("Добавлена новая книга в каталог");
            message.setText(textEmail.toString());

//            Transport.send(message);
            System.out.println("Сообщение отправлено");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public User authorization() {
        User activeUser = null;
        boolean authorization = false;
        User userInit = new User();
        userInit.setLogin(User.inputLogin());
        userInit.setPassword(User.inputPassword());
        for (User user : userSet) {
            if (user.getLogin().equals(userInit.getLogin()) && user.getPassword().equals(userInit.getPassword())) {
                activeUser = user;
                authorization = true;
                break;
            } else {
                continue;
            }
        }
        if (authorization == false) {
            System.out.println("Вы не авторизованы.");
        } else {
            System.out.println("Вы авторизованы. Добрый день пользователь " + activeUser.getLogin());
        }
        return activeUser;
    }
}

