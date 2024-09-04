package com.uwdp.jdbcutils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.gientech.lcds.generator.commons.api.exception.BizRuntimeException;
import com.uwdp.module.biz.infrastructure.entity.DBConfigDo;
import com.uwdp.module.biz.infrastructure.repository.DBConfigRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.sql.*;
import java.util.*;

/**
 * CRUD方法
 * 增删改查Util
 */
@Configuration
@Slf4j
public class DatabaseReaderUtil {

    @Autowired
    public DBConfigRepository dbConfigRepository;

    /**
     * 查询数据
     * @param dbName 数据源
     * @return List<Map>
     */
    public List<Map> findData(String dbName,String sql) {
        DBConfigDo dbConfigDo = Optional.ofNullable(
                dbConfigRepository.getOne(new LambdaQueryWrapper<DBConfigDo>()
                        .eq(DBConfigDo::getDbName, dbName)
                        .last(" LIMIT 1"))
        ).orElseThrow(
                () -> new BizRuntimeException(
                        "根据daName:{" + dbName + "}找不到对应的数据源",new Exception())
        );
        String url=dbConfigDo.getConnectUrl();
        String username=dbConfigDo.getUserName();
        String password=dbConfigDo.getPassword();
        String driver =dbConfigDo.getJDBCDriver();
        // 加载数据库驱动程序
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            log.error("找不到数据库驱动程序！");
            e.printStackTrace();
            return null;
        }

        // 连接数据库并执行查询
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            log.info("sql:{}",sql);
            List<Map> rtnList = new ArrayList();
            if (rs != null) {
                ResultSetMetaData metaData = rs.getMetaData();
                // 所有查询列
                Map<String, String> columnMap = new HashMap<String, String>();
                for (int i = 1, length = metaData.getColumnCount(); i <= length; i++) {
                    columnMap.put(metaData.getColumnName(i), metaData.getColumnName(i));
                }
                Map mapSet = null;
                while (rs.next()) {
//						String fdNo = rs.getString("fd_no") + ";";
                    mapSet = new HashMap<String, Object>();
                    for (Iterator<String> ite = columnMap.keySet().iterator(); ite.hasNext();) {
                        String column = ite.next();
                        mapSet.put(column.toLowerCase(), rs.getObject(column));
                    }
                    rtnList.add(mapSet);
                }
                // 关闭结果集、语句和连接
                rs.close();
                stmt.close();
            }
            return rtnList;
        } catch (SQLException e) {
            System.err.println("数据库连接或查询错误！");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 增删改数据
     * @param dbName 数据源
     * @param sql sql
     */
    public int operationData(String dbName,String sql){
        DBConfigDo dbConfigDo = Optional.ofNullable(
                dbConfigRepository.getOne(new LambdaQueryWrapper<DBConfigDo>()
                        .eq(DBConfigDo::getDbName, dbName)
                        .last(" LIMIT 1"))
        ).orElseThrow(
                () -> new BizRuntimeException(
                        "根据daName:{" + dbName + "}找不到对应的数据源",new Exception())
        );
        String url=dbConfigDo.getConnectUrl();
        String username=dbConfigDo.getUserName();
        String password=dbConfigDo.getPassword();
        String driver =dbConfigDo.getJDBCDriver();
        try {
            Class.forName(driver);//注册驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            int rowsAffected = statement.executeUpdate();
            connection.close();
            statement.close();
            return rowsAffected;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
