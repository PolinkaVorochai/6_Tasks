package part_6.task_1.bean;

import java.util.Arrays;
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
public class User {
    String login;
    String password;
    boolean administrator;
    String email;

    public static String inputLogin() {
        System.out.println("Требуется авторизация. Введите логин: ");
        String login = "andreika123";
//            Scanner scannerLogin = new Scanner(System.in);
//            login = scannerLogin.next();
        return login;
    }

    public static String inputPassword() {
        System.out.println("Введите пароль: ");
        String password = "treasd";
//            Scanner scannerPassword = new Scanner(System.in);
//            password = scannerPassword.next();
        return password;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        char[] chars = password.toCharArray();
        char[] result = new char[6];
        result = Arrays.copyOf(chars, result.length);
        String resultPassword = new String(result);
        this.password = resultPassword;
    }

    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Логин: " + login + "\n");
        stringBuilder.append("Пароль: " + "******" + "\n");
        stringBuilder.append("email: " + email + "\n");
        stringBuilder.append("Права Администратора: " + administrator + "\n");
        return stringBuilder.toString();
    }
}
