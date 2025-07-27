package ra.managementSoftware.model;

public class Statistic {
    private String courseName;
    private int studentCount;
    private int totalCourse;
    private int totalStudents;
    private int statisticCourse;
    private int statisticStudent;
    private int statisticTop5Course;
    private int statisticCourseHavingMore10Student;

    public Statistic() {
    }

    public Statistic(String courseName, int studentCount, int totalCourse, int totalStudents, int statisticCourse, int statisticStudent, int statisticTop5Course, int statisticCourseHavingMore10Student) {
        this.courseName = courseName;
        this.studentCount = studentCount;
        this.totalCourse = totalCourse;
        this.totalStudents = totalStudents;
        this.statisticCourse = statisticCourse;
        this.statisticStudent = statisticStudent;
        this.statisticTop5Course = statisticTop5Course;
        this.statisticCourseHavingMore10Student = statisticCourseHavingMore10Student;
    }

    public Statistic(int totalCourse, int totalStudents) {
        this.totalCourse = totalCourse;
        this.totalStudents = totalStudents;
    }

    public Statistic(String courseName, int studentCount) {
        this.courseName = courseName;
        this.studentCount = studentCount;
    }

    public int getStatisticCourse() {
        return statisticCourse;
    }

    public void setStatisticCourse(int statisticCourse) {
        this.statisticCourse = statisticCourse;
    }

    public int getStatisticStudent() {
        return statisticStudent;
    }

    public void setStatisticStudent(int statisticStudent) {
        this.statisticStudent = statisticStudent;
    }

    public int getStatisticTop5Course() {
        return statisticTop5Course;
    }

    public void setStatisticTop5Course(int statisticTop5Course) {
        this.statisticTop5Course = statisticTop5Course;
    }

    public int getTotalStudents() {
        return totalStudents;
    }

    public void setTotalStudents(int totalStudents) {
        this.totalStudents = totalStudents;
    }

    public int getStatisticCourseHavingMore10Student() {
        return statisticCourseHavingMore10Student;
    }

    public void setStatisticCourseHavingMore10Student(int statisticCourseHavingMore10Student) {
        this.statisticCourseHavingMore10Student = statisticCourseHavingMore10Student;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public int getTotalCourse() {
        return totalCourse;
    }

    public void setTotalCourse(int totalCourse) {
        this.totalCourse = totalCourse;
    }
}

