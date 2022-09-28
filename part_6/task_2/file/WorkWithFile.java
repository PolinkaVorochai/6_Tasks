package part_6.task_2.file;

import part_6.task_2.note.Note;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//Задание 2. Блокнот. Разработать консольное приложение, работающее с Заметками в Блокноте. Каждая Заметка это: Заметка (тема, дата создания, e-mail, сообщение).
//        Общие пояснения к практическому заданию.
//        • В начале работы приложения данные должны считываться из файла, в конце работы – сохраняться в файл.
//        • У пользователя должна быть возможность найти запись по любому параметру или по группе параметров (группу параметров можно определить самостоятельно),
//        получить требуемые записи в отсортированном виде, найти записи, текстовое поле которой содержит определенное слово, а также добавить новую запись.
//        • Особое условие: поиск, сравнение и валидацию вводимой информации осуществлять с использованием регулярных выражений.
//        • Особое условие: проверку введенной информации на валидность должен осуществлять код, непосредственно добавляющий информацию.
public class WorkWithFile {
    public static final String pathNotepad = "C:\\Users\\User\\IdeaProjects\\untitled\\src\\part_6\\task_2\\notepad.txt";

    public static List<Note> getNoteFromFile() throws FileNotFoundException {
        File file = new File(pathNotepad);
        Scanner scanner = new Scanner(file);
        List<Note> noteList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("//") || line.length() == 0) {
                continue;
            } else {
                List<String> lineList = Arrays.asList(line.split(";"));
                Note note = new Note(lineList.get(0), lineList.get(1), lineList.get(2), lineList.get(3));
                noteList.add(note);
            }
        }
        return noteList;
    }

    //сохранить данные в файл
    public static void noteInFile(Note note, boolean append) throws IOException {
        FileWriter fileWriter = new FileWriter(pathNotepad, append);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(note.getTopic() + ";");
        bufferedWriter.write(note.getDate() + ";");
        bufferedWriter.write(note.getEmail() + ";");
        bufferedWriter.write(note.getText() + ";");
        bufferedWriter.newLine();
        bufferedWriter.close();
    }
}
