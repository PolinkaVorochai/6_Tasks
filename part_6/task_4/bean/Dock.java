package part_6.task_4.bean;

/*
Многопоточность. Порт . Корабли заходят в порт для разгрузки/загрузки контейнеров. Число контейнеров,
находящихся в текущий момент в порту и на корабле, должно быть неотрицательным и превышающим заданную
грузоподъемность судна и вместимость порта.
В порту работает несколько причалов.
У одного причала может стоять один корабль.
Корабль может загружаться у причала или разгружаться.
 */
public class Dock  {
    private int id;
    private Port port;

    public Dock(int id, Port port) {
        this.id = id;
        this.port = port;
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("На пристани " + id + " \n");
        return string.toString();
    }
}
