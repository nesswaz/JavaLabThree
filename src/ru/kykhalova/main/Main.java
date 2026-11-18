package ru.kykhalova.main;

import java.util.Scanner;

// статический импорт для коротких имен методов parseInt и pow - 7.3
import static java.lang.Integer.parseInt;
import static java.lang.Math.pow;

import ru.kykhalova.time.Time;
import ru.kykhalova.company.Employee;
import ru.kykhalova.company.Department;
import ru.kykhalova.structures.Node;
import ru.kykhalova.geometry.Point;
import ru.kykhalova.geometry.PointXYZ;
import ru.kykhalova.birds.Birds;
import ru.kykhalova.birds.Sparrow;
import ru.kykhalova.birds.Cuckoo;
import ru.kykhalova.birds.Parrot;

public class Main {

    public static int readInt(Scanner scanner, String message) {
        System.out.print(message);

        while (!scanner.hasNextInt()) {
            System.out.println("Ошибка! Введите целое число.");
            scanner.next();
            System.out.print(message);
        }

        int number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    public static int readNonNegativeInt(Scanner scanner, String message) {
        int number;
        while (true) {
            number = readInt(scanner, message);
            if (number >= 0) {
                return number;
            }
            System.out.println("Число не может быть отрицательным. Попробуйте ещё раз.");
        }
    }

    public static int readPositiveInt(Scanner scanner, String message) {
        while (true) {
            int number = readInt(scanner, message);

            if (number > 0) {
                return number;
            }

            System.out.println("Число должно быть больше нуля. Попробуйте ещё раз.");
        }
    }

    public static String readNonEmptyString(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String str = scanner.nextLine().trim();
            if (!str.isEmpty()) {
                return str;
            }
            System.out.println("Строка не может быть пустой. Попробуйте ещё раз.");
        }
    }

    // задача 5.2 - птичий рынок
    public static void birdMarket(Birds[] birds) {
        System.out.println("\nПтичий рынок поет:");
        for (int i = 0; i < birds.length; i++) {
            System.out.print((i + 1) + ") " + birds[i] + " -> ");
            birds[i].sing();
        }
    }

