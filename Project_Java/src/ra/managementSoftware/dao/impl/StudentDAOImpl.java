package ra.managementSoftware.dao.impl;

import ra.managementSoftware.dao.IStudentDAO;
import ra.managementSoftware.model.Student;
import ra.managementSoftware.utils.DBUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements IStudentDAO {
    @Override
    public List<Student> getAll() {
        Connection conn = null;
        CallableStatement stmt = null;
        try {
            conn = DBUtil.openConnection();
            stmt = conn.prepareCall("{call get_all_student()}");
            ResultSet rs = stmt.executeQuery();
            List<Student> list = new ArrayList<>();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setDateOfBirth(LocalDate.parse(rs.getString("dob"), dtf));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setSex((rs.getInt("sex") == 1));
                student.setPassword(rs.getString("password"));
                student.setCreateAt(rs.getDate("create_at").toLocalDate());
                list.add(student);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            DBUtil.closeConnection(conn,stmt);
        }
        return List.of();
    }

    @Override
    public Student getById(int id) {
        return null;
    }

    @Override
    public boolean addStudent(Student student) {
        return false;
    }

    @Override
    public boolean updateStudent(Student student) {
        return false;
    }

    @Override
    public boolean deleteStudent(int id) {
        return false;
    }
}
