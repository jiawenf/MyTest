package test.wn.modules.zhangxinzheng;

import com.weinong.base.*;
import test.wn.bean.User;
import test.wn.requestbean.UserForm;
import test.wn.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  用户
 *  @author zhangxinzheng
 *  @Date 2016/3/9
 */
@ApiDefined(label = "userAction", description = "用户信息")
public class UserAction extends BaseApiAction{
    @ParamDefined(label = "请求参数", checkType = CheckType.empty)
    private UserForm param;

    @Override
    protected void registResult(Map<Integer, String> map) {
        map.put(1, "查找成功");
        map.put(-1, "查找失败");
    }

    @Override
    public ApiResult doApi(){
        try{
            User user = UserService.getUserById(param.getId());
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("user",user);
            return result_success(1,map);
        }catch (Exception e){
            e.printStackTrace();
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
