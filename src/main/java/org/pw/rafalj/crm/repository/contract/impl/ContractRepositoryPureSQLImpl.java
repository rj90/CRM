package org.pw.rafalj.crm.repository.contract.impl;

import org.pw.rafalj.crm.filter.ContractFilter;
import org.pw.rafalj.crm.repository.contract.ContractRepository;
import org.pw.rafalj.crm.vo.contract.ContractStatusVO;
import org.pw.rafalj.crm.vo.pageContainer.PageContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by rjozwiak on 2016-04-26.
 */
public class ContractRepositoryPureSQLImpl implements ContractRepository {
    @Autowired
    DataSource dataSource;

    @Override
    public PageContainer findByFilter(ContractFilter filter, String... columns) {
        return null;
    }

    @Override
    public List<ContractStatusVO> getStatuses() {
        return null;
    }

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
}
