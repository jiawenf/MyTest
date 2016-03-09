package test.wn.requestbean;

import com.weinong.base.CheckType;
import com.weinong.base.JsonParam;
import com.weinong.base.ParamDefined;

/**
 * 用户表单
 *  @author zhangxinzheng
 *  @Date 2016/3/9
 */
public class UserForm implements JsonParam{
    @ParamDefined(label = "user", checkType = CheckType.empty)
    private Integer id;
    @ParamDefined(label = "user", checkType = CheckType.empty)
    private String name;
    @ParamDefined(label = "user", checkType = CheckType.empty)
    private Integer age;
    @ParamDefined(label = "user", checkType = CheckType.empty)
    private String gender;
    @ParamDefined(label = "user", checkType = CheckType.empty)
    private String remark;

    public Integer getId() {
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
