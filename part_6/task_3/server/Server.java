package part_6.task_3.server;

import part_6.task_3.server.account.Account;
import part_6.task_3.server.student.Student;

import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
/*
Задание 3: создайте клиент-серверное приложение “Архив”.
Общие требования к заданию:
• В архиве хранятся Дела (например, студентов). Архив находится на сервере.
• Клиент, в зависимости от прав, может запросить дело на просмотр, внести в него изменения, или создать новое дело.
Требования к коду лабораторной работы:
• Для реализации сетевого соединения используйте сокеты.
• Формат хранения данных на сервере – xml-файлы.
 */

public class Server {
    static final String fileStudent = "C:\\Users\\User\\IdeaProjects\\untitled\\src\\part_6\\task_3\\server\\fileXML\\students.xml";
    static final String fileAccount = "C:\\Users\\User\\IdeaProjects\\untitled\\src\\part_6\\task_3\\server\\fileXML\\account.xml";

    static Account activeUser = null;


    public static void main(String[] args) throws IOException, InterruptedException {
        while (true) {
            try {
                ServerSocket serverSocket = new ServerSocket(8000);//создание сокета
                System.out.println("Сервер подключен...");
                Socket clientSocket = serverSocket.accept();//прослушивание порта/клиент должен подключится
                System.out.println("Клиент подключен...");
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));//отправка сообщения в поток
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));//чтение потока от клиента

                while (true) {
                    String command = reader.readLine();//считать команду
                    switch (command) {
                        case "login":
                            String login = reader.readLine();//считать логин
                            String password = reader.readLine();//считать пароль
                            Account user = null;
                            List<Account> listAccount = new ArrayList<>(ParseXmlFile.parseXmlAccount(fileAccount));//чтение данных по всем аккаунтам из xmlFile
                            for (Account account : listAccount) {
                                if (account.getLogin().equals(login)) {
                                    if (account.getPassword().equals(password)) {
                                        user = account; //запомнить активированного пльзоваткля
                                        break;
                                    }
                                }
                            }
                            if (user != null) {
                                writer.write("true");//пользователь есть
                                writer.newLine();
                                writer.write(String.valueOf(user.isAdministrator()));//админ
                                writer.newLine();
                            } else {
                                writer.write("false");//админ
                                writer.newLine();
                            }
                            writer.flush();//отправка сообщения
                            break;
                        case "showCase":
                            List<Student> listStudent = new ArrayList<>(ParseXmlFile.parseXmlStudent(fileStudent));//чтение данных по всем студентам аккаунтам из xmlFile
                            for (Student student : listStudent) {
                                writer.write(student.toString());//здесь сообщение для отправки
                                writer.newLine();
                            }
                            writer.write("exit");
                            writer.newLine();
                            writer.flush();//отправка сообщения
                            break;
                        case "redactionCase":
                            int id = Integer.parseInt(reader.readLine());//считать id дела
                            String namePoint = reader.readLine();//считать поле для редактирвоания
                            String surname = reader.readLine();//считать новое значение поля
                            ParseXmlFile.redactionInXmlFileStudent(id, namePoint, surname);
                            break;
                        case "newCase":
                            String newSurname = reader.readLine();
                            String newName = reader.readLine();
                            String newDateBorn = reader.readLine();
                            String newFaculty = reader.readLine();
                            ParseXmlFile.writeInXmlFileNewStudent(newSurname, newName, newDateBorn, newFaculty);
                            break;
                        default:
                            System.out.println("command not supported");
                    }
                }
            } catch (SocketException e) {

            } catch (XMLStreamException e) {
                throw new RuntimeException(e);
            }

        }
    }
}


