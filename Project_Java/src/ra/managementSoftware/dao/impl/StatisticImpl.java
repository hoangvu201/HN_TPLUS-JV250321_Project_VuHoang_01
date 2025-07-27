package ra.managementSoftware.dao.impl;

import ra.managementSoftware.dao.IStatistic;
import ra.managementSoftware.model.Statistic;
import ra.managementSoftware.utils.DBUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StatisticImpl implements IStatistic {
    @Override
    public Statistic getTotalStatisticCourseAndStudent() {
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        Statistic statistic = null;
        try {
            conn = DBUtil.openConnection();
            cstmt = conn.prepareCall("{call total_course_and_students()}");
            rs = cstmt.executeQuery();
            if(rs.next()) {
                int totalCourse = rs.getInt("total_courses");
                int totalStudents = rs.getInt("total_students");
                statistic = new Statistic(totalCourse, totalStudents);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn,cstmt);
        }
        return statistic;
    }

    @Override
    public List<Statistic> getTotalStatisticStudentInOtherCourse() {
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<Statistic> statisticList = null;
        try {
            conn = DBUtil.openConnection();
            cstmt = conn.prepareCall("{call total_students_in_course()}");
            rs = cstmt.executeQuery();
            statisticList = new ArrayList<>();
            while(rs.next()) {
                String courseName = rs.getString("course_name");
                int studentCount = rs.getInt("student_count");
                Statistic statistic = new Statistic(courseName,studentCount);
                statisticList.add(statistic);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection(conn,cstmt);
        }
        return statisticList;
    }

    @Override
    public List<Statistic> getTop5TotalStatisticStudent() {
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<Statistic> statisticList = null;
        try {
            conn = DBUtil.openConnection();
            cstmt = conn.prepareCall("{call top_5_course()}");
            rs = cstmt.executeQuery();
            statisticList = new ArrayList<>();
            while(rs.next()) {
                String courseName = rs.getString("course_name");
                int studentCount = rs.getInt("total_students");
                statisticList.add(new Statistic(courseName,studentCount));
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn,cstmt);
        }
        return statisticList;
    }

    @Override
    public List<Statistic> getStatisticCourseHavingMore10Student() {
        Connection conn = null;
        CallableStatement cstmt = null;
        ResultSet rs = null;
        List<Statistic> statisticList = null;
        try {
            conn = DBUtil.openConnection();
            cstmt = conn.prepareCall("{call course_with_more_10_students()}");
            rs = cstmt.executeQuery();
            statisticList = new ArrayList<>();
            while(rs.next()) {
                String courseName = rs.getString("course_name");
                int studentCount = rs.getInt("student_count");
                statisticList.add(new Statistic(courseName,studentCount));
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn,cstmt);
        }
        return statisticList;
    }
}
