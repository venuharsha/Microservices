package com.example.school.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.UUID;
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RandomGenerator {

    public static String randomUUID(){
       return UUID.randomUUID().toString();
    }
}
