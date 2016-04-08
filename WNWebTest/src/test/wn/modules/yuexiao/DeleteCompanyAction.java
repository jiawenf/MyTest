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

@ApiDefined(label = "CompanyAction",description = "删除公司信息")
public class DeleteCompanyAction extends BaseApiAction{

    @ParamDefined(label = "需要删除的公司数据",checkType = CheckType.empty)
    private CompanyForm param;

    public CompanyForm getParam() {
        return param;
    }

    public void setParam(CompanyForm param) {
        this.param = param;
    }

    @Override
    protected void registResult(Map<Integer, String> map) {
        map.put(1,"删除公司成功");
        map.put(-1,"删除公司失败");
    }

    @Override
    public ApiResult doApi() throws Exception {
        try {
            Company company = new Company();
            company.setId(param.getId());

            int result = CompanyService.delete(company);
            Map<String, Object> rs = new HashMap<>();
            rs.put("deleteResult", result);
            return result > 0 ? result_success(1, rs):result_fail(-1,"deleteResult="+result);
        } catch (Exception e) {
            e.printStackTrace();
            return result_fail(-1,e.getLocalizedMessage());
        }

    }
}
