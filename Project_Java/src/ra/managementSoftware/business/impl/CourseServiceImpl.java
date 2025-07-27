package ra.managementSoftware.business.impl;

import ra.managementSoftware.business.ICourseService;
import ra.managementSoftware.dao.ICourseDAO;
import ra.managementSoftware.dao.impl.CourseDAOImpl;
import ra.managementSoftware.model.Course;

import java.util.List;

public class CourseServiceImpl implements ICourseService {
    private final ICourseDAO courseDAO;
    public CourseServiceImpl() {
        courseDAO = new CourseDAOImpl();
    }

    @Override
    public List<Course> getAll() {
        return courseDAO.getAll();
    }

    @Override
    public boolean checkCourseIdExists(int id) {
        return courseDAO.checkCourseIdExists(id);
    }


    @Override
    public boolean addCourse(Course course) {
        return courseDAO.addCourse(course);
    }

    @Override
    public boolean updateCourse(Course course) {
        return courseDAO.updateCourse(course);
    }

    @Override
    public boolean deleteCourse(int id) {
        return courseDAO.deleteCourse(id);
    }

    @Override
    public Course getCourseById(int id) {
        return courseDAO.getCourseById(id);
    }

    @Override
    public List<Course> getCourseByName(String name) {
        return courseDAO.getCourseByName(name);
    }

    @Override
    public List<Course> sortById(int number) {
        return courseDAO.sortById(number);
    }

    @Override
    public List<Course> sortByName(int number) {
        return courseDAO.sortByName(number);
    }
}
