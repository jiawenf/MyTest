package test.wn.service;

import com.weinong.base.IDPage;
import test.wn.BaseService;
import test.wn.bean.Company;
import test.wn.core.TestDB;
import yao.pagespliter.Page;
import yao.util.db.bean.Limit;
import yao.util.db.bean.OrderBy;
import yao.util.db.bean.OrderItem;
import yao.util.db.tool.Select;
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

    public static List<Company> findPage(IDPage page) throws SQLException {
        boolean isAsc = "ASC".equals(page.getSortType());
        Expression where = isAsc? new Expression("id").gt(page.getLastId()) : new Expression("id").lt(page.getLastId());
        OrderBy orderBy = new OrderBy(new OrderItem("id",isAsc ? OrderItem.OrderType.ASC : OrderItem.OrderType.DESC));
        Limit limit = new Limit(page.getPageSize());
        Select.SqlCtrl sqlCtrl = new Select.SqlCtrl(orderBy,limit);
        return getSelect().select(Company.class,where,sqlCtrl);
    }

}
