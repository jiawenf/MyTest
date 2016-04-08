package test.wn.modules.yuexiao;

import com.weinong.base.*;
import org.springframework.beans.BeanUtils;
import test.wn.bean.Company;
import test.wn.requestbean.CompanyForm;
import test.wn.service.CompanyService;

import java.util.Map;

/**
 * Created by Administrator on 2016/4/8.
 */

@ApiDefined(label = "CompanyAction",description = "新增公司")
public class AddCompanyAction extends BaseApiAction{

    @ParamDefined(label = "需要新增的公司数据",checkType = CheckType.empty)
    private CompanyForm param;

    public CompanyForm getParam() {
        return param;
    }

    public void setParam(CompanyForm companyForm) {
        this.param = companyForm;
    }

    @Override
    protected void registResult(Map<Integer, String> map) {
        map.put(1,"添加公司成功");
        map.put(-1,"添加公司失败");
    }

    @Override
    public ApiResult doApi() throws Exception {
        try {
            Company company = new Company();
            BeanUtils.copyProperties(param,company);

            int result = CompanyService.insert(company);

            return result_success(1, result>0?"新增成功":"新增失败!");
        } catch (Exception e) {
            e.printStackTrace();
            return result_fail(-1,e.getLocalizedMessage());
        }

    }
}
