package test.wn.bean;

import com.weinong.base.CheckType;
import com.weinong.base.JsonParam;
import com.weinong.base.ParamDefined;
import yao.util.db.bean.TableDefined;

/**
 * Created by Administrator on 2016/4/8.
 */

@TableDefined(table = "company" , primaryKey = "id")
public class Company implements JsonParam {

    @ParamDefined(label = "公司ID", checkType = CheckType.empty)
    private int id;
    @ParamDefined(label = "公司名称", checkType = CheckType.empty)
    private String name;
    @ParamDefined(label = "公司编码", checkType = CheckType.empty)
    private String code;
    @ParamDefined(label = "公司地址", checkType = CheckType.empty)
    private String address;

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
