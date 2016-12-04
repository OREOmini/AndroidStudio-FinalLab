package com.example.wangyunwen.finallab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import java.util.List;

/**
 * Created by wangyunwen on 16/12/4.
 */
public class MyAdapter extends BaseAdapter {
    private List<TodoItem> list;
    private Context context;

    public MyAdapter(Context context, List<TodoItem> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        if(list == null)
            return 0;
        else
            return list.size();
    }

    @Override
    public TodoItem getItem(int i) {
        if(list == null)
            return null;
        else
            return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View convertView;
        ViewHolder viewHolder;

        if(view == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
            viewHolder = new ViewHolder();
            viewHolder.todo = (CheckBox) convertView.findViewById(R.id.checkbox);
            convertView.setTag(viewHolder);
        } else {
            convertView = view;
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.todo.setText(list.get(i).getTodo());

        return convertView;
    }
    private class ViewHolder {
        public CheckBox todo;
    }
}
