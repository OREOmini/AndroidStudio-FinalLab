package com.example.wangyunwen.finallab;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView todoList;
    MyAdapter myAdapter;
    Button addButton;
    List<TodoItem> list = new ArrayList<TodoItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addItem();
        getViews();

        myAdapter = new MyAdapter(this, list);
        todoList.setAdapter(myAdapter);

        todoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("---", "");
                Intent intent = new Intent(MainActivity.this, ItemDetails.class);
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            public void setDialogView(View v) {
                //DatePicker date_picker = (DatePicker) v.findViewById(R.id.date_picker);
                TimePicker time_picker = (TimePicker) v.findViewById(R.id.time_picker);

                time_picker.setIs24HourView(true);
            }

            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                View view1 = LayoutInflater.from(MainActivity.this).inflate(R.layout.add_dialog, null);
                setDialogView(view1);

                Dialog add_item = new AlertDialog.Builder(context).
                        setTitle("增加新备忘").
                        setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Context context = getApplicationContext();
                                MyAdapter myAdapter1 = new MyAdapter(context, list);
                                todoList.setAdapter(myAdapter1);
                            }
                        }).
                        setView(view1).
                        create();
                add_item.show();
            }
        });


    }

    public void getViews(){
        todoList = (ListView) findViewById(R.id.todoList);
        addButton = (Button) findViewById(R.id.add);
    }

    private void addItem() {
        TodoItem temp = new TodoItem("homework");
        list.add(temp);
        list.add(new TodoItem("hahhhahh"));
    }
}
