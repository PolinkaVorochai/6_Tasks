package part_6.task_1.bean;

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
public class Book {
    String nameBook;
    String author;
    String isbn;
    String description;
    int yearOfPublishing;
    int numberOfPage;
    String publishing;
    boolean eBook;

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(int numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    public String getPublishing() {
        return publishing;
    }

    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }

    public boolean iseBook() {
        return eBook;
    }

    public void seteBook(boolean eBook) {
        this.eBook = eBook;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Наименование книги: " + nameBook + "\n");
        stringBuilder.append("Автор: " + author + "\n");
        stringBuilder.append("ISBN: " + isbn + "\n");
        stringBuilder.append("Аннотация: " + description + "\n");
        stringBuilder.append("Год издания: " + yearOfPublishing + "\n");
        stringBuilder.append("Количество страниц: " + numberOfPage + "\n");
        stringBuilder.append("Издательство: " + publishing + "\n");
        stringBuilder.append("Электронная книга: " + eBook + "\n");
        return stringBuilder.toString();
    }
}
