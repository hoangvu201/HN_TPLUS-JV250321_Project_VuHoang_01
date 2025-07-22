package ra.managementSoftware.presentation;

import ra.managementSoftware.business.IStudentService;
import ra.managementSoftware.business.impl.StudentServiceImpl;
import ra.managementSoftware.validation.Validator;

import java.util.Scanner;

public class StudentView {
    private final IStudentService studentService;

    public StudentView() {
        studentService = new StudentServiceImpl();
    }

    public boolean loginByStudent(Scanner scanner) {
        do {
            System.out.println("===== ĐĂNG NHẬP HỌC VIÊN =====");
            System.out.println("Nhập email đăng nhập: ");
            String email = scanner.nextLine();
            System.out.println("Nhập mật khẩu: ");
            String password = scanner.nextLine();

            if (!Validator.isEmpty(email) && !Validator.isEmpty(password)) {
                boolean success = studentService.checkLoginStudent(email, password);
                if (success) {
                    System.out.println("Đăng nhập thành công!");
                    return true;
                } else {
                    System.err.println("Sai tên đăng nhập hoặc mật khẩu. Vui lòng thử lại.");
                }
            } else {
                System.err.println("Tên đăng nhập và mật khẩu không được để trống");
            }
        }while (true);
    };

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
