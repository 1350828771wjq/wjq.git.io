package game;

import java.util.Random;

public class CodeUtil {
    public static String getCode() {
        String code = "";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int type = random.nextInt(3);
            switch (type){
                case 0:
                    code += (char) (random.nextInt(26)+65);
                    break;
                case 1:
                    code += (char) (random.nextInt(26)+97);
                    break;
                default:
                    code += random.nextInt(10);
                    break;
            }
        }
        return code;
    }

}
