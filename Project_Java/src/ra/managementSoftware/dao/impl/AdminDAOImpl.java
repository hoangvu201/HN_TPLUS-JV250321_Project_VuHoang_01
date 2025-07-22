package ra.managementSoftware.dao.impl;

import ra.managementSoftware.dao.IAdminDAO;
import ra.managementSoftware.utils.DBUtil;

import java.lang.reflect.Type;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;

public class AdminDAOImpl implements IAdminDAO {
    @Override
    public boolean login(String username, String password) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn= DBUtil.openConnection();
            callStmt = conn.prepareCall("{call login_by_admin(?,?,?)}");
            callStmt.setString(1,username);
            callStmt.setString(2,password);
            callStmt.registerOutParameter(3, Types.INTEGER);
            callStmt.execute();
            int count = callStmt.getInt(3);
            return  (count>0);
        }catch (Exception e){
            System.err.println("Lỗi khi đăng nhập Admin: "+e.getMessage());
        }
        finally {
            DBUtil.closeConnection(conn,callStmt);
        }
        return false;
    }
}
