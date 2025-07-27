package ra.managementSoftware.dao.impl;

import ra.managementSoftware.dao.IEnrollmentDAO;
import ra.managementSoftware.model.EnrollmentImpl;
import ra.managementSoftware.model.EnrollmentStatus;
import ra.managementSoftware.utils.DBUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAOImpl implements IEnrollmentDAO {
    @Override
    public List<EnrollmentImpl> findAll() {
        Connection conn = null;
        CallableStatement stmt = null;
        List<EnrollmentImpl> enrollmentImpl = null;
        ResultSet rs = null;
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            conn = DBUtil.openConnection();
            stmt = conn.prepareCall("{call get_all_enrollment()}");
            stmt.executeQuery();
            rs = stmt.getResultSet();
            enrollmentImpl = new ArrayList<>();
            while (rs.next()) {
                EnrollmentImpl enrollment = new EnrollmentImpl();
                enrollment.setId(rs.getInt("Enrollment_id"));
                enrollment.setStudentId(rs.getInt("Student_id"));
                enrollment.setStudentName(rs.getString("Student_name"));
                enrollment.setCourseId(rs.getInt("Course_id"));
                enrollment.setCourseName(rs.getString("Course_name"));
                enrollment.setRegistered_at(LocalDateTime.parse(rs.getString("registered_at"), dtf));
                enrollment.setStatus(EnrollmentStatus.valueOf(rs.getString("status")));
                enrollmentImpl.add(enrollment);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, stmt);
        }
        return enrollmentImpl;
    }

    @Override
    public boolean addEnrollment(int idStudent, int idCourse) {
        Connection conn = null;
        CallableStatement stmt = null;
        try {
            conn = DBUtil.openConnection();
            stmt = conn.prepareCall("{call enrollment_student(?,?)}");
            stmt.setInt(1, idStudent);
            stmt.setInt(2, idCourse);
            int rs = stmt.executeUpdate();
            if (rs > 0) {
                return true;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            DBUtil.closeConnection(conn, stmt);
        }
        return false;
    }


    @Override
    public boolean deleteStudent(int idStudent, int idCourse) {
        Connection conn = null;
        CallableStatement stmt = null;
        try {
            conn = DBUtil.openConnection();
            stmt = conn.prepareCall("{call delete_student_from_course(?,?)}");
            stmt.setInt(1, idStudent);
            stmt.setInt(2, idCourse);
            int delete = stmt.executeUpdate();
            if (delete > 0) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Có lỗi trong quá trình xoá sinh viên khỏi khoá học");
        } finally {
            DBUtil.closeConnection(conn, stmt);
        }
        return false;
    }

    @Override
    public boolean isEnrollmentExist(int idStudent, int idCourse) {
        Connection conn = null;
        CallableStatement stmt = null;
        try {
            conn = DBUtil.openConnection();
            stmt = conn.prepareCall(("{call is_enrollment_exists(?,?,?)}"));
            stmt.setInt(1, idStudent);
            stmt.setInt(2, idCourse);
            stmt.setInt(3, Types.INTEGER);
            stmt.executeQuery();
            int rs = stmt.getInt(3);
            if (rs > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, stmt);
        }
        return false;
    }

    @Override
    public List<EnrollmentImpl> findEnrollment(int idStudent) {
        Connection conn = null;
        CallableStatement stmt = null;
        List<EnrollmentImpl> enrollmentImpl = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.openConnection();
            stmt = conn.prepareCall("{call find_course_by_student_id(?)}");
            stmt.setInt(1, idStudent);
            rs = stmt.executeQuery();
            enrollmentImpl = new ArrayList<>();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            while (rs.next()) {
                EnrollmentImpl enrollment = new EnrollmentImpl();
                enrollment.setId(rs.getInt("Enrollment_id"));
                enrollment.setStudentId(rs.getInt("Student_id"));
                enrollment.setStudentName(rs.getString("Student_name"));
                enrollment.setCourseId(rs.getInt("Course_id"));
                enrollment.setCourseName(rs.getString("Course_name"));
                enrollment.setRegistered_at(LocalDateTime.parse(rs.getString("registered_at"), dtf));
                enrollment.setStatus(EnrollmentStatus.valueOf(rs.getString("status")));
                enrollmentImpl.add(enrollment);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            DBUtil.closeConnection(conn, stmt);
        }
        return enrollmentImpl;
    }

    @Override
    public boolean getStatusByIdEnrollment(int idEnrollment) {
        Connection conn = null;
        CallableStatement stmt = null;

        return false;
    }

    @Override
    public String getEnrollmentStatus(int studentId, int courseId) {
        Connection conn = null;
        CallableStatement cstmt = null;
        String status = null;
        try {
            conn = DBUtil.openConnection();
            cstmt = conn.prepareCall("{call get_status_by_student_id(?,?,?)}");
            cstmt.setInt(1, studentId);
            cstmt.setInt(2, courseId);
            cstmt.registerOutParameter(3, Types.VARCHAR);
            cstmt.execute();
            status = cstmt.getString(3);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, cstmt);
        }
        return status;
    }

}
