package test.wn.service;


import test.wn.BaseService;
import test.wn.bean.Test;
import test.wn.bean.User;
import test.wn.core.TestDB;
import yao.util.db.where.Expression;

import java.sql.SQLException;
import java.util.List;

/**
 *  用户服务
 *  @author zhangxinzheng
 *  @Date 2016/3/9
 */
public class UserService extends BaseService {

    /**
     * 获得所有用户信息
     */
    public static List<User> getAllUsers() throws SQLException{
        return getSelect().select(User.class);
    }

    /**
     * 通过id查找用户信息
     */
    public static User getUserById(Integer id) throws SQLException {
        Expression where = new Expression("id").eq(id);
        return getSelect().selectSingle(User.class, where);
    }

    /**
     * 添加用户
     */
    public static int insert(User user) throws SQLException {
        return TestDB.TestDB().getUpdate().insert(user);
    }

    /**
     * 根据id删除用户信息
     */
    public static int delete(User user) throws SQLException{
        return TestDB.TestDB().getUpdate().deleteSingle(user);
    }

    /**
     * 根据id更新用户信息
     */
    public static int update(User user) throws SQLException{
        return TestDB.TestDB().getUpdate().updateSingle(user);
    }
}
