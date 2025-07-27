package ra.managementSoftware.presentation;

import ra.managementSoftware.business.ICourseService;
import ra.managementSoftware.business.IEnrollmentService;
import ra.managementSoftware.business.IStudentService;
import ra.managementSoftware.business.impl.CourseServiceImpl;
import ra.managementSoftware.business.impl.EnrollmentServiceImpl;
import ra.managementSoftware.business.impl.StudentServiceImpl;
import ra.managementSoftware.model.EnrollmentImpl;
import ra.managementSoftware.model.Student;
import ra.managementSoftware.validation.Validator;

import java.util.List;
import java.util.Scanner;

public class StudentView {
    private final IStudentService studentService;
    private final IEnrollmentService enrollmentService;
    private final ICourseService courseService;
    private final CourseView courseView;

    public StudentView() {
        studentService = new StudentServiceImpl();
        courseService = new CourseServiceImpl();
        enrollmentService = new EnrollmentServiceImpl();
        courseView = new CourseView();
    }

    public int loginByStudent(Scanner scanner) {
        do {
            System.out.println("========== ĐĂNG NHẬP HỌC VIÊN ==========");
            String email;
            String password;

            boolean validEmail = false;
            do {
                System.out.println("Nhập email đăng nhập: ");
                email = scanner.nextLine();
                if (Validator.isEmpty(email)) {
                    System.err.println("Email không được để trống");
                } else {
                    validEmail = true;
                }
            } while (!validEmail);

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

            int studentId = studentService.checkLoginStudent(email, password);
            if (studentId != 0) {
                System.out.println("Đăng nhập thành công!");
                return studentId;
            } else {
                System.err.println("Sai email hoặc mật khẩu. Vui lòng thử lại.");
            }
        } while (true);
    }

    public void studentManagerMenu(Scanner scanner) {
        boolean isExits = true;
        do {
            System.out.println("===============STUDENT MANAGER=============");
            System.out.println("1.Hiển thị danh sách học viên");
            System.out.println("2.Thêm mới học viên");
            System.out.println("3.Chỉnh sửa thông tin học viên");
            System.out.println("4.Xoá học viên");
            System.out.println("5.Tìm kiếm theo tên,email hoặc id");
            System.out.println("6.Sắp xếp theo tên hoặc id");
            System.out.println("7.Quay về menu chính");
            System.out.print("Lựa chọn của bạn: ");
            try {
                int choice = Validator.validateMenuChoice(scanner, 1, 7);
                switch (choice) {
                    case 1:
                        displayAllStudent();
                        break;
                    case 2:
                        createStudent(scanner);
                        break;
                    case 3:
                        updateStudent(scanner);
                        break;
                    case 4:
                        deleteStudent(scanner);
                        break;
                    case 5:
                        findStudent(scanner);
                        break;
                    case 6:
                        sortStudent(scanner);
                        break;
                    case 7:
                        isExits = false;
                        break;
                }
            } catch (Exception e) {
                System.err.println("Vui lòng nhập số nguyên");
            }
        } while (isExits);
    }

    public void displayAllStudent() {
        System.out.println("========================================================================STUDENT MANAGER====================================================================");
        System.out.printf("| %-7s | %-20s | %-25s | %-30s | %-5s | %20s | %25s |\n", "Id", "Name", "Date of Birth", "Email", "Sex", "Phone", "Create At");
        System.out.println("===========================================================================================================================================================");
        studentService.getAll().forEach(System.out::println);
        System.out.println("===========================================================================================================================================================");
    }

    public void createStudent(Scanner scanner) {
        Student student = new Student();
        student.inputData(scanner);
        studentService.addStudent(student);
        System.out.println("Thêm mới thành công");
    }

