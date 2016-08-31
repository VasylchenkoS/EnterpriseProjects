package com.vasylchenko.jdbc.dao;

import com.vasylchenko.jdbc.model.Tables;

import java.util.List;

public interface TablesDAO {

    List<Tables> getAllTables();

    Tables getTableByName(String name);
}
