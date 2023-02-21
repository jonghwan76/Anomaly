package com.realblox.dimetime.util;

import java.io.Reader;
import java.io.Writer;
import java.sql.Clob;

public class ClobUtil {

    public static String read(Clob clob) {

        char[] result = (char[])null;
        try {
            Reader reader = clob.getCharacterStream();
            result = new char[0];
            char[] buf = new char[4096];
            int size = 0;
            while ((size = reader.read(buf)) != -1) {
                char[] new_result = new char[result.length + size];
                System.arraycopy(result, 0, new_result, 0, result.length);
                System.arraycopy(buf, 0, new_result, result.length, size);
                result = new_result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(result);
    }

    public static void write(Clob clob, String text) {
        try {
            Writer out = clob.setCharacterStream(1L);
            out.write(text);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}