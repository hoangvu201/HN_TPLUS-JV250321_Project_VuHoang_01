package ra.managementSoftware.presentation;

import ra.managementSoftware.business.IAdminService;
import ra.managementSoftware.business.impl.AdminServiceImpl;
import ra.managementSoftware.validation.Validator;

import java.util.Scanner;

public class AdminView {
    private final IAdminService adminService = new AdminServiceImpl();

    public boolean loginByAdmin(Scanner scanner) {
        do {
            System.out.println("===== ĐĂNG NHẬP ADMIN =====");
            System.out.println("Nhập tên đăng nhập: ");
            String username = scanner.nextLine();
            System.out.println("Nhập mật khẩu: ");
            String password = scanner.nextLine();

            if (!Validator.isEmpty(username) && !Validator.isEmpty(password)) {
                boolean success = adminService.checkLoginAdmin(username, password);
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

    public static void menuAdmin(Scanner scanner) {
        boolean exit = false;
        do {
            System.out.println("============ MENU ADMIN ============");
            System.out.println("1.Quản lý khoá học");
            System.out.println("2.Quản lý học viên");
            System.out.println("3.Quản lý đăng ký học");
            System.out.println("4.Thống kê học viên theo khoá học");
            System.out.println("5.Đăng xuất");
            System.out.println("=====================================");
            System.out.print("Nhập lựa chọn: ");
            int choice = Validator.validateMenuChoice(scanner,1,5);
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
                    exit = true;
                    break;
            }
        } while (!exit);
    }
}
