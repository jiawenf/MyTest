package test.wn;

import com.weinong.base.IDPage;
import test.wn.core.TestDB;
import yao.pagespliter.Page;
import yao.util.db.DBKit;
import yao.util.db.ResultList;
import yao.util.db.ResultMap;
import yao.util.db.bean.Limit;
import yao.util.db.bean.OrderBy;
import yao.util.db.bean.OrderItem;
import yao.util.db.bean.OrderItem.OrderType;
import yao.util.db.bean.TableDefined;
import yao.util.db.tool.Select;
import yao.util.db.tool.Select.SqlCtrl;
import yao.util.db.util.SqlNameTranser;
import yao.util.db.util.TableDefinedUtil;
import yao.util.db.where.Expression;
import yao.util.object.FieldUtil;
import yao.util.type.IntegerUtil;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseService {

	private static Map<DBKit, BaseService> services = new HashMap<DBKit, BaseService>();
	private DBKit dbkit;

	/**
	 * 获得一个BaseService对象
	 * 
	 * @param dbkit
	 * @return
	 */
	public static BaseService get(DBKit dbkit) {
		BaseService bs = services.get(dbkit);
		if (null == bs) {
			synchronized (services) {
				bs = services.get(dbkit);
				if (null == bs) {
					bs = new BaseService();
					bs.dbkit = dbkit;
					services.put(dbkit, bs);
				}
			}
		}
		return bs;
	}

	public static BaseService get() {
		return get(TestDB.TestDB());
	}

	public static Select getSelect() {
		return TestDB.TestDB().getSelect();
	}

	/**
	 * 保存一条记录
	 * 
	 * @param bean
	 * @return
	 * @throws SQLException
	 */
	public boolean save(Object bean) throws SQLException {
		TableDefined tableDefined = TableDefinedUtil.ask(bean.getClass());
		// 如果有自增长ID
		if (tableDefined.primaryAutoIncrement()) {
			boolean idNotNull = true;
			for (String key : tableDefined.primaryKey()) {
				idNotNull &= null != FieldUtil.get(bean, key);
			}
			if (idNotNull) {
				return dbkit.getUpdate().updateSingle(bean) > 0;
			} else {
				return dbkit.getUpdate().insert(bean) > 0;
			}
		} else {
			throw new SQLException("Can't choose insert and update from primaryAutoIncrement = true");
		}
	}

	/**
	 * 保存一条记录，返回ID
	 * 
	 * @param bean
	 * @return
	 * @throws SQLException
	 */
	public int saveAndReturnId(Object bean) throws SQLException {
		TableDefined tableDefined = TableDefinedUtil.ask(bean.getClass());
		if (tableDefined.primaryKey().length > 1) { throw new IllegalArgumentException("primary key more than 1, can't call this method."); }
		String idName = tableDefined.primaryKey()[0];
		Object id = FieldUtil.get(bean, idName);
		if (null == id) {
			id = dbkit.getUpdate().insertAndGetGeneratedKeys(bean);
		} else {
			dbkit.getUpdate().updateSingle(bean);
		}
		return IntegerUtil.parseInt(id);
	}

	/**
	 * 保存一条记录，填充对象的ID
	 * 
	 * @param bean
	 * @return
	 * @throws SQLException
	 */
	public <T> T saveAndFillId(T bean) throws SQLException {
		TableDefined tableDefined = TableDefinedUtil.ask(bean.getClass());
		if (tableDefined.primaryKey().length > 1) { throw new IllegalArgumentException("primary key more than 1, can't call this method."); }
		String idName = tableDefined.primaryKey()[0];
		Object id = FieldUtil.get(bean, idName);
		if (null == id) {
			id = dbkit.getUpdate().insertAndGetGeneratedKeys(bean);
			FieldUtil.set(bean, idName, IntegerUtil.parseInt(id));
		} else {
			dbkit.getUpdate().updateSingle(bean);
		}
		return bean;
	}

	/**
	 * 删除一条记录
	 * 
	 * @param bean
	 * @return
	 * @throws SQLException
	 */
	public boolean del(Object bean) throws SQLException {
		return dbkit.getUpdate().deleteSingle(bean) > 0;
	}

	/**
	 * 根据ID得到一个记录对象
	 * 
	 * @param beanClass
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public <T> T getById(Class<T> beanClass, Object id) throws SQLException {
		return dbkit.getSelect().selectSingle(beanClass, id);
	}

	/**
	 * 根据唯一键得到一个记录对象
	 * 
	 * @param beanClass
	 *            对象类
	 * @param field
	 *            唯一键字段名
	 * @param value
	 *            唯一键值
	 * @return
	 * @throws SQLException
	 */
	public <T> T getByKey(Class<T> beanClass, String field, Object value) throws SQLException {
		Expression where = new Expression(field).eq(value);
		return getByWhere(beanClass, where);
	}

	/**
	 * 根据条件得到一个对象
	 * 
	 * @param beanClass
	 * @param where
	 * @return
	 * @throws SQLException
	 */
	public <T> T getByWhere(Class<T> beanClass, Expression where) throws SQLException {
		return dbkit.getSelect().selectSingle(beanClass, where);
	}
	
	
	public <T> T getByWhere(Class<T> beanClass, Expression where, OrderBy orderBy) throws SQLException {
		return dbkit.getSelect().selectSingle(beanClass, where, orderBy);
	}

	/**
	 * 根据ID列表得到一个列表
	 * 
	 * @param beanClass
	 * @param ids
	 * @return
	 * @throws SQLException
	 */
	public <T> List<T> getListByIds(Class<T> beanClass, Object[] ids) throws SQLException {
		TableDefined tableDefined = TableDefinedUtil.ask(beanClass);
		if (tableDefined.primaryKey().length > 1) { throw new IllegalArgumentException("primary key more than 1, can't call this method."); }
		Expression where = new Expression(tableDefined.primaryKey()[0]).in(ids);
		return getListByWhere(beanClass, where);
	}

	/**
	 * 根据键对应的值得到数据
	 * 
	 * @param beanClass
	 * @param field
	 * @param value
	 * @return
	 * @throws SQLException
	 */
	public <T> List<T> getListByKey(Class<T> beanClass, String field, Object value) throws SQLException {
		Expression where = new Expression(field).eq(value);
		return dbkit.getSelect().select(beanClass, where);
	}

	/**
	 * 根据条件、分页、和控制得到一个页的数据
	 * 
	 * @param beanClass
	 * @param page
	 * @param where
	 * @param orderBy
	 * @return
	 * @throws SQLException
	 */
	public <T> List<T> getListByWherePage(Class<T> beanClass, Page page, Expression where, OrderBy orderBy) throws SQLException {
		String table = TableDefinedUtil.ask(beanClass).table();
		table = SqlNameTranser.IT.toString(table);
		if (null == where) {
			where = Expression.TRUE;
		}
		ResultMap rm = dbkit.getSelect().selectSingle("SELECT COUNT(*) AS `count` FROM " + table + " " + where.toWhereString(), where.getParams());
		page.setMaxRecord(((Number) rm.get("count")).intValue());
		SqlCtrl ctrl;
		if (null == orderBy) {
			ctrl = new SqlCtrl(new Limit(page.getOffest(), page.getPageSize()));
		} else {
			ctrl = new SqlCtrl(orderBy, new Limit(page.getOffest(), page.getPageSize()));
		}
		return dbkit.getSelect().select(beanClass, where, ctrl);
	}

	public <T> List<T> getListByWherePage(Class<T> beanClass, Page page, Expression where) throws SQLException {
		return getListByWherePage(beanClass, page, where, null);
	}

	public <T> List<T> getListByWhereIDPage(Class<T> beanClass, IDPage page, BaseQuery query) throws SQLException {
		String table = TableDefinedUtil.ask(beanClass).table();
		table = SqlNameTranser.IT.toString(table);
		Expression where = query.toWhere();
		if (null == where) {
			where = Expression.TRUE;
		}
		TableDefined tableDefined = TableDefinedUtil.ask(beanClass);
		if (tableDefined.primaryKey().length > 1) { throw new IllegalArgumentException("primary key more than 1, can't call this method."); }
		String idFieldname = tableDefined.primaryKey()[0];
		if (null != page) {
			// 升序
			if ("asc".equalsIgnoreCase(page.getSortType())) {
				where = Expression.and(where, new Expression(idFieldname).gt(page.getLastId()));

				// where = Expression.and(where, new
				// Expression(idFieldname).lt(page.getLastId()+page.getPageSize()));
			}
			// 降序
			else {
				where = Expression.and(where, new Expression(idFieldname).lt(page.getLastId()));

				// where = Expression.and(where, new
				// Expression(idFieldname).gteq(page.getLastId()-page.getPageSize()));
			}
			return dbkit.getSelect().select(beanClass, where, new SqlCtrl(new OrderBy(new OrderItem(idFieldname, OrderType.valueOf(page.getSortType().toUpperCase()))), new Limit(page.getPageSize())));
		}

		return dbkit.getSelect().select(beanClass, where, new SqlCtrl(new OrderBy(new OrderItem(idFieldname, OrderType.valueOf("ASC"))))); // 不传page默认
	}

	// public <T> List<T> getListByWhereIDPage(Class<T> beanClass, IDPage page,
	// BaseQuery query) throws SQLException {
	// if (null == query) { return getListByWhereIDPage(beanClass, page, null);
	// }
	// return getListByWhereIDPage(beanClass, page, query);
	// }

	/**
	 * 根据查询对象得到一个页的数据
	 * 
	 * @param beanClass
	 * @param page
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public <T> List<T> getListByWherePage(Class<T> beanClass, Page page, BaseQuery query) throws SQLException {
		if (null == query) { return getListByWherePage(beanClass, page, null, null); }
		return getListByWherePage(beanClass, page, query.toWhere(), query.toOrderBy());
	}

	/**
	 * 得到一个表的所有数据
	 * 
	 * @param beanClass
	 * @return
	 * @throws SQLException
	 */
	public <T> List<T> getAllList(Class<T> beanClass) throws SQLException {
		return dbkit.getSelect().select(beanClass);
	}

	/**
	 * 根据条件得到一个表的数据
	 * 
	 * @param beanClass
	 * @param where
	 * @return
	 * @throws SQLException
	 */
	public <T> List<T> getListByWhere(Class<T> beanClass, Expression where) throws SQLException {
		return dbkit.getSelect().select(beanClass, where);
	}

	/**
	 * 取上次插入的id
	 * 
	 * @return
	 * @throws SQLException
	 */

	public BigInteger getLastInsertId() throws SQLException {
		ResultMap resultMap = dbkit.getSelect().selectSingle("SELECT LAST_INSERT_ID() id");
		return (BigInteger) resultMap.get("id");
	}

	public ResultList getListBySqlPage(String sql, Page page) throws SQLException {
		ResultMap rm = dbkit.getSelect().selectSingle("SELECT COUNT(*) AS `count` FROM (" + sql + ") date");
		page.setMaxRecord(((Number) rm.get("count")).intValue());

		sql += " limit " + page.getOffest() + "," + page.getPageSize();
		return dbkit.getSelect().select(sql);
	}

	public <T> List<T> getListBySqlIDPage(Class<T> beanClass,String sql,Object[] params,String pk, IDPage page) throws SQLException {
		String pageWhere = "";
		if (page != null) {
			if ("asc".equalsIgnoreCase(page.getSortType()))
				pageWhere = " and " + pk + " > " + page.getLastId() + " order by " + pk + " asc limit 0," + page.getPageSize();
			else
				pageWhere = " and " + pk + " < " + page.getLastId() + " order by " + pk + " desc limit 0," + page.getPageSize();
		} else {
			pageWhere = " order by " + pk + " desc limit 0,20";
		}
		return dbkit.getSelect().select(beanClass, sql + pageWhere, params);
	}
}
