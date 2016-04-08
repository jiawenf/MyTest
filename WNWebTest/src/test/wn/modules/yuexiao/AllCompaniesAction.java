package test.wn.modules.yuexiao;

import com.weinong.base.*;
import test.wn.bean.Company;
import test.wn.requestbean.CompanyForm;
import test.wn.service.CompanyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/8.
 */

@ApiDefined(label = "AllCompaniesAction",description = "查询全部公司信息")
public class AllCompaniesAction extends BaseApiAction{



    @Override
    protected void registResult(Map<Integer, String> map) {
        map.put(1,"查找到全部公司信息");
        map.put(-1,"查不找到公司信息");
    }

    @Override
    public ApiResult doApi() throws Exception {
        try {
            List<Company> companies = CompanyService.getAllCompanies();

            Map<String, Object> rs = new HashMap<>();
            rs.put("companies", companies);

            return result_success(1, rs);
        } catch (Exception e) {
            e.printStackTrace();
            return result_fail(-1,e.getLocalizedMessage());
        }

    }
}
