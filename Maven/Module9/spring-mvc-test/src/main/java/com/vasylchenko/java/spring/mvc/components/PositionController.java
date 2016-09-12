package com.vasylchenko.java.spring.mvc.components;

import com.vasylchenko.java.spring.mvc.dao.PositionDAO;
import com.vasylchenko.java.spring.mvc.model.Position;
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
