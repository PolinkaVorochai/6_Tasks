package part_6.task_2.pattern;
//Задание 2. Блокнот. Разработать консольное приложение, работающее с Заметками в Блокноте. Каждая Заметка это: Заметка (тема, дата создания, e-mail, сообщение).
//        Общие пояснения к практическому заданию.
//        • В начале работы приложения данные должны считываться из файла, в конце работы – сохраняться в файл.
//        • У пользователя должна быть возможность найти запись по любому параметру или по группе параметров (группу параметров можно определить самостоятельно),
//        получить требуемые записи в отсортированном виде, найти записи, текстовое поле которой содержит определенное слово, а также добавить новую запись.
//        • Особое условие: поиск, сравнение и валидацию вводимой информации осуществлять с использованием регулярных выражений.
//        • Особое условие: проверку введенной информации на валидность должен осуществлять код, непосредственно добавляющий информацию.

import java.util.regex.Pattern;

public class PatternValidity {
    public static Pattern patternText() {
        Pattern pattern = Pattern.compile(".+");
        return pattern;
    }

    public static Pattern patternTopic() {
        Pattern pattern = Pattern.compile(".{1,30}");
        return pattern;
    }

    //“(\\w{1,}[\\.-]{0,1}\\w{1,})+@(\\w{1,}[\\.-]{0,1}\\w{1,})+[\\.]{1}[a-z]{2,4}
    public static Pattern patternEmail() {
        Pattern pattern = Pattern.compile("\\w+[\\.-]?\\w+@\\w+[\\.-]?\\w+\\.[a-z]{2,4}");
        return pattern;
    }

    public static Pattern patternDate() {
        Pattern pattern = Pattern.compile("((0[1-9])||([12]\\d)||(3[01]))\\.((0[1-9])||(1[012]))\\.((2022)||(2023))");
        return pattern;
    }

}
