package part_6.task_4.bean;

/*
Многопоточность. Порт . Корабли заходят в порт для разгрузки/загрузки контейнеров. Число контейнеров,
находящихся в текущий момент в порту и на корабле, должно быть неотрицательным и превышающим заданную
грузоподъемность судна и вместимость порта.
В порту работает несколько причалов.
У одного причала может стоять один корабль.
Корабль может загружаться у причала или разгружаться.
 */
public class Ship {
    private int id;
    private int max;
    private int now;
    private boolean load;
    private boolean status = false;

    public Ship(int id, int max, int now, boolean load) {
        this.id = id;
        this.max = max;
        this.now = now;
        this.load = load;
    }


    public void run() {
        if (load) {
            increment();
            if (now == max) {
                status = true;
            }
        } else {
            decrement();
            if (now == 0) {
                status = true;
            }
        }
    }

    public void increment() {
        now++;
    }

    public void decrement() {
        now--;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getNow() {
        return now;
    }

    public void setNow(int now) {
        this.now = now;
    }

    public boolean isLoad() {
        return load;
    }

    public void setLoad(boolean load) {
        this.load = load;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Корабль " + id + " ");
        string.append("грузоподъемность судна  " + max + " контейнеров " + "\n");
        string.append("текущий груз на судне  " + now + "  контейнеров" + "\n");
        string.append("судно ожидает зазгрузки   " + load + "\n");
        string.append("судно разгрузилось/загрузилось в порту   " + status + "\n");
        return string.toString();
    }
}
