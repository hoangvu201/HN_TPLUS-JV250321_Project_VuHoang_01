package ra.managementSoftware.dao.impl;

import ra.managementSoftware.dao.ICourseDAO;
import ra.managementSoftware.model.Course;
import ra.managementSoftware.utils.DBUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
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
                course.setCreateAt(LocalDate.parse(rs.getString("create_at"),formatter));
                courses.add(course);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            DBUtil.closeConnection(conn,cstmt);
        }
        return courses;
    }

    @Override
    public Course getById(int id) {

        return null;
    }

    @Override
    public boolean addCourse(Course course) {
        return false;
    }

    @Override
    public boolean updateCourse(Course course) {
        return false;
    }

    @Override
    public boolean deleteCourse(int id) {
        return false;
    }
}
