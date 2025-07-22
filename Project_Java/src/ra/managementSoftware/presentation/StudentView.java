package ra.managementSoftware.presentation;

import ra.managementSoftware.business.IStudentService;
import ra.managementSoftware.business.impl.StudentServiceImpl;

import java.util.Scanner;

public class StudentView {
    private final IStudentService studentService;

    public StudentView() {
        studentService = new StudentServiceImpl();
    }

    public void displayStudentMenu(Scanner scanner) {
        boolean isExits = true;
        do {
            System.out.println("==========MENU HỌC VIÊN==========");
            System.out.println("1.Xem danh sách khoá học");
            System.out.println("2.Đăng ký khoá học");
            System.out.println("3.Xem khoá học đã đăng ký");
            System.out.println("4.Huỷ đăng ký (nếu chưa bắt đầu)");
            System.out.println("5.Đổi mật khẩu");
            System.out.println("6.Đăng xuất");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    isExits = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1 đến 7");
            }
        } while (isExits);
    }
}
