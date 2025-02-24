
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private final static Scanner scanner = new Scanner(System.in);
    private final static ArrayList<Phone> phoneList = new ArrayList<>();

    public static void main(String[] args) {
        createPhoneArr();

        int threshold = getIntInput("Введіть поріг часу в хвилинах: ");
        cityTimeExceed(threshold);

        useOutCity();

        long range1 = getLongInput("Введіть перший номер рахунку діапазону: ");
        long range2 = getLongInput("Введіть другий номер рахунку діапазону: ");
        accountNumRange(range1, range2);
    }

    private static void createPhoneArr() {
        while (true) {
            System.out.print("Введіть дані про користувача або '0' для завершення вводу\n");
            int id = getIntInput("ID: ");

            if (id == 0) {
                break;
            }

            scanner.nextLine(); // очищуємо після nextInt()

            System.out.println("Ім'я користувача: ");
            String firstName = getStringInput();
            System.out.println("Прізвище: ");
            String lastName = getStringInput();
            System.out.println("По батькові: ");
            String middleName = getStringInput();

            long accountNum = getLongInput("Номер рахунку: ");
            scanner.nextLine(); // очищуємо після nextLong()

            int timeInCity = getIntInput("Час міських розмов в хвилинах: ");
            int timeOutCity = getIntInput("Час розмов поза містом: ");

            phoneList.add(new Phone(id, firstName, lastName, middleName, accountNum, timeInCity, timeOutCity));
        }
    }

    private static void cityTimeExceed(int threshold) {
        System.out.println("\nАбоненти, у яких час міських розмов перевищує заданий поріг: ");
        for (Phone phone : phoneList) {
            if (phone.getTimeInCity() > threshold) {
                System.out.print(phone);
            }
        }
    }

    private static void useOutCity() {
        System.out.println("\nАбоненти, які користувались міжміським зв'язком: ");
        for (Phone phone : phoneList) {
            if (phone.getTimeOutCity() > 0) {
                System.out.print(phone);
            }
        }
    }

    private static void accountNumRange(long range1, long range2) {
        System.out.println("\nАбоненти, чий номер рахунку знаходиться в заданому діапазоні (" + range1 + " та " + range2 + "):");
        for (Phone phone : phoneList) {
            if (phone.getAccountNum() >= range1 && phone.getAccountNum() <= range2) {
                System.out.print(phone);
            }
        }
    }

    private static int getIntInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Введіть правильне числове значення.");
                scanner.next(); // очищуємо некоректне введення
            }
        }
    }

    private static long getLongInput(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return scanner.nextLong();
            } catch (InputMismatchException e) {
                System.out.println("Введіть правильне числове значення.");
                scanner.next(); // очищуємо некоректне введення
            }
        }
    }

    private static String getStringInput() {
        while (true) {
            String input = scanner.nextLine();
            if (input.matches("[a-zA-Zа-яА-Я]+")) { // Перевіряємо, що введення містить лише літери
                return input;
            } else {
                System.out.println("Введіть правильне текстове значення (тільки букви).");
            }
        }
    }
}