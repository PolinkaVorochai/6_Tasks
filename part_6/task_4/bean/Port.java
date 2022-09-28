package part_6.task_4.bean;

import java.util.ArrayList;

/*
Многопоточность. Порт . Корабли заходят в порт для разгрузки/загрузки контейнеров. Число контейнеров,
находящихся в текущий момент в порту и на корабле, должно быть неотрицательным и превышающим заданную
грузоподъемность судна и вместимость порта.
В порту работает несколько причалов.
У одного причала может стоять один корабль.
Корабль может загружаться у причала или разгружаться.
 */
public class Port {
    int max;
    int now;

    public Port(int max, int now) {
        this.max = max;
        this.now = now;
    }

    public int getMax() {
        return max;
    }

    public int getNow() {
        return now;
    }


    public synchronized void increment() {
        now++;
    }

    public synchronized void decrement() {
        now--;
    }

    public synchronized boolean testIncrement() {
        if (now == max) {
            return false;
        } else return true;
    }

    public synchronized boolean testDecrement() {
        if (now == 0) {
            return false;
        } else return true;
    }


    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Порт: " + " вместимость " + max + " ,наличие контейнеров " + now);
        return string.toString();
    }
}
