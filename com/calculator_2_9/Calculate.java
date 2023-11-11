package com.calculator_2_9;

import java.util.Scanner;
import java.text.MessageFormat;

public class Calculate {
    private static double result = 0;
    private static final int FIRST_CHAR = 0;
    private static Operation NOW_OPERATION = Operation.START;
    private static final String ASK_FIRST_ARGUMENT = "Введите первый аргумент:";
    private static final String RESULT = "Результат операции равен: {0,number}";
    private static final String CALCULATE_OPERATION = """
            Выберите и введите необходимую операцию:
            '/' - деление
            '*' - умножение
            '-' - вычитание
            '+' - сложение
            """;
    private static final String SYSTEM_OPERATON = """
            Выберете что делать теперь:
            'P' - продолжить выполнять операции вычисления с текущим результатом: {0, number}
            'C' - сброс текущего результа до 0 и продолежние вычисления
            's' - остановка программы
            """;
    private static final String ASK_ARGUMENT = "Введите следующий аргумент";
    private static final String OPERATION_NOT_FOUND = "Операция не найдена, повторите ввод:";

    public static void main(String[] args) {
        start();
    }

    private static void start() {
        Scanner scanner = new Scanner(System.in);
        double firstArgument;
        double secondArgument;

        while (NOW_OPERATION != Operation.STOP) {
            if (NOW_OPERATION == Operation.START) {
                startOperation(scanner);
                askNextDo(scanner);
            }
            if (NOW_OPERATION == Operation.CONTINIU) {
                progressOperation(scanner);
                askNextDo(scanner);
            }
        }
    }

    private static void startOperation(Scanner scanner) {
        boolean validateOperation = false;
        System.out.println(ASK_FIRST_ARGUMENT);
        double firstArgument = scanner.nextDouble();
        char operation = getOperation(scanner, true);

        System.out.println(ASK_ARGUMENT);
        double secondArgument = scanner.nextDouble();
        calculate(firstArgument, operation, secondArgument);
    }

    private static void progressOperation(Scanner scanner) {
        boolean validateOperation = false;
        char operation = getOperation(scanner, true);
        System.out.println(ASK_ARGUMENT);
        double secondArgument = scanner.nextDouble();
        calculate(result, operation, secondArgument);
    }

    private static void calculate(double first, char operation, double second) {
        if (operation == Operation.DELENIE.getTitle()) {
            result = first / second;
        }
        if (operation == Operation.UMNOJENIE.getTitle()) {
            result = first * second;
        }
        if (operation == Operation.SLOJENIE.getTitle()) {
            result = first + second;
        }
        if (operation == Operation.VICHITANIE.getTitle()) {
            result = first - second;
        }
        System.out.println(MessageFormat.format(RESULT, result));
    }
    private static boolean validateCalculateOperation(char operation) {
        return operation == Operation.DELENIE.getTitle() || operation == Operation.UMNOJENIE.getTitle() || operation == Operation.SLOJENIE.getTitle() || operation == Operation.VICHITANIE.getTitle();
    }
    private static boolean validateSystemOperation(char operation) {
        return operation == Operation.CONTINIU.getTitle() || operation == Operation.RESET.getTitle() || operation == Operation.STOP.getTitle();
    }

    private static void askNextDo(Scanner scanner) {
        boolean validateOperation = false;
        char operation = getOperation(scanner, false);

        if (operation == Operation.CONTINIU.getTitle()) {
            NOW_OPERATION = Operation.CONTINIU;
        }
        if (operation == Operation.RESET.getTitle()) {
            NOW_OPERATION = Operation.CONTINIU;
            result = 0;
        }
        if (operation == Operation.STOP.getTitle()) {
            NOW_OPERATION = Operation.STOP;
        }
    }

    private static char getOperation(Scanner scanner, boolean isCalculateOperation) {
        boolean validateOperation = false;
        char operation;
        String message;

        if (isCalculateOperation) {
            message = CALCULATE_OPERATION;
        } else {
            message = MessageFormat.format(SYSTEM_OPERATON, result);
        }
        System.out.println(message);
        do {
            operation = scanner.next().charAt(FIRST_CHAR);
            validateOperation = isCalculateOperation ? validateCalculateOperation(operation) : validateSystemOperation(operation);
            if (!validateOperation) {
                System.out.println(OPERATION_NOT_FOUND);
            }
        } while (!validateOperation);

        return operation;
    }
}
