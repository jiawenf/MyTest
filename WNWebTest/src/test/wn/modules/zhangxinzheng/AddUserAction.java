package test.wn.modules.zhangxinzheng;

import com.weinong.base.*;
import test.wn.bean.User;
import test.wn.requestbean.UserForm;
import test.wn.service.UserService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *  用户
 *  @author zhangxinzheng
 *  @Date 2016/3/9
 */
@ApiDefined(label = "addUserAction", description = "添加用户信息")
public class AddUserAction extends BaseApiAction{
    @ParamDefined(label = "请求参数", checkType = CheckType.empty)
    private UserForm param;

    @Override
    protected void registResult(Map<Integer, String> map) {
        map.put(1, "用户添加成功");
        map.put(-1, "用户添加失败");
    }

    @Override
    public ApiResult doApi() throws SQLException{
        User user = new User();
        user.setAge(param.getAge());
        user.setGender(param.getGender());
        user.setName(param.getName());
        user.setRemark(param.getRemark());
        int i = UserService.insert(user);
        if(i > 0){
            return result_success(i);
        }
        return result_fail(-1);
    }

    public UserForm getParam() {
        return param;
    }

    public void setParam(UserForm param) {
        this.param = param;
    }
}
