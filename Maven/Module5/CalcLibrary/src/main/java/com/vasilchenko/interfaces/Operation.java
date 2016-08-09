package com.vasilchenko.interfaces;

import java.math.BigDecimal;

public interface Operation {
    BigDecimal count(BigDecimal value1, BigDecimal value2);
    String getOperator();
}
