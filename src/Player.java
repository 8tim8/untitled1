import java.io.PrintStream;
import java.util.*;

public class Player{
    private static Object Comparator;
    private String name;
    private int score;

    Player() {
        name = "Noname";
        score = 0;
    }

    public static final Comparator<Player> comparator1 = new Comparator<Player>() {
        @Override
        public int compare(Player o1, Player o2) {
            if (o1.score >= o2.score){
                return -1;
            }
            else{
                return 1;
            }
        }
    };

    public static final Comparator<Player> comparator2 = new Comparator<Player>() {
        @Override
        public int compare(Player o1, Player o2) {
            if (o1.score >= o2.score){
                return 1;
            }
            else{
                return -1;
            }
        }
    };

    // просмотр списка через объект Iterator
    public static void printListPlayer(ArrayList<Player> list) {
        Iterator<Player> iterator = list.iterator();
        while (iterator.hasNext()) {
            Player p1 = iterator.next();
            System.out.println(p1.name + "  " + p1.score);
        }
    }

    // поиск индекса заданного игрока p по фамилии
    public static int searchBestList(ArrayList<Player> list, String name) {
        int ind = 0;
        while ((ind < (list.size()))
                && !(list.get(ind).name.equals(name))) {
            ind++;
        }
        if (ind == list.size())
            ind = -1;
        return ind;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        PrintStream out = new PrintStream(System.out);

        ArrayList<Player> list = new ArrayList<Player>();
        int choice = 0;

        do {
            Player p = new Player();
            int ind = -1;
            // System.out.println("\nСписок игроков:");
            // printListPlayer(list);

            out.println("\nВыберите:");
            out.println("1 - Добавить игрока");
            out.println("2 - Удалить игрока (по индексу)");
            out.println("3 - Изменить запись об игроке");
            out.println("4 - Добавление лучшего результата игрока");
            out.println("5 - Вывести весь список игроков");
            out.println("0 - Выход");
            out.print("Ваш выбор: ");
            choice = in.nextInt();
            out.println("\n\n");

            switch (choice) {
                case 1: // Добавление нового результата
                    // (фамилия игрока может повторяться)
                    out.println("Добавление результата игрока ");
                    out.print("Введите фамилию: ");
                    p.name = in.next();
                    out.print("Введите очки: ");
                    p.score = in.nextInt();
                    list.add(p);
                    break;

                case 2: // Удаление игрока из списка по индексу
                    out.println("Удаление по индексу");
                    if (list.size() != 0) {
                        out.print("Введите индекс: ");
                        ind = in.nextInt();
                        if ((ind >= 0) && (ind < list.size())) {
                            list.remove(ind);
                        } else
                            out.print("Введите индекс от 0 до " +
                                    (list.size() - 1));
                    } else
                        out.print("Список пуст. Удаление невозможно");
                    break;
                case 3: // Изменение объекта по индексу
                    out.println("Изменение данных об игроке");
                    if (list.size() != 0) {
                        out.print("Введите индекс: ");
                        ind = in.nextInt();
                        if ((ind >= 0) && (ind < list.size())) {
                            out.print("Введите фамилию: ");
                            p.name = in.next();
                            out.print("Введите очки: ");
                            p.score = in.nextInt();
                            list.set(ind, p);
                        } else
                            out.print("Введите индекс от 0 до " +
                                    (list.size() - 1));
                    } else
                        out.print("Список пуст. Изменение невозможно");
                    break;

                case 4:  // Добавление лучшего результата игрока
                    out.println("Добавление лучшего результата игрока");
                    out.print("Введите фамилию :");
                    p.name = in.next();
                    out.print("Введите очки: ");
                    p.score = in.nextInt();
                    ind = searchBestList(list, p.name);
                    if (ind == -1) {
                        list.add(p);
                    } else if (list.get(ind).score < p.score)
                        list.set(ind, p);
                    break;

                case 5: // Вывод списка всех игроков
                    out.println("1 - Вывести список в произвольном порядке");
                    out.println("2 - Вывести список в порядке убывания очков");
                    out.println("3 - Вывести список в порядке возрастания очков");
                    out.print("Ваш выбор: ");
                    int choice1 = in.nextInt();
                    out.println();
                    out.println();
                    out.println();
                    switch (choice1){
                        case 1:
                            printListPlayer(list);
                            break;

                        case 2:
                            Collections.sort(list, Player.comparator1);
                            printListPlayer(list);
                            break;

                        case 3:
                            Collections.sort(list, Player.comparator2);
                            printListPlayer(list);
                            break;
                    }
                    break;
            }
        }
        while (choice != 0);
        in.close();
        out.println("Программа завершена");
    }

}
