package test.wn.modules;

import com.weinong.base.*;
import test.wn.bean.Test;
import test.wn.requestbean.TestForm;
import test.wn.service.TestService;
import yao.util.collection.MapUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by peiyongkang on 2016/2/16.
 */

@ApiDefined(label = "testAction",description = "test信息")
public class TestAction extends BaseApiAction{
    @ParamDefined(label = "请求参数",checkType = CheckType.empty)
    private TestForm param;

    // >0 成功 0 系统异常 <0 失败
    @Override
    protected void registResult(Map<Integer, String> map) {
        map.put(1,"添加成功");
        map.put(-1,"添加失败");
    }

    @Override
    public ApiResult doApi() throws Exception {
        try {
            List<Test> testList = TestService.getAll();
            Test test = TestService.getById(param.getId());

            Map<String, Object> rs = new HashMap<String, Object>();
            rs.put("allTest", testList);
            rs.put("test", test);

            return result_success(1, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result_fail(-1);
    }

    public TestForm getParam() {
        return param;
    }

    public void setParam(TestForm param) {
        this.param = param;
    }
}
