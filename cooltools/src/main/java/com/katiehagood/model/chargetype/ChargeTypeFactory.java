package com.katiehagood.model.chargetype;

import java.math.BigDecimal;

public class ChargeTypeFactory {
    
    public ChargeType getChargeType(String toolCode) {
        char toolType = Character.toLowerCase(toolCode.charAt(0));
        switch (toolType) {
            case 'c':
                return new ChargeType("Chainsaw", new BigDecimal("1.49"), true, false, true);
            case 'j':
                return new ChargeType("Jackhammer", new BigDecimal("2.99"), true, false, false);
            case 'l':
                return new ChargeType("Ladder", new BigDecimal("1.99"), true, true, false);
            default:
                return null;
        }
    }
}
