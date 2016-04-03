package org.pw.rafalj.crm.repository.test.impl.contract;

import org.pw.rafalj.crm.repository.test.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Rav on 2016-04-03.
 */
@Repository
public class ContractTestRepositoryPureSQLImpl implements TestRepository {
    @Autowired
    DataSource dataSource;

    @Override
    public void testSelect(int index) {
        Connection conn = DataSourceUtils.getConnection(dataSource);
        PreparedStatement stmt = null;
        try{
            conn.setAutoCommit(false);
            stmt = conn.prepareStatement("select * from CONTRACTS WHERE id = ?");
            stmt.setInt(1, index);
            stmt.executeQuery();

            conn.rollback();
        } catch (Exception e) {
        } finally{
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
            }
        }
    }

    @Override
    public void testInsert(int index) {

    }

    @Override
    public void testUpdate(int index) {

    }

    @Override
    public void testDelete(int index) {

    }
}
