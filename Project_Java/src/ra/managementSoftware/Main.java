package ra.managementSoftware;

import ra.managementSoftware.business.IAdminService;
import ra.managementSoftware.business.IStudentService;
import ra.managementSoftware.business.impl.AdminServiceImpl;
import ra.managementSoftware.business.impl.StudentServiceImpl;
import ra.managementSoftware.presentation.AdminView;
import ra.managementSoftware.presentation.StudentView;
import ra.managementSoftware.validation.Validator;

import java.util.Scanner;

public class Main {
    private final IAdminService adminService;
    private final IStudentService studentService;
    private final StudentView studentView ;

    public Main() {
        adminService = new AdminServiceImpl();
        studentService = new StudentServiceImpl();
        studentView = new StudentView();
    }

    public static void main(String[] args) {
        AdminView adminView = new AdminView();
        StudentView studentView = new StudentView();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("=========HỆ THỐNG QUẢN LÝ ĐÀO TẠO===========");
            System.out.println("1.Đăng nhập với tư cách Quản trị viên");
            System.out.println("2.Đăng nhập với tư cách Học viên");
            System.out.println("3.Thoát");
            System.out.println("============================================");
            System.out.print("Nhập lựa chọn: ");
            int choice = Validator.validateMenuChoice(scanner,1,3);
            switch (choice) {
                case 1:
                    if(adminView.loginByAdmin(scanner)) {
                        adminView.menuAdmin(scanner);
                    }
                    break;
                case 2:
                    int studentId = studentView.loginByStudent(scanner);
                    if (studentId > 0){
                        studentView.displayStudentMenu(scanner, studentId);
                    }
                    break;
                case 3:
                    System.exit(0);
            }
        } while (true);
    }


}