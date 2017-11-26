package sandbox;

import java.text.DecimalFormat;
import java.util.Random;

public class randomtests {

    public static void main(String[] args) {
        Random rand = new Random();
        int num1 = 0 + (rand.nextInt(9) * 10) + rand.nextInt(9);
        int num2 = rand.nextInt(999);
        int num3 = rand.nextInt(10000);

        DecimalFormat df3 = new DecimalFormat("000"); // 3 zeros
        DecimalFormat df4 = new DecimalFormat("0000"); // 4 zeros

        String phoneNumber = df3.format(num1) + " " + df3.format(num2) + " " + df4.format(num3);

        System.out.println(phoneNumber);
    }
}