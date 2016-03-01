package test.wn.service;


import test.wn.BaseService;
import test.wn.bean.Test;
import test.wn.core.TestDB;
import yao.util.db.where.Expression;

import java.sql.SQLException;
import java.util.List;

/**
 * @author jiawenfeng
 * @Date 2016/3/1 17:46
 */
public class TestService extends BaseService {
    public static List<Test> getAll() throws SQLException {
        return getSelect().select(Test.class);
    }

    public static Test getById(Integer id) throws SQLException {
        Expression where = new Expression("id").eq(id);
        return getSelect().selectSingle(Test.class, where);
    }

    ////返回影响的行数
    public static int isnert(Test test) throws SQLException {
        return TestDB.TestDB().getUpdate().insert(test);
    }


    ///返回主键并设置
    public static Test save(Test test) throws SQLException {
        test.setId(TestDB.TestDB().getUpdate().insertAndGetGeneratedKeys(test).intValue());
        return test;
    }
}