    // 7.3 - возведение в степень с короткими именами
    public static double power(String xStr, String yStr) {
        int x = parseInt(xStr); // короткое имя вместо Integer.parseInt
        int y = parseInt(yStr);
        return pow(x, y); // короткое имя вместо Math.pow
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Department savedDepartment = null; // сохраненный отдел для проверки между кейсами

        String menu = """
                Задание 1:
                1) - 1.2 Время от начала суток
                2) - 1.11 Начальник отдела (и Задача 8.1 — пакетная инкапсуляция)
                Задание 2:
                3) - 2.4 Бинарное дерево
                Задание 3:
                4) - 3.5 Трехмерная точка
                Задание 4 и 5:
                5) - 4.3 и 5.2 Птицы и Птичий рынок
                Задание 6:
                6) - 6.2 Сравнение точек (equals)
                Задание 7:
                7) - 7.3 Возведение в степень
                8) - Выход из программы
                """;

        System.out.print(menu);
        while (true) {
            System.out.print("\nВыберите один из пунктов от 0 до 8 (0 - показать меню): ");

            while (!scanner.hasNextInt()) {
                System.out.println("Ошибка! Введите целое число");
                scanner.next();
                System.out.print("Ваш выбор: ");
            }
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 8) {
                System.out.println("Программа завершена.");
                break;
            }

            if (choice == 0) {
                System.out.print("\n" + menu);
                continue;
            }

            switch (choice) {
                case 1: {
                    System.out.println("\n1.2. Время от начала суток");
                    int seconds = readInt(scanner, "Введите количество секунд: ");

                    try {
                        Time time = new Time(seconds);
                        System.out.println("Время: " + time);
                        System.out.println("Часы: " + time.getHours() + ", Минуты: " + time.getMinutes() + ", Секунды: " + time.getSeconds());
                    } catch (RuntimeException e) { // запоминает ошибку в переменную e
                        System.out.println("Ошибка: " + e.getMessage()); // с помощью метода вывод этой ошибки на экран
                    }
                    break;
                }

                case 2: {
                    System.out.println("\n1.11 и 8.1. Сотрудники и Начальник отдела");

                    String departmentName = readNonEmptyString(scanner, "Введите название отдела: ");
                    Department department = new Department(departmentName);
                    savedDepartment = department;

                    int count = readPositiveInt(scanner, "Введите количество обычных сотрудников: ");
                    for (int i = 0; i < count; i++) {
                        String empName = readNonEmptyString(scanner, "Введите имя сотрудника " + (i + 1) + ": ");
                        Employee emp = new Employee(empName);
                        emp.setDepartment(department);
                    }

                    System.out.println("\nСоздание начальника");
                    String managerName = readNonEmptyString(scanner, "Введите имя начальника отдела: ");
                    Employee manager = new Employee(managerName);
                    savedDepartment.setManager(manager);

                    System.out.println("\nИнформация об отделе: " + savedDepartment);
                    System.out.println("Список всех сотрудников отдела " + savedDepartment.getName() + ":");
                    for (Employee e : savedDepartment.getEmployees()) {
                        System.out.println(e);
                    }
                    break;
                }

                case 3: {
                    System.out.println("\n2.4. Бинарное дерево");
                    Node tree = new Node();

                    int count = readPositiveInt(scanner, "Сколько чисел добавить в дерево: ");
                    for (int i = 0; i < count; i++) {
                        int val = readInt(scanner, "Введите число " + (i + 1) + ": ");
                        tree.add(val);
                    }

                    System.out.println("Текстовое представление дерева (левосторонний обход): " + tree);

                    int searchVal = readInt(scanner, "Введите число для поиска в дереве: ");
                    System.out.println("Есть ли число " + searchVal + " в дереве: " + tree.contains(searchVal));

                    int delVal = readInt(scanner, "Введите число для удаления из дерева: ");
                    tree.remove(delVal);
                    System.out.println("Дерево после удаления числа " + delVal + ": " + tree);
                    break;
                }
                case 4: {
                    System.out.println("\n3.5. Трехмерная точка");
                    int x = readInt(scanner, "Введите координату X: ");
                    int y = readInt(scanner, "Введите координату Y: ");
                    int z = readInt(scanner, "Введите координату Z: ");

                    PointXYZ point3D = new PointXYZ(x, y, z);
                    System.out.println("Создана трехмерная точка: " + point3D);
                    break;
                }

                case 5: {
                    System.out.println("\n4.3 и 5.2. Птицы и Птичий рынок");
                    int count = readPositiveInt(scanner, "Введите количество птиц на птичьем рынке: ");
                    Birds[] birds = new Birds[count];

                    for (int i = 0; i < count; i++) {
                        System.out.println("\nКого добавить под номером " + (i + 1) + " (1 - Воробей, 2 - Кукушка, 3 - Попугай)");
                        int type = readPositiveInt(scanner, "Ваш выбор: ");
                        if (type == 1) {
                            birds[i] = new Sparrow();
                        }
                        else if (type == 2) {
                            birds[i] = new Cuckoo();
                        }
                        else {
                            String text = readNonEmptyString(scanner, "Введите текст для попугая: ");
                            birds[i] = new Parrot(text);
                        }
                    }
                    birdMarket(birds);
                    break;
                }

                case 6: {
                    System.out.println("\n6.2. Сравнение точек");
                    System.out.println("Первая точка:");
                    int x1 = readInt(scanner, "Введите X1: ");
                    int y1 = readInt(scanner, "Введите Y1: ");
                    Point p1 = new Point(x1, y1);

                    System.out.println("Вторая точка:");
                    int x2 = readInt(scanner, "Введите X2: ");
                    int y2 = readInt(scanner, "Введите Y2: ");
                    Point p2 = new Point(x2, y2);

                    System.out.println("Точка 1: " + p1);
                    System.out.println("Точка 2: " + p2);
                    System.out.println("Точки одинаковые - " + p1.equals(p2));
                    break;
                }

                case 7: {
                    System.out.println("\n7.3. Возведение в степень");
                    // если переданы параметры командной строки args:
                    if (args.length >= 2) {
                        try {
                            System.out.println("Из параметров командной строки (" + args[0] + "^" + args[1] + "): " + power(args[0], args[1]));
                        } catch (NumberFormatException e) {
                            System.out.println("Ошибка! Параметры командной строки должны быть целыми числами.");
                        }
                    }

                    System.out.print("Введите строку X (основание): ");
                    String xStr = scanner.nextLine();

                    System.out.print("Введите строку Y (степень): ");
                    String yStr = scanner.nextLine();

                    try {
                        double result = power(xStr, yStr);
                        System.out.println("Результат возведения " + xStr + " в степень " + yStr + " = " + result);
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка! В строках должны быть записаны целые числа.");
                    }
                    break;
                }

                default: {
                    System.out.println("Неверный выбор! Введите число от 0 до 8");
                    break;
                }
            }
        }
    }
}