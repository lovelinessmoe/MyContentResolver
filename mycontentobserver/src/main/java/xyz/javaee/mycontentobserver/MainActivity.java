package xyz.javaee.mycontentobserver;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uri URI = Uri.parse("content://loveliness");

        //绑定观察者
        getContentResolver().registerContentObserver(URI, true, new MyObserver(new Handler()));
        reloadStudent();
    }

    public void reloadStudent() {
        Cursor c = MyContentResolver.rawQuery(this, "SELECT * FROM student");
        ArrayList<Student> students = new ArrayList<>();
        //循环将数据结果保存到list里
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            @SuppressLint("Range") String stuNum1 = c.getString(c.getColumnIndex("stuNum"));
            @SuppressLint("Range") String name1 = c.getString(c.getColumnIndex("name"));
            @SuppressLint("Range") String classRoom1 = c.getString(c.getColumnIndex("classRoom"));
            @SuppressLint("Range") int sex1 = c.getInt(c.getColumnIndex("sex"));
            Student student1 = new Student(name1, stuNum1, sex1, classRoom1);
            students.add(student1);
        }

        //配置适配器
        StudentsAdapter adapter = new StudentsAdapter(this, R.layout.data_show_single, students);
        ListView listView = findViewById(R.id.list_student);
        //将适配器关联到ListView
        listView.setAdapter(adapter);
    }

    private class MyObserver extends ContentObserver {
        public MyObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
            reloadStudent();
        }
    }
}