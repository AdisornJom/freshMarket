package com.fresh.market.core.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtil {

    public static double getDouble(Double input) {
        double output = 0.0;
        if (input != null) {
            output = input.doubleValue();
        }
        return output;
    }

    public static float getFloat(Double input) {
        float output = 0;
        if (input != null) {
            output = input.floatValue();
        }
        return output;
    }

    public static int getInteger(Integer input) {
        int output = 0;
        if (input != null) {
            output = input.intValue();
        }
        return output;
    }
    
    public static String numberFormat(BigDecimal input, String format) {
        String output = "0";
        if (input != null) {
            output = (new DecimalFormat(format)).format(input);
        } else {
            output = (new DecimalFormat(format)).format(0.0);
        }
        return output;
    }

    public static String numberFormat(Double input, String format) {
        String output = "0";
        if (input != null) {
            output = (new DecimalFormat(format)).format(input);
        } else {
            output = (new DecimalFormat(format)).format(0.0);
        }
        return output;
    }

    public static String numberFormat(Integer input, String format) {
        String output = "0";
        if (input != null) {
            output = (new DecimalFormat(format)).format(input);
        } else {
            output = (new DecimalFormat(format)).format(0.0);
        }
        return output;
    }
}
