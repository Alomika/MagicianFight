package model;

import java.util.concurrent.ThreadLocalRandom;

public class RandomNumberGenerator {
    public static int generateRandomNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
