package part_6.task_2.note;

import part_6.task_2.comparator.DateComparator;
import part_6.task_2.comparator.DateEmailComparator;
import part_6.task_2.comparator.EmailComparator;
import part_6.task_2.comparator.TopicComparator;
import part_6.task_2.file.WorkWithFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Задание 2. Блокнот. Разработать консольное приложение, работающее с Заметками в Блокноте. Каждая Заметка это: Заметка (тема, дата создания, e-mail, сообщение).
//        Общие пояснения к практическому заданию.
//        • В начале работы приложения данные должны считываться из файла, в конце работы – сохраняться в файл.
//        • У пользователя должна быть возможность найти запись по любому параметру или по группе параметров (группу параметров можно определить самостоятельно),
//        получить требуемые записи в отсортированном виде, найти записи, текстовое поле которой содержит определенное слово, а также добавить новую запись.
//        • Особое условие: поиск, сравнение и валидацию вводимой информации осуществлять с использованием регулярных выражений.
//        • Особое условие: проверку введенной информации на валидность должен осуществлять код, непосредственно добавляющий информацию.
public class Notepad {
    private List<Note> noteList;

    public Notepad(List<Note> noteList) {
        this.noteList = noteList;
    }

    public void addNote() {
        noteList.add(new Note());
    }

    public void findWord() {
        String word;
        System.out.println("Введите слово: ");
        Scanner scanner = new Scanner(System.in);
        word = scanner.next();
        Pattern pattern = Pattern.compile(word);
        boolean find = false;
        for (Note note : noteList) {
            Matcher matcher = pattern.matcher(note.getText());
            if (matcher.find() == true) {
                System.out.println(note);
                find = true;
            }
        }
        if (!find) {
            System.out.println(word + " не найдено в заметках.");
        }
    }

    public void findDateEmail() {
        DateEmailComparator comparator = new DateEmailComparator();
        List<Note> result = new ArrayList<>();
        String email;
        String date;
        System.out.println("Введите email: ");
        Scanner scanner = new Scanner(System.in);
        email = scanner.next();
        System.out.println("Введите дату в формате ЧЧ.ММ.ГГГГ: ");
        scanner = new Scanner(System.in);
        date = scanner.next();
        Pattern pattern1 = Pattern.compile(email);
        Pattern pattern2 = Pattern.compile(date);
        boolean find = false;
        for (Note note : noteList) {
            Matcher matcher1 = pattern1.matcher(note.getEmail());
            Matcher matcher2 = pattern2.matcher(note.getDate());
            if (matcher1.find() == true && matcher2.matches() == true) {
                result.add(note);
                find = true;
            }
        }
        if (!find) {
            System.out.println(email + " " + date + " не найдены в заметках.");
        } else {
            result.sort(comparator);
            System.out.println(result);
        }
    }

    public void findDate() {
        DateComparator comparator = new DateComparator();
        List<Note> result = new ArrayList<>();
        String date;
        System.out.println("Введите дату в формате ЧЧ.ММ.ГГГГ: ");
        Scanner scanner = new Scanner(System.in);
        date = scanner.next();
        Pattern pattern = Pattern.compile(date);
        boolean find = false;
        for (Note note : noteList) {
            Matcher matcher = pattern.matcher(note.getDate());
            if (matcher.matches() == true) {
                result.add(note);
                find = true;
            }
        }
        if (!find) {
            System.out.println(date + " не найдена в заметках.");
        } else {
            result.sort(comparator);
            System.out.println(result);
        }
    }

    public void findEmail() {
        EmailComparator comparator = new EmailComparator();
        List<Note> result = new ArrayList<>();
        String email;
        System.out.println("Введите email: ");
        Scanner scanner = new Scanner(System.in);
        email = scanner.next();
        Pattern pattern = Pattern.compile(email);
        boolean find = false;
        for (Note note : noteList) {
            Matcher matcher = pattern.matcher(note.getEmail());
            if (matcher.find() == true) {
                result.add(note);
                find = true;
            }
        }
        if (!find) {
            System.out.println(email + " не найдена в заметках.");
        } else {
            result.sort(comparator);
            System.out.println(result);
        }
    }

    public void findTopic() {
        TopicComparator comparator = new TopicComparator();
        List<Note> result = new ArrayList<>();
        String topic;
        System.out.println("Введите слово/предложение: ");
        Scanner scanner = new Scanner(System.in);
        topic = scanner.nextLine();
        Pattern pattern = Pattern.compile(topic);
        boolean find = false;
        for (Note note : noteList) {
            Matcher matcher = pattern.matcher(note.getTopic());
            if (matcher.find() == true) {
                result.add(note);
                find = true;
            }
        }
        if (!find) {
            System.out.println(topic + " не найдена в заметках.");
        } else {
            result.sort(comparator);
            System.out.println(result);
        }
    }

    public void inFile() throws IOException {
        boolean writeInEnd = false;
        for (Note note : noteList) {
            WorkWithFile.noteInFile(note, true);
        }
    }
}

