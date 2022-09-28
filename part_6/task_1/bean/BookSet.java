package part_6.task_1.bean;

import part_6.task_1.util.FileReaderAndWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
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
public class BookSet {
    private static Set<Book> bookSet;

    static {
        try {
            bookSet = FileReaderAndWriter.getBookFromFile();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private int pageSize = 2;


    public BookSet(Set<Book> bookSet) {
        this.bookSet = bookSet;
    }

    public BookSet() {
    }

    // команда 1
//    public void showCatalog() {
//        for (Book book : bookSet) {
//            System.out.println(book);
//        }
//    }
    // команда 1
    public int showCatalogByPage(int currentPage) {
        for (int i = currentPage * pageSize; i < (currentPage + 1) * pageSize; i++) {
            if (bookSet.size() > i) {
                System.out.println(bookSet.toArray()[i]);
            } else {
                System.out.println("Конец каталога");
                break;
            }
        }
        return currentPage + 1;
    }


    // команда 2
    public void searchBook() {
        boolean findBook = false;
        String nameBook;
        System.out.println("введите наименование книги: ");
        Scanner scanner = new Scanner(System.in);
        nameBook = scanner.nextLine();
        for (Book book : bookSet) {
            if (book.getNameBook().equalsIgnoreCase(nameBook)) {
                System.out.println(book);
                findBook = true;
                break;
            } else {
                findBook = false;
            }
        }
        if (findBook == false) {
            System.out.println("Такой книги не найдено");
        }
    }


    // команда 3
    public Set<Book> addBook() throws IOException {
        Book addBook = new Book();

        System.out.println("Введите название книги: ");
        String nameBook;
        Scanner scannerNameBook = new Scanner(System.in);
        nameBook = scannerNameBook.nextLine();
        addBook.setNameBook(nameBook);

        System.out.println("Введите атора: ");
        String authorBook;
        Scanner scannerAuthorBook = new Scanner(System.in);
        authorBook = scannerAuthorBook.nextLine();
        addBook.setAuthor(authorBook);

        System.out.println("Введите ISBN: ");
        String isbnBook;
        Scanner scannerIsbnBook = new Scanner(System.in);
        isbnBook = scannerIsbnBook.next();
        addBook.setIsbn(isbnBook);

        System.out.println("Введите описание книги: ");
        String descriptionBook;
        Scanner scannerDescriptionBook = new Scanner(System.in);
        descriptionBook = scannerDescriptionBook.nextLine();
        addBook.setDescription(descriptionBook);

        System.out.println("Введите год издания книги: ");
        int yearOfPublishingBook;
        Scanner scannerYearOfPublishingBook = new Scanner(System.in);
        yearOfPublishingBook = scannerYearOfPublishingBook.nextInt();
        addBook.setYearOfPublishing(yearOfPublishingBook);

        System.out.println("Введите количество страниц : ");
        int numberOfPageBook;
        Scanner scannerNumberOfPageBook = new Scanner(System.in);
        numberOfPageBook = scannerNumberOfPageBook.nextInt();
        addBook.setNumberOfPage(numberOfPageBook);

        System.out.println("Введите издательство: ");
        String publishingBook;
        Scanner scannerPublishingBook = new Scanner(System.in);
        publishingBook = scannerPublishingBook.nextLine();
        addBook.setPublishing(publishingBook);

        System.out.println("true-электронный формат, false-бумажный формат: ");
        boolean eBook;
        Scanner scannerEBook = new Scanner(System.in);
        eBook = scannerEBook.nextBoolean();
        addBook.seteBook(eBook);

        FileReaderAndWriter.addBookInFile(addBook);//запись новой книги в конец каталога
//        UserSet.sendEmail(addBook);//отправка на email
        return bookSet = FileReaderAndWriter.getBookFromFile();
    }

    public Set<Book> getBookSet() {
        return bookSet;
    }

    public void writeInFileBookSet() throws IOException {
        boolean isNewFile = true;
        for (Book book : bookSet) {
            if (isNewFile) {
                FileReaderAndWriter.catalogInFile(book, false);
                isNewFile = false;
            } else {
                FileReaderAndWriter.catalogInFile(book, true);
            }
        }
    }

    // команда 4
    public Set<Book> deleteBook() throws IOException {
        boolean delete = false;
        System.out.println("\n" + "Все ISBN, имеющиеся в каталоге: ");
        for (Book book : bookSet) {
            System.out.println(book.getIsbn());
        }
        System.out.println("Введите ISBN книги которую нужно удалить: ");
        String isbn;
        Scanner scanner = new Scanner(System.in);
        isbn = scanner.next();
        for (Book book : bookSet) {
            if (book.getIsbn().equalsIgnoreCase(isbn)) {
                bookSet.remove(book);
                delete = true;
                break;
            }
        }
        if (!delete) {
            System.out.println("Удаление невозможно, такой книги нет");
            scanner = new Scanner(System.in);
            isbn = scanner.next();
        } else {
            BookSet bookSet1 = new BookSet(bookSet);
            bookSet1.writeInFileBookSet();
        }
        return bookSet = FileReaderAndWriter.getBookFromFile();
    }

    // команда 5
    public void changeBook() throws IOException {
        System.out.println("Все ISBN, имеющиеся в каталоге: ");
        for (Book book : bookSet) {
            System.out.println(book.getIsbn());
        }
        System.out.println("Введите ISBN книги которую хотите изменить: ");
        boolean change = false;
        Book changeBook = null;
        String isbn;
        Scanner scanner = new Scanner(System.in);
        isbn = scanner.next();
        for (Book book : bookSet) {
            if (book.getIsbn().equals(isbn)) {
                changeBook = book;
                change = true;
                break;
            }
        }
        if (change == true) {
            int command = 0;
            while (command != -1) {
                System.out.println("Что хотите изменить: " + "\n" +
                        "1 - название книги;" + "\n" +
                        "2 - автора книги;" + "\n" +
                        "3 - ISBN книги;" + "\n" +
                        "4 - описание книги;" + "\n" +
                        "5 - год издания книги;" + "\n" +
                        "6 - количество страниц;" + "\n" +
                        "7 - издательство;" + "\n" +
                        "8 - формат книги;" + "\n" +
                        "-1 - выход;" + "\n");
                System.out.println("Введите команду: ");
                Scanner scannerCommand = new Scanner(System.in);
                command = scannerCommand.nextInt();
                switch (command) {
                    case 1:
                        System.out.println("Введите название книги: ");
                        String nameBook;
                        Scanner scannerNameBook = new Scanner(System.in);
                        nameBook = scannerNameBook.nextLine();
                        changeBook.setNameBook(nameBook);
                        BookSet bookSet1 = new BookSet(bookSet);
                        bookSet1.writeInFileBookSet();
                        FileReaderAndWriter.getBookFromFile();//прочитать заново каталог
                        break;
                    case 2:
                        System.out.println("Введите атора: ");
                        String authorBook;
                        Scanner scannerAuthorBook = new Scanner(System.in);
                        authorBook = scannerAuthorBook.nextLine();
                        changeBook.setAuthor(authorBook);
                        BookSet bookSet2 = new BookSet(bookSet);
                        bookSet2.writeInFileBookSet();
                        FileReaderAndWriter.getBookFromFile();//прочитать заново каталог
                        break;
                    case 3:
                        System.out.println("Введите ISBN: ");
                        String isbnBook;
                        Scanner scannerIsbnBook = new Scanner(System.in);
                        isbnBook = scannerIsbnBook.next();
                        changeBook.setIsbn(isbnBook);
                        BookSet bookSet3 = new BookSet(bookSet);
                        bookSet3.writeInFileBookSet();
                        FileReaderAndWriter.getBookFromFile();//прочитать заново каталог
                        break;
                    case 4:
                        System.out.println("Введите описание книги: ");
                        String descriptionBook;
                        Scanner scannerDescriptionBook = new Scanner(System.in);
                        descriptionBook = scannerDescriptionBook.nextLine();
                        changeBook.setDescription(descriptionBook);
                        BookSet bookSet4 = new BookSet(bookSet);
                        bookSet4.writeInFileBookSet();
                        FileReaderAndWriter.getBookFromFile();//прочитать заново каталог
                        break;
                    case 5:
                        System.out.println("Введите год издания книги: ");
                        int yearOfPublishingBook;
                        Scanner scannerYearOfPublishingBook = new Scanner(System.in);
                        yearOfPublishingBook = scannerYearOfPublishingBook.nextInt();
                        changeBook.setYearOfPublishing(yearOfPublishingBook);
                        BookSet bookSet5 = new BookSet(bookSet);
                        bookSet5.writeInFileBookSet();
                        FileReaderAndWriter.getBookFromFile();//прочитать заново каталог
                        break;
                    case 6:
                        System.out.println("Введите количество страниц : ");
                        int numberOfPageBook;
                        Scanner scannerNumberOfPageBook = new Scanner(System.in);
                        numberOfPageBook = scannerNumberOfPageBook.nextInt();
                        changeBook.setNumberOfPage(numberOfPageBook);
                        BookSet bookSet6 = new BookSet(bookSet);
                        bookSet6.writeInFileBookSet();
                        FileReaderAndWriter.getBookFromFile();//прочитать заново каталог
                        break;
                    case 7:
                        System.out.println("Введите издательство: ");
                        String publishingBook;
                        Scanner scannerPublishingBook = new Scanner(System.in);
                        publishingBook = scannerPublishingBook.nextLine();
                        changeBook.setPublishing(publishingBook);
                        BookSet bookSet7 = new BookSet(bookSet);
                        bookSet7.writeInFileBookSet();
                        FileReaderAndWriter.getBookFromFile();//прочитать заново каталог
                        break;
                    case 8:
                        System.out.println("true-электронный формат, false-бумажный формат: ");
                        boolean eBook;
                        Scanner scannerEBook = new Scanner(System.in);
                        eBook = scannerEBook.nextBoolean();
                        changeBook.seteBook(eBook);
                        BookSet bookSet8 = new BookSet(bookSet);
                        bookSet8.writeInFileBookSet();
                        FileReaderAndWriter.getBookFromFile();//прочитать заново каталог
                        break;
                    case -1:
                        break;
                }
            }

        } else {
            System.out.println("Редактирование невозможно, такой книги нет");
            System.out.println("ISBN не найден. Повторите ввод.");
            Scanner sc = new Scanner(System.in);
            isbn = sc.next();
        }
    }
}
