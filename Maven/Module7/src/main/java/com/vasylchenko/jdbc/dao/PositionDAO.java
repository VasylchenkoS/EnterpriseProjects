package com.vasylchenko.jdbc.dao;

import com.vasylchenko.jdbc.model.Position;

import java.util.List;

public interface PositionDAO {

    List<Position> getAllPositions();

    Position getPositionByName(String name);
}
