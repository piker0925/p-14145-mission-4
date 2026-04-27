package com.back;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private final String commandName;
    private final Map<String, String> params;

    public Rq(String command) {
        params = new HashMap<>();
        String[] commandBits = command.split("\\?", 2);
        this.commandName = commandBits[0];

        if (commandBits.length == 2) {
            String queryStr = commandBits[1];
            String[] paramBits = queryStr.split("&");
            for (String paramBit : paramBits) {
                String[] bits = paramBit.split("=", 2);
                if (bits.length == 2) {
                    params.put(bits[0], bits[1]);
                }
            }
        }
    }

    public String getCommandName() {
        return commandName;
    }

    public String getParam(String name, String defaultValue) {
        return params.getOrDefault(name, defaultValue);
    }

    public int getParamAsInt(String name, int defaultValue) {
        String value = getParam(name, "");
        if (value.isEmpty()) return defaultValue;
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException _) {
            return defaultValue;
        }
    }
}
