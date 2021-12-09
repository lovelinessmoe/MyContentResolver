package xyz.javaee.mycontentresolver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button add = findViewById(R.id.add);
        Button modify = findViewById(R.id.modify);
        Button delete = findViewById(R.id.delete);

        add.setOnClickListener(this);
        modify.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        try {
            EditText stuNum_ET = findViewById(R.id.stuNum);
            EditText name_ET = findViewById(R.id.name);
            RadioGroup sex_RG = findViewById(R.id.sex);
            EditText classRoom_ET = findViewById(R.id.classRoom);

            String stuNum = stuNum_ET.getText().toString();
            String name = name_ET.getText().toString();
            String classRoom = classRoom_ET.getText().toString();

            //-1默认未选择
            int sex = -1;
            if (sex_RG.getCheckedRadioButtonId() == R.id.sex_nan) {
                sex = 1;
            } else if (sex_RG.getCheckedRadioButtonId() == R.id.sex_nv) {
                sex = 0;
            }

            ContentResolver contentResolver = getContentResolver();
            Student student = new Student(name, stuNum, sex, classRoom);

            String sql = "";
            switch (v.getId()) {
                case R.id.add:
                    if ("".equals(student.getStuNum()) || student.getStuNum().length() != 11) {
                        throw new Exception("请输入正确的学号");
                    } else if ("".equals(student.getName()) || student.getName().length() > 10) {
                        throw new Exception("请输入正确的名字,长度不超过10位");
                    } else if ("".equals(student.getClassRoom()) || student.getName().length() > 20) {
                        throw new Exception("请输入正确的班级,长度不超过20位");
                    } else if (student.getSex() == -1) {
                        throw new Exception("请选择性别");
                    }

                    sql = "INSERT INTO student " +
                            "(stuNum,name, sex, classRoom)" +
                            " VALUES ("
                            + "'" + student.getStuNum() + "'" + ","
                            + "'" + student.getName() + "'" + ","
                            + student.getSex() + ","
                            + "'" + student.getClassRoom() + "'"
                            + ")";
                    Log.i("sqlINSERT", sql);
//                    Uri uri = Uri.parse("content://loveliness");
//                    contentResolver.query(uri, null, sql, null, "execSQL");
                    MyContentResolver.execSQL(this, sql);
                    Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.modify:
                    if ("".equals(student.getStuNum()) || student.getStuNum().length() != 11) {
                        throw new Exception("请输入正确的学号");
                    } else if ("".equals(student.getName()) || student.getName().length() > 10) {
                        throw new Exception("请输入正确的名字,长度不超过10位");
                    } else if ("".equals(student.getClassRoom()) || student.getName().length() > 20) {
                        throw new Exception("请输入正确的班级,长度不超过20位");
                    } else if (student.getSex() == -1) {
                        throw new Exception("请选择性别");
                    }

                    sql = "UPDATE student SET " +
                            "name = " + "'" + student.getName() + "'" + "," +
                            "sex = " + student.getSex() + ", " +
                            "classRoom = " + "'" + student.getClassRoom() + "'" +
                            "WHERE stuNum = " + "'" + student.getStuNum() + "'";
                    Log.i("sqlUPDATE", sql);
                    MyContentResolver.execSQL(this, sql);
                    Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.delete:
                    if ("".equals(student.getStuNum()) || student.getStuNum().length() != 11) {
                        throw new Exception("请输入正确的学号");
                    }
                    sql = "DELETE FROM student " +
                            "WHERE stuNum = " + "'" + student.getStuNum() + "'";
                    Log.i("sqlDELETE", sql);
                    MyContentResolver.execSQL(this, sql);
                    Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();
                    break;
            }
        } catch (SQLiteConstraintException e) {
            //主键重复的异常
            System.out.println(e.getMessage());
            Toast.makeText(getApplicationContext(), "学号重复", Toast.LENGTH_SHORT).show();
        } catch (SQLiteException e) {
            System.out.println(e.getMessage());
            Toast.makeText(getApplicationContext(), "数据库错误", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            //如果构建学生时出现错误，sql也不执行
            System.out.println(e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}