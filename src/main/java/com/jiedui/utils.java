package com.jiedui;

public class utils {
    public static String GPF(int x, int y) {

        if (x < y) {
            StringBuilder outcom = new StringBuilder();
            outcom.append(x);
            outcom.append("/");
            outcom.append(y);
            return outcom.toString();

        } else if (x > y) {
            int a = x / y;
            int b = (x % y);
            StringBuilder outcom1 = new StringBuilder();
            String A = String.valueOf(a);
            String B = String.valueOf(b);
            outcom1.append(A);
            outcom1.append("'");
            outcom1.append(B);
            outcom1.append("/");
            outcom1.append(y);
            return outcom1.toString();


        }
        return null;
    }
}
