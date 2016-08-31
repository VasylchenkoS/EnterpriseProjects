package com.vasylchenko.jdbc.components;

import com.vasylchenko.jdbc.dao.PositionDAO;
import com.vasylchenko.jdbc.model.Position;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class PositionController {

    PositionDAO positionDAO;

    public void setPositionDAO(PositionDAO positionDAO) {
        this.positionDAO = positionDAO;
    }

    public List<Position> getAllPosition() {
        return positionDAO.getAllPositions();
    }

    public Position getPositionByName(String name) {
        return positionDAO.getPositionByName(name);
    }
}
