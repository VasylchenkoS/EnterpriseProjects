package com.vasilchenko.java.dao;

import com.vasilchenko.java.model.Tables;

import java.util.List;

public interface TablesDAO {

    List<Tables> getAllTables();

    Tables getTableByName(String name);
}
