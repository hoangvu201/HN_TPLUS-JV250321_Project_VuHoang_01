package ra.managementSoftware.dao.impl;

import ra.managementSoftware.dao.IStudentDAO;
import ra.managementSoftware.model.Student;
import ra.managementSoftware.utils.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StudentDAOImpl implements IStudentDAO {
    @Override
    public int loginStudent(String email, String password) {
        Connection conn = null;
        CallableStatement callStmt = null;
        int studentId = -1;
        try {
            conn = DBUtil.openConnection();
            callStmt = conn.prepareCall("{call login_by_student(?,?,?)}");
            callStmt.setString(1, email);
            callStmt.setString(2, password);
            callStmt.registerOutParameter(3, Types.INTEGER);
            callStmt.execute();
            studentId = callStmt.getInt(3);
        } catch (Exception e) {
            System.err.println("Lỗi khi đăng nhập Học viên: " + e.getMessage());
        } finally {
            DBUtil.closeConnection(conn, callStmt);
        }
        return studentId;
    }

    @Override
    public List<Student> getAll() {
        Connection conn = null;
        CallableStatement stmt = null;
        List<Student> list = null;
        try {
            conn = DBUtil.openConnection();
            stmt = conn.prepareCall("{call get_all_student()}");
            ResultSet rs = stmt.executeQuery();
            list = new ArrayList<>();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setDateOfBirth(LocalDate.parse(rs.getString("dob"), dtf));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setSex((rs.getBoolean("sex")));
                student.setPassword(rs.getString("password"));
                student.setCreateAt(rs.getDate("create_at").toLocalDate());
                list.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, stmt);
        }
        return list;
    }

    @Override
    public Student getById(int id) {
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Student student = null;
        try {
            conn = DBUtil.openConnection();
            cstmt = conn.prepareCall("{call get_student_by_id(?)}");
            cstmt.setInt(1, id);
            rs = cstmt.executeQuery();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setDateOfBirth(LocalDate.parse(rs.getString("dob"), dtf));
                student.setSex(rs.getBoolean("sex"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setPassword(rs.getString("password"));
                student.setCreateAt(rs.getDate("create_at").toLocalDate());
            }
        } catch (Exception e) {
            System.err.println("Có lỗi trong quá trình lấy dữ liệu" + e.getMessage());
        } finally {
            DBUtil.closeConnection(conn, cstmt);
        }
        return student;
    }

    @Override
    public boolean addStudent(Student student) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = DBUtil.openConnection();
            cstmt = conn.prepareCall("{call create_student(?,?,?,?,?,?)}");
            cstmt.setString(1, student.getName());
            cstmt.setDate(2, Date.valueOf(student.getDateOfBirth()));
            cstmt.setString(3, student.getEmail());
            cstmt.setBoolean(4, student.isSex());
            cstmt.setString(5, student.getPhone());
            cstmt.setString(6, student.getPassword());
            int rs = cstmt.executeUpdate();
            if (rs > 0) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Có lỗi trong quá trình thêm mới sinh viên " + e.getMessage());
        } finally {
            DBUtil.closeConnection(conn, cstmt);
        }
        return false;
    }

    @Override
    public boolean updateStudent(Student student) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = DBUtil.openConnection();
            cstmt = conn.prepareCall("{call update_student_by_id(?,?,?,?,?,?)}");
            cstmt.setInt(1, student.getId());
            cstmt.setString(2, student.getName());
            cstmt.setDate(3, Date.valueOf(student.getDateOfBirth()));
            cstmt.setString(4, student.getEmail());
            cstmt.setBoolean(5, student.isSex());
            cstmt.setString(6, student.getPhone());
            int rs = cstmt.executeUpdate();
            if (rs > 0) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Có lỗi trong quá trình cập nhật sinh viên " + e.getMessage());
        } finally {
            DBUtil.closeConnection(conn, cstmt);
        }
        return false;
    }

    @Override
    public boolean deleteStudent(int id) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = DBUtil.openConnection();
            cstmt = conn.prepareCall("{call delete_student_by_id(?)}");
            cstmt.setInt(1, id);
            int rs = cstmt.executeUpdate();
            if (rs > 0) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Có lỗi trong quá trình xoá sinh viên " + e.getMessage());
        } finally {
            DBUtil.closeConnection(conn, cstmt);
        }
        return false;
    }

    @Override
    public List<Student> findStudentByEmail(String email) {
        Connection conn = null;
        CallableStatement cstmt = null;
        List<Student> studentList = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.openConnection();
            cstmt = conn.prepareCall("{call find_student_by_email(?)}");
            cstmt.setString(1, email);
            studentList = new LinkedList<>();
            rs = cstmt.executeQuery();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while (rs.next()) {
                extractedStudents(rs, dtf, studentList);
            }
        } catch (Exception e) {
            System.err.println("Có lỗi trong quá trình tìm sinh viên");
        } finally {
            DBUtil.closeConnection(conn, cstmt);
        }
        return studentList;
    }

    @Override
    public List<Student> findStudentByName(String name) {
        Connection conn = null;
        CallableStatement cstmt = null;
        List<Student> studentList = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.openConnection();
            cstmt = conn.prepareCall("{call find_student_by_name(?)}");
            cstmt.setString(1, name);
            studentList = new LinkedList<>();
            rs = cstmt.executeQuery();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while (rs.next()) {
                extractedStudents(rs, dtf, studentList);
            }
        } catch (Exception e) {
            System.err.println("Có lỗi trong quá trình tìm sinh viên");
        } finally {
            DBUtil.closeConnection(conn, cstmt);
        }
        return studentList;
    }

    @Override
    public List<Student> findStudentById(int id) {
        Connection conn = null;
        CallableStatement cstmt = null;
        List<Student> studentList = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.openConnection();
            cstmt = conn.prepareCall("{call find_student_by_id(?)}");
            cstmt.setInt(1, id);
            studentList = new LinkedList<>();
            rs = cstmt.executeQuery();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while (rs.next()) {
                extractedStudents(rs, dtf, studentList);
            }
        } catch (Exception e) {
            System.err.println("Có lỗi trong quá trình tìm sinh viên");
        } finally {
            DBUtil.closeConnection(conn, cstmt);
        }
        return studentList;
    }

    @Override
    public boolean checkEmailExist(String email) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = DBUtil.openConnection();
            cstmt = conn.prepareCall("{call check_email_exists(?)}");
            cstmt.setString(1, email);
            boolean isExist = cstmt.execute();
            if (isExist) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Có lỗi xảy ra trong quá trình lấy dữ liệu");
        } finally {
            DBUtil.closeConnection(conn, cstmt);
        }
        return false;
    }

    @Override
    public boolean checkIdExist(int id) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = DBUtil.openConnection();
            cstmt = conn.prepareCall("{call check_student_id_exists(?,?)}");
            cstmt.setInt(1, id);
            cstmt.registerOutParameter(2, java.sql.Types.INTEGER);
            cstmt.execute();
            int result =  cstmt.getInt(2);
            if (result > 0) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Có lỗi xảy ra trong quá trình lấy dữ liệu");
        } finally {
            DBUtil.closeConnection(conn, cstmt);
        }
        return false;
    }

    @Override
    public List<Student> sortStudentById(int number) {
        Connection conn = null;
        CallableStatement cstmt = null;
        List<Student> studentList = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.openConnection();
            cstmt = conn.prepareCall("{call sort_student_by_id(?)}");
            cstmt.setInt(1, number);
            studentList = new ArrayList<>();
            rs = cstmt.executeQuery();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while (rs.next()) {
                extractedStudents(rs,dtf,studentList);
            }
        } catch (Exception e) {
            System.err.println("Có lỗi trong quá trình lấy dữ liệu");
        } finally {
            DBUtil.closeConnection(conn, cstmt);
        }
        return studentList;
    }

    private static void extractedStudents(ResultSet rs, DateTimeFormatter dtf, List<Student> studentList) throws SQLException {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setName(rs.getString("name"));
        student.setDateOfBirth(LocalDate.parse(rs.getString("dob"), dtf));
        student.setSex(rs.getBoolean("sex"));
        student.setEmail(rs.getString("email"));
        student.setPhone(rs.getString("phone"));
        student.setPassword(rs.getString("password"));
        student.setCreateAt(rs.getDate("create_at").toLocalDate());
        studentList.add(student);
    }


}
