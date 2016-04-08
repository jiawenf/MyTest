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
@ApiDefined(label = "allUserAction", description = "所有用户信息")
public class AllUserAction extends BaseApiAction{
    @Override
    protected void registResult(Map<Integer, String> map) {
        map.put(1, "查找成功");
        map.put(-1, "查找失败");
    }

    @Override
    public ApiResult doApi(){
        try{
            List<User> users = UserService.getAllUsers();
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("users",users);
            return result_success(1,map);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result_fail(-1);
    }

}