    public void updateStudent(Scanner scanner) {
        displayAllStudent();
        do {
            System.out.println("Nhập vào id sinh viên cần chỉnh sửa");
            String id = scanner.nextLine();
            if (!Validator.isEmpty(id)) {
                try {
                    int studentId = Integer.parseInt(id);
                    if (studentService.checkIdExist(studentId)) {
                        Student student = studentService.getById(studentId);
                        boolean exit = false;
                        do {
                            System.out.println("Lựa chọn thông tin sinh viên cần cập nhật");
                            System.out.println("1.Cập nhật tên sinh viên");
                            System.out.println("2.Cập nhật ngày sinh của sinh viên");
                            System.out.println("3.Cập nhật email sinh viên");
                            System.out.println("4.Cập nhật giới tính sinh viên");
                            System.out.println("5.Cập nhật số điện thoại sinh viên");
                            System.out.println("6.Thoát");
                            System.out.print("Lựa chọn chức năng: ");
                            try {
                                int choice = Validator.validateMenuChoice(scanner, 1, 6);
                                switch (choice) {
                                    case 1:
                                        student.setName(student.inputStudentName(scanner));
                                        break;
                                    case 2:
                                        student.setDateOfBirth(student.inputDateOfBirth(scanner));
                                        break;
                                    case 3:
                                        student.setEmail(student.inputEmail(scanner));
                                        break;
                                    case 4:
                                        student.setSex(student.inputSex(scanner));
                                        break;
                                    case 5:
                                        student.setPhone(student.inputPhone(scanner));
                                        break;
                                    case 6:
                                        boolean update = studentService.updateStudent(student);
                                        if (update) {
                                            System.out.println("Cập nhật thành công");
                                        } else {
                                            System.err.println("Cập nhật thất bại");
                                        }
                                        exit = true;
                                        break;

                                }
                            } catch (Exception e) {
                                System.out.println("Vui lòng nhập vào số nguyên");
                            }
                        } while (!exit);
                        break;
                    } else {
                        System.err.println("Id không tồn tại");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Vui lòng nhập số nguyên");
                }
            } else {
                System.err.println("Id không được để trống vui lòng nhập lại");
            }
        } while (true);
    }

    public void deleteStudent(Scanner scanner) {
        displayAllStudent();
        do {
            System.out.println("Nhập vào id sinh viên cần xoá: ");
            String idDelete = scanner.nextLine();
            if (!Validator.isEmpty(idDelete)) {
                try {
                    int numIdDelete = Integer.parseInt(idDelete);
                    if (studentService.checkIdExist(numIdDelete)) {
                        System.out.println("Bạn có muốn xoá sinh viên này không? ");
                        System.out.println("1.Có");
                        System.out.println("2.Không");
                        try {
                            int choiceNum = Validator.validateMenuChoice(scanner, 1, 2);
                            if (choiceNum == 1) {
                                boolean deleted = studentService.deleteStudent(numIdDelete);
                                if (deleted) {
                                    System.out.println("Xoá thành công");
                                } else {
                                    System.err.println("Xoá thất bại");
                                }
                            } else {
                                System.out.println("Huỷ xoá thành công");
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    } else {
                        System.err.println("Id sinh viên không tồn tại, vui lòng nhập lại");
                    }
                } catch (Exception ex) {
                    System.err.println("ID sinh viên không hợp lệ, vui lòng nhập số");
                }
                break;
            } else {
                System.err.println("Vui lòng không để trống");
            }
        } while (true);
    }

    public void findStudent(Scanner scanner) {
        displayAllStudent();
        boolean isExit = false;
        do {
            System.out.println("===============LỰA CHỌN TÌM KIẾM=============");
            System.out.println("1.Tìm kiếm theo tên");
            System.out.println("2.Tìm kiếm theo email");
            System.out.println("3.Tìm kiếm theo id");
            System.out.println("4.Thoát chức năng");
            System.out.print("Lựa chọn chức năng: ");
            try {
                int choice = Validator.validateMenuChoice(scanner, 1, 4);
                switch (choice) {
                    case 1:
                        do {
                            System.out.println("Nhập vào tên sinh viên cần tìm kiếm");
                            String nameSearch = scanner.nextLine();
                            if (!Validator.isEmpty(nameSearch)) {
                                List<Student> student = studentService.getStudentByName(nameSearch);
                                if (student != null) {
                                    System.out.println("==================================================================STUDENT MANAGER==========================================================================");
                                    System.out.printf("| %-7s | %-20s | %-25s | %-30s | %-5s | %20s | %25s |\n", "Id", "Name", "Date of Birth", "Email", "Sex", "Phone", "Create At");
                                    System.out.println("===========================================================================================================================================================");
                                    student.forEach(System.out::println);
                                    System.out.println("===========================================================================================================================================================");

                                    break;
                                } else {
                                    System.err.println("Không có sinh viên phù hợp với tên cần tìm");
                                }
                            } else {
                                System.err.println("Tên không được để trống, vui lòng nhập lại");
                            }
                        } while (true);
                        break;
                    case 2:
                        do {
                            System.out.println("Nhập vào email sinh viên cần tìm kiếm");
                            String emailSearch = scanner.nextLine();
                            if (!Validator.isEmpty(emailSearch)) {
                                List<Student> student = studentService.getStudentByEmail(emailSearch);
                                if (student != null) {
                                    System.out.println("==================================================================STUDENT MANAGER==========================================================================");
                                    System.out.printf("| %-7s | %-20s | %-25s | %-30s | %-5s | %20s | %25s |\n", "Id", "Name", "Date of Birth", "Email", "Sex", "Phone", "Create At");
                                    System.out.println("===========================================================================================================================================================");
                                    student.forEach(System.out::println);
                                    System.out.println("===========================================================================================================================================================");

                                    break;
                                } else {
                                    System.err.println("Không có sinh viên phù hợp với email cần tìm");
                                }
                            } else {
                                System.err.println("Email không được để trống, vui lòng nhập lại");
                            }
                        } while (true);
                        break;
                    case 3:
                        do {
                            System.out.println("Nhập vào id sinh viên cần tìm kiếm");
                            String idSearch = scanner.nextLine();
                            if (!Validator.isEmpty(idSearch)) {
                                List<Student> student = studentService.getStudentById(Integer.parseInt(idSearch));
                                if (student != null) {
                                    System.out.println("==================================================================STUDENT MANAGER==============================================================");
                                    System.out.printf("| %-7s | %-20s | %-25s | %-30s | %-5s | %20s | %25s |\n", "Id", "Name", "Date of Birth", "Email", "Sex", "Phone", "Create At");
                                    System.out.println("===============================================================================================================================================");
                                    student.forEach(System.out::println);
                                    System.out.println("===============================================================================================================================================");
                                    break;
                                } else {
                                    System.err.println("Không có sinh viên phù hợp với id cần tìm");
                                }
                            } else {
                                System.err.println("Id không được để trống, vui lòng nhập lại");
                            }
                        } while (true);
                        break;
                    case 4:
                        isExit = true;
                        break;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } while (!isExit);
    }

    public void sortStudent(Scanner scanner) {
        boolean exit = false;
        do {
            System.out.println("===============LỰA CHỌN SẮP XẾP=============");
            System.out.println("1.Sắp xếp theo tên");
            System.out.println("2.Sắp xếp theo id");
            System.out.println("3.Trở về menu chính");
            System.out.print("Lựa chọn chức năng: ");
            try {
                int choice = Validator.validateMenuChoice(scanner, 1, 3);
                switch (choice) {
                    case 1:
                        System.out.println("1.Sắp xếp theo tên từ A-Z");
                        System.out.println("2.Sắp xếp theo tên từ Z-A");
                        System.out.print("Lựa chọn: ");
                        try {
                            int choice1 = Validator.validateMenuChoice(scanner, 1, 2);
                            if (choice1 == 1) {
                                System.out.println("==================================================================STUDENT MANAGER==========================================================================");
                                System.out.printf("| %-7s | %-20s | %-25s | %-30s | %-5s | %20s | %25s |\n", "Id", "Name", "Date of Birth", "Email", "Sex", "Phone", "Create At");
                                System.out.println("===========================================================================================================================================================");
                                studentService.sortStudentByName(choice1).forEach(System.out::println);
                                System.out.println("===========================================================================================================================================================");
                            }
                            if (choice1 == 2) {
                                System.out.println("==================================================================STUDENT MANAGER==========================================================================");
                                System.out.printf("| %-7s | %-20s | %-25s | %-30s | %-5s | %20s | %25s |\n", "Id", "Name", "Date of Birth", "Email", "Sex", "Phone", "Create At");
                                System.out.println("===========================================================================================================================================================");

                                studentService.sortStudentById(choice1).forEach(System.out::println);
                                System.out.println("===========================================================================================================================================================");
                            }
                        } catch (Exception ex) {
                            System.err.println("Vui lòng nhập số nguyên tương ứng");
                        }
                        break;
                    case 2:
                        System.out.println("1.Sắp xếp theo id theo thứ tự tăng dần");
                        System.out.println("2.Sắp xếp theo id theo thứ tự giảm dần");
                        System.out.print("Lựa chọn: ");
                        try {
                            int choice1 = Validator.validateMenuChoice(scanner, 1, 2);
                            if (choice1 == 1) {
                                System.out.println("==================================================================STUDENT MANAGER==========================================================================");
                                System.out.printf("| %-7s | %-20s | %-25s | %-30s | %-5s | %20s | %25s |\n", "Id", "Name", "Date of Birth", "Email", "Sex", "Phone", "Create At");
                                System.out.println("===========================================================================================================================================================");

                                studentService.sortStudentById(choice1).forEach(System.out::println);
                                System.out.println("===========================================================================================================================================================");
                            }
                            if (choice1 == 2) {
                                System.out.println("==================================================================STUDENT MANAGER==========================================================================");
                                System.out.printf("| %-7s | %-20s | %-25s | %-30s | %-5s | %20s | %25s |\n", "Id", "Name", "Date of Birth", "Email", "Sex", "Phone", "Create At");
                                System.out.println("===========================================================================================================================================================");

                                studentService.sortStudentById(choice1).forEach(System.out::println);
                                System.out.println("===========================================================================================================================================================");
                            }
                        } catch (Exception ex) {
                            System.err.println("Vui lòng nhập số nguyên tương ứng");
                        }
                        break;
                    case 3:
                        exit = true;
                        break;
                }
            } catch (Exception e) {
                System.err.println("Vui lòng nhập vào số nguyên");
            }
        } while (!exit);
    }

    public void displayStudentMenu(Scanner scanner, int studentId) {
        boolean exit = false;
        do {
            System.out.println("===================MENU HỌC VIÊN=======================");
            System.out.println("1.Xem danh sách khoá học");
            System.out.println("2.Đăng ký khoá học");
            System.out.println("3.Xem khoá học đã đăng ký");
            System.out.println("4.Huỷ đăng ký");
            System.out.println("5.Đổi mật khẩu");
            System.out.println("6.Đăng xuất");
            System.out.print("Lựa chọn chức năng: ");
            try {
                int choice = Validator.validateMenuChoice(scanner, 1, 6);
                switch (choice) {
                    case 1:
                        courseView.displayAllCourses();
                        break;
                    case 2:
                        registerCourse(scanner, studentId);
                        break;
                    case 3:
                        displayCourseRegistered(studentId);
                        break;
                    case 4:
                        cancelEnrollment(scanner, studentId);
                        break;
                    case 5:
                        changePassword(scanner, studentId);
                        break;
                    case 6:
                        exit = true;
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (!exit);
    }

    public void registerCourse(Scanner scanner, int studentId) {
        do {
            courseView.displayAllCourses();
            System.out.print("Nhập ID khoá học muốn đăng ký: ");
            try {
                int courseId = Integer.parseInt(scanner.nextLine());

                if (!courseService.checkCourseIdExists(courseId)) {
                    System.err.println("Khoá học không tồn tại. Vui lòng nhập lại.");
                    continue;
                }

                if (enrollmentService.isEnrollmentExist(studentId, courseId)) {
                    System.err.println("Bạn đã đăng ký khoá học này rồi!");
                    continue;
                }

                boolean success = enrollmentService.addEnrollment(studentId, courseId);
                if (success) {
                    System.out.println("Đăng ký khoá học thành công!");
                } else {
                    System.err.println("Có lỗi xảy ra khi đăng ký. Vui lòng thử lại.");
                }
                break;
            } catch (Exception e) {
                System.err.println("Lỗi: " + e.getMessage());
            }
        } while (true);
    }

    private void displayCourseRegistered(int studentId) {
        List<EnrollmentImpl> enrollments = enrollmentService.findEnrollment(studentId);
        if (enrollments.isEmpty()) {
            System.out.println("Bạn chưa đăng ký khoá học nào.");
        } else {
            System.out.println("==========================================================Danh sách khoá học đã đăng ký:============================================================================");
            System.out.printf("| %-7s | %-15s | %-15s | %-15s | %-30s | %-20s | %-15s |\n","Id","Student Id","Student Name","Course Id","Course Name","Created At","Status");
            enrollments.forEach(System.out::println);
            System.out.println("=====================================================================================================================================================================");
        }
    }

    private void cancelEnrollment(Scanner scanner, int studentId) {
        List<EnrollmentImpl> enrollments = enrollmentService.findEnrollment(studentId);
        if (!enrollments.isEmpty()) {
            displayCourseRegistered(studentId);
            System.out.println("Nhập vào id khoá học muốn huỷ đăng kí: ");
            try {
                int courseId = Integer.parseInt(scanner.nextLine());
                if (enrollmentService.isEnrollmentExist(studentId, courseId)) {
                    String status = enrollmentService.getEnrollmentStatus(studentId, courseId);
                    if (!Validator.isEmpty(status) && !status.equalsIgnoreCase("CONFIRM")) {
                        boolean success = enrollmentService.deleteStudent(studentId, courseId);
                        if (success) {
                            System.out.println("Huỷ đăng ký thành công.");
                        } else {
                            System.err.println("Huỷ đăng ký thất bại.");
                        }
                    } else {
                        System.err.println("Khoá học đã được xác nhận, không thể huỷ.");
                    }
                } else {
                    System.err.println("Bạn chưa đăng ký khoá học này.");
                }
            } catch (NumberFormatException e) {
                System.err.println("ID không hợp lệ.");
            } catch (Exception e) {
                System.err.println("Lỗi: " + e.getMessage());
            }
        } else {
            System.err.println("Bạn chưa đăng ký khoá học nào.");
        }
    }

    private void changePassword(Scanner scanner, int studentId) {
        Student student = studentService.getById(studentId);
        System.out.println("Nhập vào email của bạn để thay đổi mật khẩu");
        do {
            String email = new Student().inputEmail(scanner);
            if (student.getEmail().equals(email)) {
                System.out.println("Nhập vào mật khẩu cũ");
                String OldPassword = scanner.nextLine();
                if (student.getPassword().equals(OldPassword)) {
                    System.out.println("Nhập vào mật khẩu mới");
                    String newPassword = scanner.nextLine();
                    if (Validator.isEmpty(newPassword)) {
                        student.setPassword(newPassword);
                        System.out.println("Thay đổi mật khẩu thành công");
                        break;
                    }
                } else {
                    System.err.println("Mật khẩu cũ không đúng,vui lòng nhập lại");
                }
            } else {
                System.err.println("Email của bạn không đúng,vui lòng nhập lại");
            }
        } while (true);

    }

}
