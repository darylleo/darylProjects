package com.daryl.customtemplate;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.MapperException;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.*;

import java.util.Iterator;
import java.util.Set;

/**
 * @author wl
 * @create 2022-01-13
 */
public class CustomProvider extends MapperTemplate {
    public CustomProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String selectList(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        this.setResultType(ms, entityClass);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.selectAllColumns(entityClass));
        sql.append(SqlHelper.fromTable(entityClass, this.tableName(entityClass)));
        sql.append("WHERE DEL_TAG = 0");
        return sql.toString();
    }

    public String selectById(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        this.setResultType(ms, entityClass);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.selectAllColumns(entityClass));
        sql.append(SqlHelper.fromTable(entityClass, this.tableName(entityClass)));
        sql.append("WHERE DEL_TAG = 0 AND ID = #{id}");
        return sql.toString();
    }

    public String queryByPrimeIList(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        this.setResultType(ms, entityClass);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.selectAllColumns(entityClass));
        sql.append(SqlHelper.fromTable(entityClass, this.tableName(entityClass)));
        sql.append("WHERE DEL_TAG = 0");
        sql.append(
                "        <if test=\"ids != null and ids.size() > 0\">\n" +
                "            <foreach open=\"AND ID IN(\" close=\")\" collection=\"ids\" item=\"id\" index=\"i\" separator=\",\">\n" +
                "                #{id}\n" +
                "            </foreach>\n" +
                "        </if>\n" +
                "        <if test=\"ids == null or ids.size == 0\">\n" +
                "            AND 1=0\n" +
                "        </if>");
        return sql.toString();
    }

    public String deleteById(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.updateTable(entityClass, this.tableName(entityClass)));
        sql.append("SET DEL_TAG = 1 ");
        sql.append("WHERE DEL_TAG = 0 AND ID = #{id}");
        return sql.toString();
    }

    public String replace(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        EntityColumn logicDeleteColumn = SqlHelper.getLogicDeleteColumn(entityClass);
        this.processKey(sql, entityClass, ms, columnList);
        sql.append(SqlHelper.insertIntoTable(entityClass, this.tableName(entityClass)).replace("INSERT", "REPLACE"));
        sql.append(SqlHelper.insertColumns(entityClass, false, false, false));
        sql.append("<trim prefix=\"VALUES(\" suffix=\")\" suffixOverrides=\",\">");
        Iterator var6 = columnList.iterator();

        while (true) {
            while (true) {
                EntityColumn column;
                do {
                    if (!var6.hasNext()) {
                        sql.append("</trim>");
                        return sql.toString();
                    }

                    column = (EntityColumn) var6.next();
                } while (!column.isInsertable());

                if (logicDeleteColumn != null && logicDeleteColumn == column) {
                    sql.append(SqlHelper.getLogicDeletedValue(column, false)).append(",");
                } else {
                    if (column.isIdentity()) {
                        sql.append(SqlHelper.getIfCacheNotNull(column, column.getColumnHolder((String) null, "_cache", ",")));
                    } else {
                        sql.append(SqlHelper.getIfNotNull(column, column.getColumnHolder((String) null, (String) null, ","), this.isNotEmpty()));
                    }

                    if (column.isIdentity()) {
                        sql.append(SqlHelper.getIfCacheIsNull(column, column.getColumnHolder() + ","));
                    } else {
                        sql.append(SqlHelper.getIfIsNull(column, column.getColumnHolder((String) null, (String) null, ","), this.isNotEmpty()));
                    }
                }
            }
        }
    }

    public String insertByMap(MappedStatement ms) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ${table}");
        sql.append("<foreach collection=\"params.keys\" item=\"key\" open=\"(\" close=\")\"  \n            separator=\",\">  \n            ${key}  \n   </foreach>");
        sql.append(" values ");
        sql.append("<foreach collection=\"params.values\" item=\"value\" open=\"(\"  \n            close=\")\" separator=\",\">  \n            #{value}  \n   </foreach>");
        return sql.toString();
    }

    public String replaceByMap(MappedStatement ms) {
        StringBuilder sql = new StringBuilder();
        sql.append("REPLACE INTO ${table}");
        sql.append("<foreach collection=\"params.keys\" item=\"key\" open=\"(\" close=\")\"  \n            separator=\",\">  \n            ${key}  \n   </foreach>");
        sql.append(" values ");
        sql.append("<foreach collection=\"params.values\" item=\"value\" open=\"(\"  \n            close=\")\" separator=\",\">  \n            #{value}  \n   </foreach>");
        return sql.toString();
    }

    public String replaceList(MappedStatement ms) {
        StringBuilder sql = new StringBuilder();
        sql.append("REPLACE INTO ${table}");
        sql.append("<foreach collection=\"columns\" item=\"column\" open=\"(\" close=\")\"  \n            separator=\",\">  \n            ${column}  \n   </foreach>");
        sql.append(" values ");
        sql.append("<foreach collection =\"datas\" item=\"data\" separator =\",\">");
        sql.append("<foreach collection=\"data\" item=\"value\" open=\"(\"  \n            close=\")\" separator=\",\">  \n            #{value}  \n   </foreach>");
        sql.append("</foreach >");
        return sql.toString();
    }

    public String updateByMap(MappedStatement ms) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ${table} ");
        sql.append("SET ");
        sql.append("<foreach collection=\"params.keys\" item=\"key\" open=\"\" close=\"\"  \n            separator=\",\">  \n            ${key} = #{params[${key}]}  \n   </foreach>");
        sql.append(" WHERE DEL_TAG = 0 AND ID = #{id}");
        return sql.toString();
    }

    public String deleteByParams(MappedStatement ms) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ${table} ");
        sql.append("SET DEL_TAG = 1, MODIFY_TIME = #{time}");
        sql.append("WHERE DEL_TAG = 0 ");
        sql.append("<foreach collection=\"params\" item=\"param\" open=\"\" close=\"\"  \n            separator=\"\">  \n            AND ${param.name} ${param.op} #{param.value}  \n   </foreach> ");
        return sql.toString();
    }

    public String selectByParams(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        this.setResultType(ms, entityClass);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.selectAllColumns(entityClass));
        sql.append(SqlHelper.fromTable(entityClass, this.tableName(entityClass)));
        sql.append("WHERE DEL_TAG = 0 ");
        sql.append("<foreach collection=\"params\" item=\"param\" open=\"\" close=\"\"  \n            separator=\"\">  \n            AND ${param.name} ${param.op} #{param.value}  \n   </foreach> ");
        return sql.toString();
    }

    public String selectByLeftJoin(MappedStatement ms) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ${columns} FROM ${table} ${name} ");
        sql.append("<foreach collection=\"joins\" item=\"join\" open=\"\" close=\"\"  \n            separator=\"\">  \n            LEFT JOIN  ${join.tName} ${join.name} ON ${join.name}.${join.rColumn} = ${join.lColumn} AND ${join.params} \n   </foreach> ");
        sql.append(" WHERE ${name}.DEL_TAG = 0 AND ${params}");
        return sql.toString();
    }

    public String selectColumns(MappedStatement ms) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ${columns} FROM ${table} ${name}");
        sql.append(" WHERE DEL_TAG = 0 AND ${params}");
        return sql.toString();
    }

    public String getGroupCount(MappedStatement ms) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ${group},COUNT(ID) AS DATA_COUNT FROM ${table} ");
        sql.append(" WHERE DEL_TAG = 0 ");
        sql.append("<foreach collection=\"params\" item=\"param\" open=\"\" close=\"\"  \n            separator=\"\">  \n            AND  ${param.name} ${param.op} #{param.value}\n   </foreach> ");
        sql.append("GROUP BY ${group}");
        return sql.toString();
    }

    public String getTableAllCount(MappedStatement ms) {
        return "SELECT COUNT(ID) FROM ${table}";
    }

    public String getTableDeleteCount(MappedStatement ms) {
        return "SELECT COUNT(ID) FROM ${table} WHERE DEL_TAG = 1";
    }

    public String getLastCreateTime(MappedStatement ms) {
        return "SELECT MAX(CREATE_TIME) FROM ${table}";
    }

    public String getLastModifiedTime(MappedStatement ms) {
        return "SELECT MAX(MODIFY_TIME) FROM ${table}";
    }

    public String selectByMap(MappedStatement ms) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM ${table} ");
        sql.append("WHERE DEL_TAG = 0 ");
        sql.append("<foreach collection=\"params\" item=\"param\" open=\"\" close=\"\"  \n            separator=\"\">  \n            AND ${param.name} ${param.op} #{param.value}  \n   </foreach> ");
        return sql.toString();
    }

    public String createTable(MappedStatement ms) {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE ${table}(");
        sql.append("<foreach collection=\"columns\" item=\"column\" open=\"\" close=\"\"  \n            separator=\",\">  \n            ${column}   </foreach> ");
        sql.append(")");
        return sql.toString();
    }

    public String insertByList(MappedStatement ms) {
        StringBuilder sql = new StringBuilder();
        Class<?> entityClass = this.getEntityClass(ms);
        Set<EntityColumn> columnSet = EntityHelper.getColumns(entityClass);
        sql.append(SqlHelper.insertIntoTable(entityClass, this.tableName(entityClass)));
        sql.append(SqlHelper.insertColumns(entityClass, false, false, false));
        sql.append(" values ");
        sql.append("<foreach collection=\"params\" item=\"value\" separator=\",\">").append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        Iterator var5 = columnSet.iterator();

        while (var5.hasNext()) {
            EntityColumn column = (EntityColumn) var5.next();
            sql.append("#{value.").append(column.getProperty()).append("},");
        }

        sql.append("</trim>").append("</foreach>");
        return sql.toString();
    }

    public String replaceByList(MappedStatement ms) {
        StringBuilder sql = new StringBuilder();
        Class<?> entityClass = this.getEntityClass(ms);
        Set<EntityColumn> columnSet = EntityHelper.getColumns(entityClass);
        sql.append(SqlHelper.insertIntoTable(entityClass, this.tableName(entityClass)).replace("INSERT", "REPLACE"));
        sql.append(SqlHelper.insertColumns(entityClass, false, false, false));
        sql.append(" values ");
        sql.append("<foreach collection=\"params\" item=\"value\" separator=\",\">").append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        Iterator var5 = columnSet.iterator();

        while (var5.hasNext()) {
            EntityColumn column = (EntityColumn) var5.next();
            sql.append("#{value.").append(column.getProperty()).append("},");
        }

        sql.append("</trim>").append("</foreach>");
        return sql.toString();
    }

    public String dropTable(MappedStatement ms) {
        return "DROP TABLE ${table}";
    }

    public String dropTableIfExists(MappedStatement ms) {
        return "DROP TABLE IF EXISTS ${table}";
    }

    private void processKey(StringBuilder sql, Class<?> entityClass, MappedStatement ms, Set<EntityColumn> columnList) {
        Boolean hasIdentityKey = false;
        Iterator var6 = columnList.iterator();

        EntityColumn column;
        label34:
        do {
            while (true) {
                while (var6.hasNext()) {
                    column = (EntityColumn) var6.next();
                    if (column.isIdentity()) {
                        sql.append(SqlHelper.getBindCache(column));
                        if (hasIdentityKey) {
                            continue label34;
                        }

                        SelectKeyHelper.newSelectKeyMappedStatement(ms, column, entityClass, this.isBEFORE(), this.getIDENTITY(column));
                        hasIdentityKey = true;
                    } else if (column.getGenIdClass() != null) {
                        sql.append("<bind name=\"").append(column.getColumn()).append("GenIdBind\" value=\"@tk.mybatis.mapper.genid.GenIdUtil@genId(");
                        sql.append("_parameter").append(", '").append(column.getProperty()).append("'");
                        sql.append(", @").append(column.getGenIdClass().getCanonicalName()).append("@class");
                        sql.append(", '").append(this.tableName(entityClass)).append("'");
                        sql.append(", '").append(column.getColumn()).append("')");
                        sql.append("\"/>");
                    }
                }

                return;
            }
        } while (column.getGenerator() != null && "JDBC".equals(column.getGenerator()));

        throw new MapperException(ms.getId() + "对应的实体类" + entityClass.getCanonicalName() + "中包含多个MySql的自动增长列,最多只能有一个!");
    }
}
