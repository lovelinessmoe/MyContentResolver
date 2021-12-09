package xyz.javaee.mycontentobserver;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class MyContentResolver {
    private final static Uri URI = Uri.parse("content://loveliness");

    public static Cursor rawQuery(Context cxt, String sql) {
        try {
            Cursor rawQuery = cxt.getContentResolver().query(URI, null, sql, null, "rawQuery");
            System.out.println(rawQuery);
            return rawQuery;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void execSQL(Context cxt, String sql) {
        try {
            System.out.println(URI);
            System.out.println(sql);
            System.out.println(cxt);
            cxt.getContentResolver().query(URI, null, sql, null, "execSQL");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
