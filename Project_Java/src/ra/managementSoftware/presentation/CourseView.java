package ra.managementSoftware.presentation;

import ra.managementSoftware.business.ICourseService;
import ra.managementSoftware.business.impl.CourseServiceImpl;
import ra.managementSoftware.model.Course;
import ra.managementSoftware.validation.Validator;

import java.util.List;
import java.util.Scanner;

public class CourseView {
    private final ICourseService courseService;


    public CourseView() {
        courseService = new CourseServiceImpl();
    }

    public void menuCourse(Scanner scanner) {
        boolean exit = false;
        do {
            System.out.println("===========MENU COURSE===========");
            System.out.println("1.Hiển thị danh sách khoá học");
            System.out.println("2.Thêm mới khoá học");
            System.out.println("3.Chỉnh sửa thông tin khoá học");
            System.out.println("4.Xoá khoá học");
            System.out.println("5.Tìm kiếm theo tên");
            System.out.println("6.Sắp xếp theo tên hoặc id(tăng/giảm dần)");
            System.out.println("7.Quay về menu chính");
            System.out.print("Lựa chọn: ");
            int choice = Validator.validateMenuChoice(scanner, 1, 7);
            switch (choice) {
                case 1:
                    displayAllCourses();
                    break;
                case 2:
                    createCourse(scanner);
                    break;
                case 3:
                    updateCourse(scanner);
                    break;
                case 4:
                    deleteCourse(scanner);
                    break;
                case 5:
                    findCourseByName(scanner);
                    break;
                case 6:
                    menuCase6(scanner);
                    break;
                case 7:
                    exit = true;
                    break;
            }
        } while (!exit);
    }

    public void displayAllCourses() {
        System.out.println("==================================================COURSE LIST=============================================");
        System.out.printf("| %-5s | %-30s | %-10s | %-25s | %-20s |\n","Id", "Name", "Duration", "Instructor", "Create At");
        courseService.getAll().forEach(System.out::println);
        System.out.println("==========================================================================================================");
    }

    public void createCourse(Scanner scanner) {
        Course course = new Course();
        course.inputData(scanner);
        courseService.addCourse(course);
        System.out.println("Thêm mới thành công");
    }

