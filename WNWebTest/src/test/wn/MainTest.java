package test.wn;

import test.wn.bean.Test;
import yao.util.array.ArrayUtil;
import yao.util.date.DateUtil;
import yao.util.db.bean.TableDefined;
import yao.util.db.util.SqlNameTranser;
import yao.util.db.util.TableDefinedUtil;
import yao.util.object.FieldUtil;
import yao.util.pool.NewObjectException;
import yao.util.string.StringUtil;

import java.sql.SQLException;

/**
 * @author jiawenfeng
 * @Date 2016/3/3 12:00
 */
public class MainTest {
    public static void main(String[] args) {
        Test test = new Test();
        test.setBank_id(1);
        test.setBase_number(1.0);
        test.setNick_name("4343");
        test.setCreate_time(DateUtil.now());
        test.setNum(1);
        test.setPriority(1);
        test.setPriority_id(1);
        test.setSubsection_id(1);

        TableDefined tableDefined = TableDefinedUtil.ask(test.getClass());
        System.out.println(tableDefined.db()+","+tableDefined.table()+","+tableDefined.primaryKey()[0]);
        /////////////////
        String[] uniqueFieldNames = tableDefined.primaryKey();
        String[] notUpdateFields  = null;
        try {
            String[] e = FieldUtil.propertys(test.getClass());
            e =  ArrayUtil.removeItem(e, uniqueFieldNames);
            e =  ArrayUtil.removeItem(e, notUpdateFields);
            String[] setList = StringUtil.convert2StringArray(e, new StringUtil.ArrayConverter() {
                public String convert(Object source) {
                    return SqlNameTranser.IT.toString(source) + "=?";
                }
            });
            Object[] values = new Object[e.length + uniqueFieldNames.length];

            int sqlSb;
            for(sqlSb = 0; sqlSb < e.length; ++sqlSb) {
                values[sqlSb] = FieldUtil.get(test, e[sqlSb]);
            }

            for(sqlSb = 0; sqlSb < uniqueFieldNames.length; ++sqlSb) {
                values[values.length - uniqueFieldNames.length + sqlSb] = FieldUtil.get(test, uniqueFieldNames[sqlSb]);
            }

            StringBuffer var16 = new StringBuffer();
            var16.append("UPDATE ");
            var16.append(SqlNameTranser.IT.toString(tableDefined.table())).append(" SET ").append(StringUtil.linkString(setList, ", "));
            var16.append(" WHERE 1=1");

            int i;
            for(i = 0; i < uniqueFieldNames.length; ++i) {
                var16.append(" AND ").append(SqlNameTranser.IT.toString(uniqueFieldNames[i])).append("=?");
            }

            var16.append(" LIMIT 1");

        } catch (Exception var14) {
        }
    }
}
