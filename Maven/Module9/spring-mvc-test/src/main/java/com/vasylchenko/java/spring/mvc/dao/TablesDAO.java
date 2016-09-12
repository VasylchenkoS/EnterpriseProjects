package com.vasylchenko.java.spring.mvc.dao;

import com.vasylchenko.java.spring.mvc.model.Tables;

import java.util.List;

public interface TablesDAO {

    List<Tables> getAllTables();

    Tables getTableByName(String name);
}