    public void updateCourse(Scanner scanner) {
        displayAllCourses();
        System.out.println("Nhập vào id khoá học cần cập nhật: ");
        String idUpdate = scanner.nextLine();
        if (!Validator.isEmpty(idUpdate)) {
            try {
                int numIdUpdate = Integer.parseInt(idUpdate);
                if (courseService.checkCourseIdExists(numIdUpdate)) {
                    Course course = courseService.getCourseById(numIdUpdate);
                    boolean exit = false;
                    do {
                        System.out.println("========CHỌN CHỨC NĂNG UPDATE=========");
                        System.out.println("1.Cập nhật tên khoá học");
                        System.out.println("2.Cập nhật thời lượng khoá học");
                        System.out.println("3.Cập nhật giảng viên phụ trách");
                        System.out.println("4.Thoát");
                        try {
                            int choice = Validator.validateMenuChoice(scanner, 1, 4);
                            switch (choice) {
                                case 1:
                                    course.inputCourseName(scanner);
                                    break;
                                case 2:
                                    course.inputCourseDuration(scanner);
                                    break;
                                case 3:
                                    course.inputCourseInstructor(scanner);
                                    break;
                                case 4:
                                    boolean update = courseService.updateCourse(course);
                                    if (update) {
                                        System.out.println("Cập nhật thành công");
                                    } else {
                                        System.err.println("Cập nhật thất bại");
                                    }
                                    exit = true;
                                    break;
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                    } while (!exit);
                } else {
                    System.err.println("Id khoá học không tồn tại, vui lòng nhập lại");
                }
            }catch (NumberFormatException e) {
                System.err.println("ID không hợp lệ, vui lòng nhập số");
            }
        } else {
            System.err.println("Vui lòng không để trống id");
        }
    }

    public void deleteCourse(Scanner scanner) {
        displayAllCourses();
        System.out.println("Nhập vào id khoá học cần xoá: ");
        String idDelete = scanner.nextLine();
        if (!Validator.isEmpty(idDelete)) {
            try {
                int numIdDelete = Integer.parseInt(idDelete);
                if (courseService.checkCourseIdExists(numIdDelete)) {
                    System.out.println("Bạn có muốn xoá khoá học này không? ");
                    System.out.println("1.Có");
                    System.out.println("2.Không");
                    try {
                        String choice = scanner.nextLine();
                        int choiceNum = Validator.validateMenuChoice(scanner, 1, 2);
                        if (choiceNum == 1 || choice.equalsIgnoreCase("có")) {
                            courseService.deleteCourse(numIdDelete);
                            System.out.println("Xoá thành công");
                        } else {
                            System.out.println("Huỷ xoá thành công");
                        }
                    }catch (Exception ex){
                        System.out.println(ex.getMessage());
                    }
                } else {
                    System.err.println("Id khoá học không tồn tại, vui lòng nhập lại");
                }
            }catch (Exception ex) {
                System.err.println("ID khoá học không hợp lệ, vui lòng nhập số");
            }
        } else {
            System.err.println("Vui lòng không để trống");
        }
    }

    public void findCourseByName(Scanner scanner) {
        System.out.println("Nhập vào tên khoá học cần tìm kiếm");
        String nameSearch = scanner.nextLine();
        if (!Validator.isEmpty(nameSearch)) {
            List<Course> course = courseService.getCourseByName(nameSearch);
            if (course != null) {
                course.forEach(System.out::println);
            } else {
                System.err.println("Không có tên khoá học cần tìm");
            }
        } else {
            System.err.println("Vui lòng không để trống");
        }
    }

    public void menuCase6(Scanner scanner) {
        boolean exit = false;
        do {
            System.out.println("===============LỰA CHỌN SẮP XẾP=============");
            System.out.println("1.Sắp xếp theo tên");
            System.out.println("2.Sắp xếp theo id");
            System.out.println("3.Thoát");
            try {
                int choice = Validator.validateMenuChoice(scanner, 1, 3);
                switch (choice) {
                    case 1:
                        System.out.println("Lựa chọn sắp xếp theo tên");
                        System.out.println("1.Sắp xếp theo tên từ A-Z");
                        System.out.println("2.Sắp xếp theo tên từ Z-A");
                        System.out.print("Lựa chọn: ");
                        try {
                            int choice1 = Validator.validateMenuChoice(scanner, 1, 2);
                            if (choice1 == 1) {
                                System.out.println("==================================================COURSE LIST=============================================");
                                System.out.printf("| %-5s | %-30s | %-10s | %-25s | %-20s |\n","id", "name", "duration", "instructor", "createAt");
                                courseService.sortByName(choice1).forEach(System.out::println);
                                System.out.println("==========================================================================================================");
                            }
                            if (choice1 == 2) {
                                System.out.println("==================================================COURSE LIST=============================================");
                                System.out.printf("| %-5s | %-30s | %-10s | %-25s | %-20s |\n","id", "name", "duration", "instructor", "createAt");
                                courseService.sortByName(choice1).forEach(System.out::println);
                                System.out.println("==========================================================================================================");


                            }
                        }catch (Exception ex) {
                            System.err.println("Vui lòng nhập số nguyên"+ex.getMessage());
                        }
                        break;
                    case 2:
                        System.out.println("Lựa chọn sắp xếp theo id");
                        System.out.println("1.Sắp xếp theo id tăng dần");
                        System.out.println("2.Sắp xếp theo id giảm dần");
                        System.out.print("Lựa chọn: ");
                        try {
                            int choice1 = Validator.validateMenuChoice(scanner, 1, 2);
                            if (choice1 == 1) {
                                System.out.println("==================================================COURSE LIST=============================================");
                                System.out.printf("| %-5s | %-30s | %-10s | %-25s | %-20s |\n","id", "name", "duration", "instructor", "createAt");
                                courseService.sortById(choice1).forEach(System.out::println);
                                System.out.println("==========================================================================================================");
                            }
                            if (choice1 == 2) {
                                System.out.println("==================================================COURSE LIST=============================================");
                                System.out.printf("| %-5s | %-30s | %-10s | %-25s | %-20s |\n","id", "name", "duration", "instructor", "createAt");
                                courseService.sortById(choice1).forEach(System.out::println);
                                System.out.println("==========================================================================================================");
                            }
                        }catch (Exception ex) {
                            System.err.println("Vui lòng nhập số nguyên"+ex.getMessage());
                        }
                        break;
                    case 3:
                        exit = true;
                        break;
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }while (!exit);
    }
}
