package test.wn.modules.yuexiao;

import com.weinong.base.*;
import test.wn.bean.Company;
import test.wn.requestbean.CompanyForm;
import test.wn.service.CompanyService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/8.
 */

@ApiDefined(label = "CompanyAction",description = "公司信息")
public class CompanyAction extends BaseApiAction{

    @ParamDefined(label = "查询参数",checkType = CheckType.empty)
    private CompanyForm param;

    public CompanyForm getParam() {
        return param;
    }

    public void setParam(CompanyForm param) {
        this.param = param;
    }

    @Override
    protected void registResult(Map<Integer, String> map) {
        map.put(1,"查找到公司信息");
        map.put(-1,"查不找到公司信息");
    }

    @Override
    public ApiResult doApi() throws Exception {
        try {
            Company company = CompanyService.getCompanyById(param.getId());

            Map<String, Object> rs = new HashMap<>();
            rs.put("company", company);

            return company==null? result_fail(-1,"找不到数据") : result_success(1, rs);
        } catch (Exception e) {
            e.printStackTrace();
            return result_fail(-1,e.getLocalizedMessage());
        }

    }
}
