package com.katiehagood.model.tool;

import com.katiehagood.model.chargetype.ChargeType;
import com.katiehagood.model.chargetype.ChargeTypeFactory;

public class Tool {

    private String code;

    private ChargeType type;

    private String brand;

    /**
     * Constructs a tool object
     * @param code Tool code
     * @param brand Brand of tool
     */
    public Tool(String code, String brand) {
        this.code = code;
        this.brand = brand;

        this.type = new ChargeTypeFactory().getChargeType(this.code);
    }

    /**
     * Gets tool code
     * @return Tool code
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets tool type
     * @return Tool type
     */
    public ChargeType getType() {
        return type;
    }

    /**
     * Gets tool brand
     * @return Tool brand
     */
    public String getBrand() {
        return brand;
    }

}
