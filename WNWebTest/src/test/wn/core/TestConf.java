package test.wn.core;

import com.weinong.util.config.WNConfig;
import yao.config.exception.ConfigException;

import java.io.IOException;

/**
 * @author jiawenfeng
 * @Date 2016/3/1 17:39
 */
public class TestConf {
    private static WNConfig test;

    public TestConf() {
    }

    public static void init() throws ConfigException, IOException {
        test = new WNConfig("test", "test.ini");
    }

    public static WNConfig test() {
        return test;
    }
}
