package part_6.task_3.client.helper;

public class MenuUser {
    public static String menu(boolean isAdmin) {
        String menu = null;
        if (isAdmin == true) {
            menu = "1-просмотр дела; 2-внести изменения в дело; 3-создать новое дело; exit-выход из программы; введите команду: ";
        } else {
            menu = "1-просмотр дела; exit-выход из программы; введите команду: ";
        }
        return menu;
    }
}
