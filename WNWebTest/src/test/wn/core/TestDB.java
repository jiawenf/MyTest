package test.wn.core;

import com.weinong.util.db.WNDBKit;
import yao.util.db.DBKit;

/**
 * @author jiawenfeng
 * @Date 2016/3/1 17:43
 */
public class TestDB {
    private static WNDBKit test;

    public TestDB() {
    }
    public static DBKit TestDB() {
        if (null == test){
            synchronized (TestDB.class){
                if (null == test){
                    test = new WNDBKit("test", TestConf.test());
                }
            }
        }
        return test;
    }

}
