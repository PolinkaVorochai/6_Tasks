package part_6.task_4;

import part_6.task_4.bean.Dock;
import part_6.task_4.bean.Port;
import part_6.task_4.bean.Ship;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
Многопоточность. Порт . Корабли заходят в порт для разгрузки/загрузки контейнеров. Число контейнеров,
находящихся в текущий момент в порту и на корабле, должно быть неотрицательным и превышающим заданную
грузоподъемность судна и вместимость порта.
В порту работает несколько причалов.
У одного причала может стоять один корабль.
Корабль может загружаться у причала или разгружаться.
 */
public class Run {
    private static BlockingQueue<Ship> queue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {
        ArrayList<Ship> ships = new ArrayList<>();
        ships.add(new Ship(1, 10, 0, true));
        ships.add(new Ship(2, 10, 10, false));
        ships.add(new Ship(3, 10, 10, false));
        ships.add(new Ship(4, 10, 0, true));
        ships.add(new Ship(5, 10, 10, false));
        ships.add(new Ship(6, 10, 0, true));
        Port port = new Port(50, 30);
        ArrayList<Dock> docks = new ArrayList<>();
        docks.add(new Dock(1, port));
        docks.add(new Dock(2, port));
        docks.add(new Dock(3, port));

        Runnable ship = () -> {
            try {
                for (Ship ship1 : ships) {
                    queue.put(ship1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(ship).start();

        while (!end(queue))
            for (Dock dock : docks) {
                Runnable dockRunnable = () -> {
                    try {
                        Ship shipTake = queue.take();
                        boolean done = false;
                        while (!done) {
                            if (shipTake.isLoad()) {
                                boolean able = port.testDecrement();
                                if (able) {
                                    if (shipTake.isStatus()) {
                                        done = true;
                                        break;
                                    } else {
                                        shipTake.run();
                                        port.decrement();
                                    }
                                } else {
                                    if (shipTake.isStatus()) {
                                        done = true;
                                        break;
                                    } else {
                                        queue.put(shipTake);
                                        done = true;
                                    }
                                }
                            } else {
                                boolean able = port.testIncrement();
                                if (able) {
                                    if (shipTake.isStatus()) {
                                        done = true;
                                        break;
                                    } else {
                                        shipTake.run();
                                        port.increment();
                                    }
                                } else {
                                    if (shipTake.isStatus()) {
                                        done = true;
                                        break;
                                    } else {
                                        queue.put(shipTake);
                                        done = true;
                                    }
                                }

                            }
                        }
                        System.out.println("Причал " + dock.getId() + " корабль " + shipTake.getId() + " разгружен/загружен " + shipTake.isStatus());
                    } catch (InterruptedException e) {

                    }
                };
                new Thread(dockRunnable).start();
            }
    }

    static boolean end(BlockingQueue<Ship> ships) {
        for (Ship ship : ships) {
            if (!ship.isStatus()) {
                return false;
            }
        }
        return true;
    }
}