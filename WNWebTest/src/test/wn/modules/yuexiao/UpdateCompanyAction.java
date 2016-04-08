package test.wn.modules.yuexiao;

import com.weinong.base.*;
import org.springframework.beans.BeanUtils;
import test.wn.bean.Company;
import test.wn.requestbean.CompanyForm;
import test.wn.service.CompanyService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/8.
 */

@ApiDefined(label = "CompanyAction",description = "更新公司")
public class UpdateCompanyAction extends BaseApiAction{

    @ParamDefined(label = "需要更新的公司数据",checkType = CheckType.empty)
    private CompanyForm param;

    public CompanyForm getParam() {
        return param;
    }

    public void setParam(CompanyForm param) {
        this.param = param;
    }

    @Override
    protected void registResult(Map<Integer, String> map) {
        map.put(1,"更新公司成功");
        map.put(-1,"更新公司失败");
    }

    @Override
    public ApiResult doApi() throws Exception {
        try {
            Company company = new Company();
            BeanUtils.copyProperties(param,company);
            company.setId(param.getId());
            int result = CompanyService.update(company);
            Map<String, Object> rs = new HashMap<>();
            rs.put("deleteResult", result);
            return result > 0 ? result_success(1, rs):result_fail(-1,"updateResult="+result);
        } catch (Exception e) {
            e.printStackTrace();
            return result_fail(-1,e.getLocalizedMessage());
        }

    }
}
