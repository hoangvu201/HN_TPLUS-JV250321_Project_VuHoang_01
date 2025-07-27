package ra.managementSoftware.model;

import ra.managementSoftware.validation.Validator;

import java.time.LocalDate;
import java.util.Scanner;

import static java.time.LocalDate.now;

public class Course {
    private int id;
    private String name;
    private int duration;
    private String instructor;
    private LocalDate createAt;

    public Course() {
    }

    public Course(int id, String name, int duration, String instructor, LocalDate createAt) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.instructor = instructor;
        this.createAt = createAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return String.format("| %-5d | %-30s | %-10d | %-25s | %-20s |", this.id, this.name, this.duration,this.instructor, this.createAt);
    }

    public void inputData (Scanner scanner) {
        this.name =  inputCourseName(scanner);
        this.duration = inputCourseDuration(scanner);
        this.instructor = inputCourseInstructor(scanner);
        this.createAt = now();
    }

    public String inputCourseName (Scanner scanner) {
        do {
            System.out.println("Nhập vào tên khoá học: ");
            String name = scanner.nextLine();
            if (!Validator.isEmpty(name)) {
                return name;
            } else {
                System.err.println("Vui lòng không để trống");;
            }
        }while (true);
    }

    public int inputCourseDuration (Scanner scanner) {
        do {
            System.out.println("Nhập vào thời lượng khoá học: ");
            String duration = scanner.nextLine();
            if (!Validator.isEmpty(duration)) {
                try {
                    int durationInt = Integer.parseInt(duration);
                    if (durationInt > 0) {
                        return durationInt;
                    }
                    else {
                        System.err.println("Thời lượng khoá học phải lớn hơn 0");
                    }
                }catch (NumberFormatException e) {
                    System.err.println("Vui lòng nhập số" + e.getMessage());
                }
            }

        }while (true);
    }

    public String inputCourseInstructor (Scanner scanner) {
        do {
            System.out.println("Nhập vào tên giảng viên phụ trách: ");
            String instructor = scanner.nextLine();
            if (!Validator.isEmpty(instructor)) {
                return instructor;
            } else {
                System.err.println("Không để trống tên giảng viên, vui lòng nhập lại");
            }
        }while (true);
    }
 }
