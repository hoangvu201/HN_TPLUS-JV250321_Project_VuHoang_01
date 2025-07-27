package ra.managementSoftware.presentation;

import ra.managementSoftware.business.ICourseService;
import ra.managementSoftware.business.IEnrollmentService;
import ra.managementSoftware.business.IStudentService;
import ra.managementSoftware.business.impl.CourseServiceImpl;
import ra.managementSoftware.business.impl.EnrollmentServiceImpl;
import ra.managementSoftware.business.impl.StudentServiceImpl;
import ra.managementSoftware.validation.Validator;

import java.util.Scanner;

public class EnrollmentView {
    private final IEnrollmentService enrollmentService;
    private final ICourseService courseService;
    private final IStudentService studentService;
    private final StudentView studentView;
    private final CourseView courseView;


    public EnrollmentView() {
        enrollmentService = new EnrollmentServiceImpl();
        studentView = new StudentView();
        courseView = new CourseView();
        courseService = new CourseServiceImpl();
        studentService = new StudentServiceImpl();
    }

    public void enrollmentMenu(Scanner scanner) {
        boolean exit = false;
        do {
            System.out.println("========================ENROLLMENT MANAGEMENT MENU===================");
            System.out.println("1.Hiển thị học viên theo từng khoá học");
            System.out.println("2.Thêm học viên vào khoá học");
            System.out.println("3.Xoá học viên khỏi khoá học");
            System.out.println("4.Quay về menu chính");
            System.out.print("Lựa chọn chức năng: ");
            try {
                int choice = Validator.validateMenuChoice(scanner, 1, 4);
                switch (choice) {
                    case 1:
                        displayAllEnrollments();
                        break;
                    case 2:
                        addStudentIntoCourse(scanner);
                        break;
                    case 3:
                        deleteStudentFromCourse(scanner);
                        break;
                    case 4:
                        exit = true;
                        break;
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
                }
            } while (!exit);
    }

    public void displayAllEnrollments() {
        System.out.println("==========================================================Danh sách khoá học đã đăng ký============================================================================");
        System.out.printf("| %-7s | %-15s | %-15s | %-15s | %-30s | %-20s | %-15s |\n","Id","Student Id","Student Name","Course Id","Course Name","Created At","Status");
        enrollmentService.findAll().forEach(System.out::println);
        System.out.println("=====================================================================================================================================================================");
    }
    public void addStudentIntoCourse(Scanner scanner) {
        courseView.displayAllCourses();
        do {
            System.out.println("Lựa chọn id khóa học cần thêm sinh viên");
            try {
                int courseId = Integer.parseInt(scanner.nextLine());
                if (courseService.checkCourseIdExists(courseId)) {
                    studentView.displayAllStudent();
                    do {
                        System.out.println("Chọn id sinh viên muốn thêm vào khoá học");
                        int studentId = Integer.parseInt(scanner.nextLine());
                        try {
                            if (studentService.checkIdExist(studentId)) {
                                 boolean success = enrollmentService.addEnrollment(studentId, courseId);
                                 if (success){
                                     System.out.println("Thêm mới thành công");
                                 } else {
                                     System.err.println("Thêm mới thất bại");
                                 }
                                break;
                            } else {
                                System.err.println("ID học sinh ko tồn tại vui lòng nhập lại");
                            }
                        }catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }while (true);
                    break;
                } else {
                    System.err.println("ID khoá học không tồn tại,vui lòng nhập lại");
                }

            }catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }while (true);

    }

    public void deleteStudentFromCourse(Scanner scanner) {
        courseView.displayAllCourses();
        do{
            System.out.println("Nhập vào ID khoá học muốn xoá sinh viên:");
            try {
                int courseId = Integer.parseInt(scanner.nextLine());
                if (courseService.checkCourseIdExists(courseId)) {
                    studentView.displayAllStudent();
                    System.out.println("Nhập vào ID sinh viên muốn xoá khỏi khoá học:");
                    int studentId = Integer.parseInt(scanner.nextLine());
                    if (studentService.checkIdExist(studentId)) {
                        boolean deleted = enrollmentService.deleteStudent(studentId, courseId);
                        if (deleted) {
                            System.out.println("Đã xoá sinh viên khỏi khoá học thành công!");
                        } else {
                            System.err.println("Sinh viên không đăng ký khoá học này hoặc lỗi khi xoá.");
                        }
                        break;
                    } else {
                        System.err.println("ID sinh viên không tồn tại.");
                    }
                } else {
                    System.err.println("ID khoá học không đúng, vui lòng nhập lại.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập số hợp lệ.");
            } catch (Exception e) {
                System.err.println("Lỗi: " + e.getMessage());
            }
        } while (true);
    }

}
