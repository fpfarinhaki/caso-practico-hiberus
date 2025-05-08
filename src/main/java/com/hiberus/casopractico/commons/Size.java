package com.hiberus.casopractico.commons;

import lombok.Getter;

@Getter
public enum Size {
    SMALL("S"),
    MEDIUM("M"),
    LARGE("L");

    private final String code;

    Size(String code) {
        this.code = code;
    }

    public static Size fromValue(String code) {
        for (Size size : Size.values()) {
            if (size.code.equals(code)) {
                return size;
            }
        }
        throw new IllegalArgumentException("Invalid size value: " + code);
    }
}
