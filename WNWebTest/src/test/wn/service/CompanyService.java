package test.wn.service;

import test.wn.BaseService;
import test.wn.bean.Company;
import test.wn.core.TestDB;
import yao.util.db.where.Expression;
import yao.util.log.Console;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by YueXiao on 2016/4/8.
 */
public class CompanyService extends BaseService {
    public static List<Company> getAllCompanies() throws SQLException{

        return getSelect().select(Company.class);
    }

    public static Company getCompanyById(int id) throws SQLException{

        Expression where = new Expression("id").eq(id);
        return getSelect().selectSingle(Company.class,where);

    }

    public static int insert(Company company) throws SQLException {
        return TestDB.TestDB().getUpdate().insert(company);
    }

    public static int delete(Company company) throws SQLException {

        Console.info("company.id",company.getId()+"");

        return TestDB.TestDB().getUpdate().deleteSingle(company);
    }

    public static int update(Company company) throws SQLException {
        return TestDB.TestDB().getUpdate().updateSingle(company);
    }

}
