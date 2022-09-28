package part_6.task_3.client;
/*
Задание 3: создайте клиент-серверное приложение “Архив”.
Общие требования к заданию:
• В архиве хранятся Дела (например, студентов). Архив находится на сервере.
• Клиент, в зависимости от прав, может запросить дело на просмотр, внести в него изменения, или создать новое дело.
Требования к коду лабораторной работы:
• Для реализации сетевого соединения используйте сокеты.
• Формат хранения данных на сервере – xml-файлы.
 */

import part_6.task_3.client.helper.MenuUser;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("192.168.100.2", 8000); //подключение к порту и хосту по своему пк

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));//отправка сообщения в поток

        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));//чтение потока от сервера
        String isUserFound = "";//вывод меню клиенту
        String isAdmin = "";
        //  while (false) {
        if (!isUserFound.equals("true")) {
            writer.write("login");//здесь сообщение для отправки
            writer.newLine();
            String login = "mary";
            writer.write(login);//здесь сообщение для отправки
            writer.newLine();
            String password = "gygj154gj";
            writer.write(password);//здесь сообщение для отправки
            writer.newLine();
            writer.flush();//отправка сообщения
        }
        isUserFound = reader.readLine();//вывод меню клиенту
        System.out.println(isUserFound);//вывод на экран
        if (isUserFound.equals("true")) {
            isAdmin = reader.readLine();//вывод меню клиенту
            System.out.println(isAdmin);//вывод на экран
            System.out.println(MenuUser.menu(Boolean.parseBoolean(isAdmin)));

            String command = "1";//пользователь задает команду
            switch (command) {
                case "1":
                    writer.write("showCase");//здесь сообщение для отправки
                    writer.newLine();
                    writer.flush();//отправка сообщения

                    String line;
                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                        if (line.equals("exit")) {
                            break;
                        }
                    }
                    break;
                case "2":
                    writer.write("redactionCase");//редактировать дело
                    writer.newLine();
                    String id = "2";//номер дела
                    writer.write(id);//здесь сообщение для отправки
                    writer.newLine();
                    String namePoint = "surname";//название поле для редактирования
                    writer.write(namePoint);//здесь сообщение для отправки
                    writer.newLine();
                    String surname = "Perry";//новаяьзначение поля
                    writer.write(surname);//здесь сообщение для отправки
                    writer.newLine();
                    writer.flush();//отправка сообщения
                    break;
                case "3":
                    writer.write("newCase");//создать новое дело
                    writer.newLine();
                    String newSurname = "Ten";
                    writer.write(newSurname);//здесь сообщение для отправки
                    writer.newLine();
                    String newName = "Kate";
                    writer.write(newName);//здесь сообщение для отправки
                    writer.newLine();
                    String newDateBorn = "14.03.1995";
                    writer.write(newDateBorn);//здесь сообщение для отправки
                    writer.newLine();
                    String newFaculty = "economic";
                    writer.write(newFaculty);//здесь сообщение для отправки
                    writer.newLine();
                    writer.flush();//отправка сообщения
                    break;
                default:
                    System.out.println("command not supported");
            }

        } else {
            System.out.println("User not found");
        }

        while (true) {

        }
    }
}
