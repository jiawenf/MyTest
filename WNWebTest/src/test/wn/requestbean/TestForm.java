package test.wn.requestbean;

import com.weinong.base.JsonParam;
import com.weinong.base.ParamDefined;

/**
 * @author jiawenfeng
 * @Date 2016/3/1 17:36
 */
public class TestForm implements JsonParam {
    @ParamDefined(label = "testId")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
