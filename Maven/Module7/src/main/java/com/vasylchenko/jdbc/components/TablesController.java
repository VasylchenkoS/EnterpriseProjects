package com.vasylchenko.jdbc.components;

import com.vasylchenko.jdbc.dao.TablesDAO;
import com.vasylchenko.jdbc.model.Tables;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class TablesController {

    TablesDAO tablesDAO;

    public void setTablesDAO(TablesDAO tablesDAO) {
        this.tablesDAO = tablesDAO;
    }


    public List<Tables> getAllTables() {
        return tablesDAO.getAllTables();
    }


    public Tables getTableByName(String name) {
        return tablesDAO.getTableByName(name);
    }
}
