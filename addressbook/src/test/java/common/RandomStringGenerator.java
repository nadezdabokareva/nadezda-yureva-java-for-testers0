package common;

import java.util.Random;

public class RandomStringGenerator {

    public static String randomString(int n) {
        var rnd = new Random();
        var result = "";
        for (int i = 0;  i < n; i++) {
            result = result + (char)('a' + rnd.nextInt(25));
        }

        //Эта часть под комментарием, так как иначе у меня создаются негативные кейсы в позитивном тесте
//        if (n<20) {
//            result = result + '\'';
//        }
        return result;
    }
}