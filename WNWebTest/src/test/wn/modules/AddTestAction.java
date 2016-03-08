package test.wn.modules;

import com.weinong.base.*;
import test.wn.bean.Test;
import test.wn.core.TestDB;
import test.wn.requestbean.AddTestForm;
import test.wn.service.TestService;
import yao.util.date.DateUtil;
import yao.util.db.TransService;
import yao.util.string.StringUtil;

import java.util.Map;

/**
 * @author jiawenfeng
 * @Date 2016/3/1 19:26
 */
@ApiDefined(label = "添加测试数据",description = "添加test信息")
public class AddTestAction extends BaseApiAction {






    @ParamDefined(label = "请求参数",checkType = CheckType.empty)
    private AddTestForm param;


    // >0 成功 0 系统异常 <0 失败
    @Override
    protected void registResult(Map<Integer, String> map) {
        map.put(1,"添加成功");
        map.put(-1,"添加失败");
        map.put(-2,"名字不能为空");
    }

    @Override
    public ApiResult doApi() throws Exception {
        if (StringUtil.isEmpty(param.getNick_name())){
            return result_fail(-2);
        }
        //int rs = TestService.isnert(buildTest(param));

        ///事务操作
        int rs = (int) getTs(buildTest(param)).doService();

        if (rs > 0){
            return result_success(1);
        }
        return result_fail(-1);
    }
    private TransService getTs(final  Test test){
        TransService ts = new TransService(TestDB.TestDB().getUpdate().getDbPool()) {
            @Override
            protected Object service() throws Exception {
                return TestService.isnert(test);
            }
        };
        return  ts;
    }

    private Test buildTest(AddTestForm param) {

        Test test = new Test();
        test.setBank_id(param.getBank_id());
        test.setBase_number(param.getBase_number());
        test.setNick_name(param.getNick_name());
        test.setCreate_time(DateUtil.now());
        test.setNum(param.getNum());
        test.setPriority(param.getPriority());
        test.setPriority_id(param.getPriority_id());
        test.setSubsection_id(param.getSubsection_id());
        return test;
    }

    public AddTestForm getParam() {
        return param;
    }

    public void setParam(AddTestForm param) {
        this.param = param;
    }
}
