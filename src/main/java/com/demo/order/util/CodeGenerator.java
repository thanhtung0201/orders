package com.demo.order.util;

import java.util.UUID;

public class CodeGenerator {


    public static String generateCode() {
        return String.format("%s%s", System.currentTimeMillis(), randomUUID()).toUpperCase();
    }

    private static String randomUUID() {

        UUID randomUUID = UUID.randomUUID();

        return randomUUID.toString().replaceAll("_", "").substring(0, 7);

    }

    public static void main(String[] args) {
        System.out.println(generateCode());
    }

}
