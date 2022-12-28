package com.rodrigosolanomarques.goldenraspberryawardsapi.domain.enumerator;

import java.util.Arrays;

public enum EWinner {
    NO("No"),
    YES("Yes");

    final String value;

    EWinner(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static EWinner fromString(String value) {
        return Arrays.stream(values())
                .filter(winner -> winner.value.equalsIgnoreCase(value))
                .findFirst()
                .orElse(EWinner.NO);
    }
}