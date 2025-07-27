package ra.managementSoftware.presentation;

import ra.managementSoftware.business.IAdminService;
import ra.managementSoftware.business.impl.AdminServiceImpl;
import ra.managementSoftware.validation.Validator;

import java.util.Scanner;

public class AdminView {
    private final IAdminService adminService ;
    private final CourseView courseView ;
    private final StudentView studentView ;
    private final EnrollmentView enrollmentView ;
    private final StatisticView statisticView ;

    public AdminView() {
        adminService = new AdminServiceImpl();
        courseView = new CourseView();
        studentView = new StudentView();
        enrollmentView = new EnrollmentView();
        statisticView = new StatisticView();
    }

    public boolean loginByAdmin(Scanner scanner) {
        do {
            System.out.println("============= ĐĂNG NHẬP ADMIN ==========");
            String username ;
            String password ;

            boolean validUsername = false;
            do {
                System.out.println("Nhập tên đăng nhập: ");
                username = scanner.nextLine();
                if (Validator.isEmpty(username)) {
                    System.err.println("Tên đăng nhập không được để trống");
                } else {
                    validUsername = true;
                }
            } while (!validUsername);

            boolean validPassword = false;
            do {
                System.out.println("Nhập mật khẩu: ");
                password = scanner.nextLine();
                if (Validator.isEmpty(password)) {
                    System.err.println("Mật khẩu không được để trống");
                } else {
                    validPassword = true;
                }
            } while (!validPassword);

            boolean success = adminService.checkLoginAdmin(username, password);
            if (success) {
                System.out.println("Đăng nhập thành công!");
                return true;
            } else {
                System.err.println("Sai tên đăng nhập hoặc mật khẩu. Vui lòng thử lại.\n");
            }
        } while (true);
    }


    public void menuAdmin(Scanner scanner) {
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
                    courseView.menuCourse(scanner);
                    break;
                case 2:
                    studentView.studentManagerMenu(scanner);
                    break;
                case 3:
                    enrollmentView.enrollmentMenu(scanner);
                    break;
                case 4:
                    statisticView.menuStatisticView(scanner);
                    break;
                case 5:
                    exit = true;
                    break;
            }
        } while (!exit);
    }
}
