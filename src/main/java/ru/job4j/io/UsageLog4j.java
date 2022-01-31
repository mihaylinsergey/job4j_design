package ru.job4j.io;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Cat";
        int age = 10;
        byte eyes = 2;
        short paws = 4;
        long pawLength = 20;
        float tailLength = 25.5f;
        double catLength = 45.9;
        char gender = 'M';
        boolean healthy = true;
        LOG.debug("User info name : {}, age : {}, eyes : {}, paws : {}, pawLength : {}, tailLength : {}, "
               + "catLength : {}, gender : {}, healthy : {}", name, age, eyes, paws, pawLength, tailLength, catLength,
                gender, healthy);
    }
}