package part_6.task_2;

import part_6.task_2.file.WorkWithFile;
import part_6.task_2.note.Notepad;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//Задание 2. Блокнот. Разработать консольное приложение, работающее с Заметками в Блокноте. Каждая Заметка это: Заметка (тема, дата создания, e-mail, сообщение).
//        Общие пояснения к практическому заданию.
//        • В начале работы приложения данные должны считываться из файла, в конце работы – сохраняться в файл.
//        • У пользователя должна быть возможность найти запись по любому параметру или по группе параметров (группу параметров
//        можно определить самостоятельно), получить требуемые записи в отсортированном виде, найти записи, текстовое поле которой содержит определенное слово,
//        а также добавить новую запись.
//        • Особое условие: поиск, сравнение и валидацию вводимой информации осуществлять с использованием регулярных выражений.
//        • Особое условие: проверку введенной информации на валидность должен осуществлять код, непосредственно добавляющий информацию.
public class Client {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Client.menu();
    }

    private static void menu() throws FileNotFoundException, IOException {
        Notepad notepad = new Notepad(WorkWithFile.getNoteFromFile());//в начале работы читать данные из файла
        System.out.println("Меню:" + "\n" + "1 - добавить заметку;" +
                "\n" + "2 - поиск слова в тексте заметки;" + "\n" + "3 - поиск заметки по дате;" + "\n" + "4 - поиск заметки по email;" +
                "\n" + "5 - поиск заметки по теме;" + "\n" + "6 - поиск заметки по email и дате;" + "\n" + "0 - выход;");
        int command;
        boolean menu = true;
        while (menu) {
            System.out.println("Введите команду: ");
            Scanner scanner = new Scanner(System.in);
            command = scanner.nextInt();
            switch (command) {
                case 1:
                    notepad.addNote();
                    break;
                case 2:
                    notepad.findWord();
                    break;
                case 3:
                    notepad.findDate();
                    break;
                case 4:
                    notepad.findEmail();
                    break;
                case 5:
                    notepad.findTopic();
                    break;
                case 6:
                    notepad.findDateEmail();
                    break;
                case 0:
                    notepad.inFile();
                    menu = false;
                    break;
                default:
                    System.out.println("Введена неверная команда. Повторите ввод: ");
                    scanner = new Scanner(System.in);
                    command = scanner.nextInt();
            }
        }
    }
}
