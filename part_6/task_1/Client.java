package part_6.task_1;

import part_6.task_1.bean.BookSet;
import part_6.task_1.bean.User;
import part_6.task_1.bean.UserSet;
import part_6.task_1.util.FileReaderAndWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
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
public class Client {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        BookSet bookSet = new BookSet(FileReaderAndWriter.getBookFromFile());
        UserSet userSet = new UserSet(FileReaderAndWriter.getUserFromFile());
        Client.menu(userSet.authorization(), bookSet);
    }

    private static void menu(User user, BookSet catalog) throws IOException {
        if (user != null) {
            if (user.isAdministrator() == false) {
                int command = 1;
                System.out.println("Меню пользователя:" + "\n" + "1 - просмотр каталога;" +
                        "\n" + "2 - поиск книги по наименованию;" + "\n" + "отрицательное число - выход;" + "\n");
                while (command > 0) {
                    System.out.println("Введите команду: ");
                    Scanner scanner = new Scanner(System.in);
                    command = scanner.nextInt();

                    switch (command) {
                        case 1:
                            int page = 0;
                            while (page >= 0) {
                                System.out.println("Введите страницу (отрицание - выход): ");
                                Scanner scannerPage = new Scanner(System.in);
                                page = scannerPage.nextInt();
                                if (page >= 0) {
                                    catalog.showCatalogByPage(page);
                                } else if (page < 0) {
                                    break;
                                }
                            }
                            break;
                        case 2:
                            catalog.searchBook();
                            break;
                        default:
                            System.out.println("Вы ввели неверную команду. Повторите ввод. ");
                            scanner = new Scanner(System.in);
                            command = scanner.nextInt();
                    }
                }

            } else {
                int command = 1;
                System.out.println("Меню Администратора:" + "\n" + "1 - просмотр каталога;" +
                        "\n" + "2 - поиск книги по наименованию;" + "\n" + "3 - добавить новую книгу;"
                        + "\n" + "4 - удалить книгу;" + "\n" + "5 - изменить книгу;" + "\n" + "отрицательное число - выход;" + "\n");
                while (command > 0) {
                    System.out.println("Введите команду: ");
                    Scanner scanner = new Scanner(System.in);
                    command = scanner.nextInt();
                    switch (command) {
                        case 1:
                            int page = 0;
                            while (page >= 0) {
                                System.out.println("Введите страницу (отрицание - выход): ");
                                Scanner scannerPage = new Scanner(System.in);
                                page = scannerPage.nextInt();
                                if (page >= 0) {
                                    catalog.showCatalogByPage(page);
                                } else if (page < 0) {
                                    break;
                                }
                            }
                            break;
                        case 2:
                            catalog.searchBook();
                            break;
                        case 3:
                            catalog.addBook();
                            break;
                        case 4:
                            catalog.deleteBook();
                            break;
                        case 5:
                            catalog.changeBook();
                            break;
                        default:
                            System.out.println("Вы ввели неверную команду. Повторите ввод. ");
                            scanner = new Scanner(System.in);
                            command = scanner.nextInt();
                    }

                }
            }
        } else {
            System.out.println("Вызов меню не возможен. Пользователь не авторизован.");
        }
    }
}





