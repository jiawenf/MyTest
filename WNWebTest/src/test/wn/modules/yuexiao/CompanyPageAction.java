package test.wn.modules.yuexiao;

import com.weinong.base.ApiDefined;
import com.weinong.base.ApiResult;
import com.weinong.base.BaseApiPageAction;
import test.wn.bean.Company;
import test.wn.service.CompanyService;
import yao.pagespliter.Page;
import yao.util.log.Console;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/8.
 */


@ApiDefined(label = "CompanyAction",description = "分页查询公司信息")
public class CompanyPageAction extends BaseApiPageAction {
    @Override
    public ApiResult doPageApi() throws Exception {

        try {
            List<Company> companies = CompanyService.findPage(page);

            return  result_success(1, companies);
        }
        catch (SQLException e){

            Console.error(this,"分页查询公司失败",e);
            result_error("分页查询公司失败",e);
        }
        return  result_fail(-1);

    }

    @Override
    protected void registResult(Map<Integer, String> map) {
        map.put(1,"分页查询公司信息成功");
        map.put(-1,"分页查询公司信息失败");

    }

    @Override
    public String getStaticDescription() {
        return "调用示例:<a target='_blank' href='company_page.wn?page={\"lastId\":5,\"pageSize\":2,\"sortType\":\"DESC\"}'>company_page.wn?page={\"lastId\":5,\"pageSize\":2,\"sortType\":\"DESC\"}</a>";
    }
}
