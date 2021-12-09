package xyz.javaee.mycontentobserver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class StudentsAdapter extends ArrayAdapter {
    private int resourceId;

    /**
     * 产品列表的构造方法
     *
     * @param context            上下文对象
     * @param textViewResourceId 对应单个item的样式
     * @param objects            要展示的数据
     */
    public StudentsAdapter(Context context, int textViewResourceId, List<Student> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    /**
     * @param position    对应item视图
     * @param convertView 一个旧的item元素
     * @param parent
     * @return 构建出来的新元素
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        Student student = (Student) getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);
//        View view = convertView

        TextView name = view.findViewById(R.id.name);
        name.setText(student.getName());

        TextView stuNum = view.findViewById(R.id.stuNum);
        stuNum.setText(student.getStuNum());

        TextView sex = view.findViewById(R.id.sex);
        sex.setText(student.getSex() == 1 ? "男" : "女");

        TextView classRoom = view.findViewById(R.id.classRoom);
        classRoom.setText(student.getClassRoom());

        return view;
    }
}
