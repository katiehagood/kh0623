package com.katiehagood.model.tool;

import com.katiehagood.exceptions.UnknownToolCodeException;

public class ToolFactory {

    /**
     * Given a tool code returns a Tool object
     * @param toolCode Tool code
     * @return Tool object
     * @throws UnknownToolCodeException If no tool with that code exists
     */
    public Tool getTool(String toolCode) throws UnknownToolCodeException{
        switch (toolCode) {
            case "CHNS":
                return new Tool(toolCode, "Stihl");
            case "LADW":
                return new Tool(toolCode, "Werner");
            case "JAKD":
                return new Tool(toolCode, "DeWalt");
            case "JAKR":
                return new Tool(toolCode, "Ridgid");
            default:
                throw new UnknownToolCodeException("%s does not exist in the system".formatted(toolCode));
        }
    }

}
