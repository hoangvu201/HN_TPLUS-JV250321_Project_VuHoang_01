package ra.managementSoftware.model;

import ra.managementSoftware.business.IStudentService;
import ra.managementSoftware.business.impl.StudentServiceImpl;
import ra.managementSoftware.dao.IStudentDAO;
import ra.managementSoftware.dao.impl.StudentDAOImpl;
import ra.managementSoftware.validation.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import static java.time.LocalDate.now;

public class Student {
    private int id;
    private String name;
    private LocalDate dateOfBirth;
    private String email;
    private boolean sex;
    private String phone;
    private String password;
    private LocalDate createAt;

    private final IStudentService studentService =  new StudentServiceImpl();


    public Student() {
    }

    public Student(int id, String name, LocalDate dateOfBirth, String email, boolean sex, String phone, String password, LocalDate createAt) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.sex = sex;
        this.phone = phone;
        this.password = password;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return String.format("| %-7d | %-20s | %-25s | %-30s | %-5s | %20s | %25s |",
                this.id, this.name, this.dateOfBirth, this.email,
                this.sex ? "Nam" : "Nữ", this.phone, this.createAt);
    }

    public void inputData(Scanner sc) {
        this.name = inputStudentName(sc);
        this.dateOfBirth = inputDateOfBirth(sc);
        this.email = inputEmail(sc);
        this.sex = inputSex(sc);
        this.phone = inputPhone(sc);
        this.createAt = now();
    }

    public String inputStudentName(Scanner sc) {
        do {
            System.out.println("Nhập vào tên sinh viên");
            String studentName = sc.nextLine();
            if (!Validator.isEmpty(studentName)) {
                return studentName;
            } else {
                System.err.println("Tên sinh viên không được để trống");
            }
        } while (true);
    }

    public LocalDate inputDateOfBirth(Scanner sc) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        do {
            System.out.println("Nhập vào ngày sinh của học viên (định dạng yyyy-MM-dd):");
            String dateOfBirth = sc.nextLine().trim();
            if (!Validator.isEmpty(dateOfBirth)) {
                try {
                    LocalDate localDate = LocalDate.parse(dateOfBirth, dtf);
                    if (localDate.isBefore(now())) {
                        return localDate;
                    } else {
                        System.err.println("Ngày sinh không được lớn hơn ngày hiện tại");
                    }
                } catch (DateTimeParseException e) {
                    System.err.println("Lỗi: Không đúng định dạng yyyy-MM-dd, vui lòng nhập lại!");
                }
            } else {
                System.err.println("Ngày sinh không được để trống, vui lòng nhập lại!");
            }
        }while (true);
    }


    public String inputEmail(Scanner sc) {
        String regexEmail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
        do {
            System.out.println("Nhập vào email sinh viên");
            String email = sc.nextLine();
            if (!Validator.isEmpty(email)) {
                if (email.matches(regexEmail) && studentService.checkEmailExist(email)) {
                    return email;
                } else {
                    System.err.println("Email không đúng định dạng vui lòng nhập lại");
                }
            } else {
                System.err.println("Email không được để trống");
            }
        } while (true);
    }

    public boolean inputSex(Scanner sc) {
        do {
            System.out.println("Nhập vào giới tính sinh viên (chọn 1 hoặc 2)");
            System.out.println("1.Nam");
            System.out.println("2.Nữ");
            try {
                int choice = Validator.validateMenuChoice(sc, 1, 2);
                if (choice == 1) {
                    return true;
                }
                if (choice == 2) {
                    return false;
                }
            } catch (Exception e) {
                System.err.println("Vui lòng nhập vào số nguyên");
            }
        } while (true);

    }

    public String inputPhone(Scanner sc) {
        String regexPhone = "^(\\+84|84|0)(3|5|7|8|9)[0-9]{8}$";
        do {
            System.out.println("Nhập vào số điện thoại");
            String phone = sc.nextLine();
            if (phone.matches(regexPhone)) {
                return phone;
            } else {
                System.err.println("Số điện thoại không đúng định dạng, vui lòng nhập lại");
            }
        } while (true);
    }

}
