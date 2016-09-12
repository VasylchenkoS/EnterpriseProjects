package com.vasylchenko.java.spring.mvc.dao;

import com.vasylchenko.java.spring.mvc.model.Position;

import java.util.List;

public interface PositionDAO {

    List<Position> getAllPositions();

    Position getPositionByName(String name);
}
