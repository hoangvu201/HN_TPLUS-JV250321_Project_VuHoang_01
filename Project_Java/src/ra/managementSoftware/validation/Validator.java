package ra.managementSoftware.validation;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Validator {
    public static boolean isEmpty(String string) {
        if (string == null || string.trim().isEmpty()) {
            return true;
        }
        return false;
    }
    public static int validateMenuChoice(Scanner scanner, int min, int max) {
        while (true) {
            String input = scanner.nextLine();
            if (!input.matches("\\d+")) {
                System.err.print("Vui lòng nhập số hợp lệ: ");
                continue;
            }

            int choice = Integer.parseInt(input);
            if (choice < min || choice > max) {
                System.err.print("Lựa chọn phải trong khoảng từ " + min + " đến " + max + ": ");
                continue;
            }

            return choice;
        }
    }
}
