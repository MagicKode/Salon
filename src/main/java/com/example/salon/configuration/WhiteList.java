package com.example.salon.configuration;

import lombok.Getter;

@Getter
public enum WhiteList {
    ALL(new String[]{
            "/**/auth",
            "/**/register",
            "/**/swagger-ui.html",
            "/**/swagger-ui/*",
            "/**/v3/**"
    });

    WhiteList(String[] list) {
        this.list = list;
    }

    private final String[] list;
}
