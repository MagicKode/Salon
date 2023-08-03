package com.example.salon.configuration;

import lombok.Getter;

@Getter
public enum WhiteList {
    ALL(new String[]{
            "/**/auth",
            "/**/register"
    });

    WhiteList(String[] list) {
        this.list = list;
    }

    private final String[] list;
}
