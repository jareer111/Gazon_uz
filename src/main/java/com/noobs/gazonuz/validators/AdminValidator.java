package com.noobs.gazonuz.validators;

import java.util.Objects;

public class AdminValidator {
    public static boolean isNotNullAndGreaterThan0(Number number) {
        return Objects.nonNull(number) && number.doubleValue() > 0;
    }
}
