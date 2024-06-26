package studio.jact.gamebox.game.profit_pioneer.core;

import java.util.Random;

public class ValueGenerator {
    public static int generate(int min, int max, int step) {
        Random random = new Random();
        int numValues = (max - min) / step + 1;

        int randomIndex = random.nextInt(numValues);
        int randomValue = min + randomIndex * step;

        return randomValue;
    }
}
