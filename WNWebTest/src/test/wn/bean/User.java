package test.wn.bean;

import yao.util.db.bean.TableDefined;

import java.util.Date;

/**
 * @author jiawenfeng
 * @Date 2016/3/1 17:38
 */
@TableDefined(table = "user" , primaryKey = "id")
public class User {

    private Integer id; //int(11) NOT NULL AUTO_INCREMENT,
    private String name;
    private Integer age;
    private String gender;
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
