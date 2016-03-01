package test.wn.requestbean;

import com.weinong.base.CheckType;
import com.weinong.base.JsonParam;
import com.weinong.base.ParamDefined;

import java.util.Date;

/**
 * @author jiawenfeng
 * @Date 2016/3/1 19:26
 */
public class AddTestForm implements JsonParam {

    @ParamDefined(label = "test", checkType = CheckType.empty)
    private Double base_number; //double(13,2) DEFAULT NULL COMMENT '用来换算积分的基数',
    @ParamDefined(label = "test", checkType = CheckType.empty)
    private Integer bank_id; //int(11) NOT NULL COMMENT '银行',
    @ParamDefined(label = "test", checkType = CheckType.empty)
    private String nick_name; //varchar(32) CHARACTER SET latin1 DEFAULT NULL COMMENT '昵称',
    @ParamDefined(label = "test", checkType = CheckType.empty)
    private Integer subsection_id; //int(11) DEFAULT NULL COMMENT '地区',
    @ParamDefined(label = "test", checkType = CheckType.empty)
    private Integer num; //tinyint(1) DEFAULT NULL,
    @ParamDefined(label = "test", checkType = CheckType.empty)
    private Integer priority; //tinyint(3) NOT NULL DEFAULT '100' COMMENT '排序优先级 1-100  越小越靠前',
    @ParamDefined(label = "test", checkType = CheckType.empty)
    private Integer priority_id; //int(11) NOT NULL COMMENT '分页用到 取值=priority*100000+id',


    public Double getBase_number() {
        return base_number;
    }

    public void setBase_number(Double base_number) {
        this.base_number = base_number;
    }

    public Integer getBank_id() {
        return bank_id;
    }

    public void setBank_id(Integer bank_id) {
        this.bank_id = bank_id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public Integer getSubsection_id() {
        return subsection_id;
    }

    public void setSubsection_id(Integer subsection_id) {
        this.subsection_id = subsection_id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Integer getPriority_id() {
        return priority_id;
    }

    public void setPriority_id(Integer priority_id) {
        this.priority_id = priority_id;
    }
}
