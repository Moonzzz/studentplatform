package com.moon.studentplatform.util;

import java.security.MessageDigest;

import static com.moon.studentplatform.util.HexUtils.bytesToHexString;

/**
 * @author Moon
 */
public class EncodeUtils {
    public static String encode(String data, String slat, String mode) {
        String string = "";
        try {
            MessageDigest md = MessageDigest.getInstance(mode);
            md.update(slat.getBytes());
            byte[] hash = md.digest(data.getBytes());

            string = bytesToHexString(hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }


    /*@Test
    void shiftTest() {
        System.out.println(-5 & 0xF0 >>> 4);
        int i = 0xF0;
        System.out.println(Integer.toBinaryString(i));
        System.out.println(Integer.toBinaryString(-5 >>> 4));
        System.out.println(Integer.toBinaryString((-5 >>> 4) & 0xFF));
        System.out.println(String.format("%032d", Integer.valueOf(Integer.toBinaryString(5))));
        System.out.println(-5 >>> 4);
        System.out.println(-5 >> 4);
        System.out.println(-123 >> 5);
        System.out.println(-123 >>> 5);
    }*/

}