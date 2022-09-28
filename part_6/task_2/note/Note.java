package part_6.task_2.note;

import part_6.task_2.pattern.PatternValidity;

import java.util.Scanner;
import java.util.regex.Matcher;

//Задание 2. Блокнот. Разработать консольное приложение, работающее с Заметками в Блокноте. Каждая Заметка это: Заметка (тема, дата создания, e-mail, сообщение).
//        Общие пояснения к практическому заданию.
//        • В начале работы приложения данные должны считываться из файла, в конце работы – сохраняться в файл.
//        • У пользователя должна быть возможность найти запись по любому параметру или по группе параметров (группу параметров
//        можно определить самостоятельно), получить требуемые записи в отсортированном виде, найти записи, текстовое поле которой содержит определенное слово,
//        а также добавить новую запись.
//        • Особое условие: поиск, сравнение и валидацию вводимой информации осуществлять с использованием регулярных выражений.
//        • Особое условие: проверку введенной информации на валидность должен осуществлять код, непосредственно добавляющий информацию.
public class Note {
    private String topic;
    private String date;
    private String email;
    private String text;

    public Note(String topic, String date, String email, String text) {
        this.topic = topic;
        this.date = date;
        this.email = email;
        this.text = text;
    }

    public Note() {
        setTopic();
        setDate();
        setEmail();
        setText();
    }

    public void setDate() {
        String date;
        System.out.println("Введите дату в формате ЧЧ.ММ.ГГГГ: ");
        Scanner scanner = new Scanner(System.in);
        date = scanner.next();
        Matcher matcher = PatternValidity.patternDate().matcher(date);
        if (!matcher.matches()) {
            System.out.println("Введенная дата не соответствует формату. Повторите ввод: ");
            scanner = new Scanner(System.in);
            date = scanner.next();
        }
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTopic() {
        System.out.println("Введите тему заметки (от 1 до 30 символов): ");
        String topic;
        Scanner scanner = new Scanner(System.in);
        topic = scanner.nextLine();
        Matcher matcher = PatternValidity.patternTopic().matcher(topic);
        if (!matcher.matches()) {
            System.out.println("Введенная тема не соответствует формату. Повторите ввод: ");
            scanner = new Scanner(System.in);
            topic = scanner.nextLine();
        }
        this.topic = topic;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmail() {
        System.out.println("Введите email: ");
        String email;
        Scanner scanner = new Scanner(System.in);
        email = scanner.next();
        Matcher matcher = PatternValidity.patternEmail().matcher(email);
        if (!matcher.matches()) {
            System.out.println("Введеннй email не соответствует формату. Повторите ввод: ");
            scanner = new Scanner(System.in);
            email = scanner.next();
        }
        this.email = email;

    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setText() {
        System.out.println("Введите текст заметки: ");
        Scanner scanner = new Scanner(System.in);
        String text;
        text = scanner.nextLine();
        Matcher matcher = PatternValidity.patternText().matcher((CharSequence) text);
        if (!matcher.matches()) {
            System.out.println("Введенный текст не соответствует формату. Повторите ввод: ");
            scanner = new Scanner(System.in);
            text = scanner.nextLine();
        }
        this.text = text;
    }

    @Override
    public String toString() {
        return "Note{" +
                "topic='" + topic + '\'' +
                ", date='" + date + '\'' +
                ", email='" + email + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

