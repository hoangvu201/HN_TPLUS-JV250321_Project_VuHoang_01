package ra.managementSoftware.business.impl;

import ra.managementSoftware.business.IAdminService;
import ra.managementSoftware.dao.IAdminDAO;
import ra.managementSoftware.dao.impl.AdminDAOImpl;

public class AdminServiceImpl implements IAdminService {
    private IAdminDAO adminDAO = new AdminDAOImpl();
    @Override
    public boolean checkLoginAdmin(String username, String password) {
        return adminDAO.login(username, password);
    }
}
