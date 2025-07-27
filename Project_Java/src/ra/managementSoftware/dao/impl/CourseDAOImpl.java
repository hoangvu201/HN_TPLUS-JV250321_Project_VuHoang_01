package ra.managementSoftware.dao.impl;

import ra.managementSoftware.dao.ICourseDAO;
import ra.managementSoftware.model.Course;
import ra.managementSoftware.utils.DBUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements ICourseDAO {
    @Override
    public List<Course> getAll() {
        Connection conn = null;
        CallableStatement cstmt = null;
        List<Course> courses = null;
        try {
            conn = DBUtil.openConnection();
            cstmt = conn.prepareCall("{call get_all_course()}");
            ResultSet rs = cstmt.executeQuery();
            courses = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreateAt(LocalDate.parse(rs.getString("create_at"), formatter));
                courses.add(course);
            }
        } catch (Exception e) {
            System.err.println("Có lỗi trong quá trình hiển thị danh sách khoá học " + e.getMessage());
        } finally {
            DBUtil.closeConnection(conn, cstmt);
        }
        return courses;
    }

    @Override
    public boolean addCourse(Course course) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = DBUtil.openConnection();
            cstmt = conn.prepareCall("{call create_course(?,?,?)}");
            cstmt.setString(1, course.getName());
            cstmt.setInt(2, course.getDuration());
            cstmt.setString(3, course.getInstructor());
            int rs = cstmt.executeUpdate();
            if (rs > 0) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Có lỗi trong quá trình thêm mới khoá học ");
        } finally {
            DBUtil.closeConnection(conn, cstmt);
        }
        return false;
    }

    @Override
    public boolean updateCourse(Course course) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = DBUtil.openConnection();
            cstmt = conn.prepareCall("{call update_course_by_id(?,?,?,?)}");
            cstmt.setInt(1, course.getId());
            cstmt.setString(2, course.getName());
            cstmt.setInt(3, course.getDuration());
            cstmt.setString(4, course.getInstructor());
            int rs = cstmt.executeUpdate();
            if (rs > 0) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Có lỗi trong quá trình cập nhật khoá học " + e.getMessage());
        } finally {
            DBUtil.closeConnection(conn, cstmt);
        }
        return false;
    }

    @Override
    public boolean deleteCourse(int id) {
        Connection conn = null;
        CallableStatement cstmt = null;
        try {
            conn = DBUtil.openConnection();
            cstmt = conn.prepareCall("{call delete_course_by_id(?)}");
            cstmt.setInt(1, id);
            int rs = cstmt.executeUpdate();
            if (rs > 0) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Có lỗi trong quá trình xoá khoá học " + e.getMessage());
        } finally {
            DBUtil.closeConnection(conn, cstmt);
        }

        return false;
    }

    @Override
    public boolean checkCourseIdExists(int id) {
        Connection conn = null;
        CallableStatement cstmt = null;
        int rs = 0;
        try {
            conn = DBUtil.openConnection();
            cstmt = conn.prepareCall("{call check_course_id_exists(?,?)}");
            cstmt.setInt(1, id);
            cstmt.registerOutParameter(2, Types.INTEGER);
            cstmt.execute();
            rs = cstmt.getInt(2);
            if (rs > 0) {
                return true;
            }
        } catch (Exception e) {
            System.err.println("Có lỗi trong quá trình kiểm tra Id " + e.getMessage());
        } finally {
            DBUtil.closeConnection(conn, cstmt);
        }
        return false;
    }

    @Override
    public List<Course> getCourseByName(String name) {
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<Course> courses = null;
        try {
            conn = DBUtil.openConnection();
            cstmt = conn.prepareCall("{call find_course_by_name(?)}");
            cstmt.setString(1, name);
            rs = cstmt.executeQuery();
            courses = new ArrayList<>();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreateAt(LocalDate.parse(rs.getString("create_at")));
                courses.add(course);
            }
        } catch (Exception e) {
            System.err.println("Có lỗi trong quá trình lấy dữ liệu");
        } finally {
            DBUtil.closeConnection(conn, cstmt);
        }
        return courses;
    }

    @Override
    public Course getCourseById(int id) {
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Course course = null;
        try {
            conn = DBUtil.openConnection();
            cstmt = conn.prepareCall("{call get_course_by_id(?)}");
            cstmt.setInt(1, id);
            rs = cstmt.executeQuery();
            if (rs.next()) {
                course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreateAt(LocalDate.parse(rs.getString("create_at")));
            }
        } catch (Exception e) {
            System.err.println("Có lỗi trong quá trình lấy dữ liệu" + e.getMessage());
        } finally {
            DBUtil.closeConnection(conn, cstmt);
        }
        return course;
    }

    @Override
    public List<Course> sortById(int number) {
        Connection conn = null;
        CallableStatement cstmt = null;
        List<Course> courses = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.openConnection();
            cstmt = conn.prepareCall("{call sort_course_by_id(?)}");
            cstmt.setInt(1, number);
            courses = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            rs = cstmt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreateAt(LocalDate.parse(rs.getString("create_at"), formatter));
                courses.add(course);
            }
        } catch (Exception e) {
            System.err.println("Có lỗi trong quá trình lấy dữ liệu" + e.getMessage());
        } finally {
            DBUtil.closeConnection(conn, cstmt);
        }
        return courses;
    }

    @Override
    public List<Course> sortByName(int number) {
        Connection conn = null;
        CallableStatement cstmt = null;
        List<Course> courses = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.openConnection();
            cstmt = conn.prepareCall("{call sort_course_by_name(?)}");
            cstmt.setInt(1, number);
            courses = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            rs = cstmt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreateAt(LocalDate.parse(rs.getString("create_at"), formatter));
                courses.add(course);
            }
        } catch (Exception e) {
            System.err.println("Có lỗi trong quá trình lấy dữ liệu" + e.getMessage());
        } finally {
            DBUtil.closeConnection(conn, cstmt);
        }
        return courses;
    }

}
