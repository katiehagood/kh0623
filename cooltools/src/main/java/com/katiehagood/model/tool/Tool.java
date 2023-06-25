package com.katiehagood.model.tool;

import com.katiehagood.model.chargetype.ChargeType;
import com.katiehagood.model.chargetype.ChargeTypeFactory;

public class Tool {

    private String code;

    private ChargeType type;

    private String brand;

    public Tool(String code, String brand) {
        this.code = code;
        this.brand = brand;

        this.type = new ChargeTypeFactory().getChargeType(this.code);
    }

    public String getCode() {
        return code;
    }

    public ChargeType getType() {
        return type;
    }

    public String getBrand() {
        return brand;
    }

}
