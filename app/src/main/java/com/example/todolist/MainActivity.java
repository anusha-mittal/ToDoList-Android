package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    EditText etTask;
    Button btnAdd;
    ListView lvTasks;

    ArrayList<String> items;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask=findViewById(R.id.etTask);
        btnAdd=findViewById(R.id.btnAdd);
        lvTasks=findViewById(R.id.lvTasks);

        items=FileHelper.readData(this);

        arrayAdapter=new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                items
        );
        lvTasks.setAdapter(arrayAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnAdd:
                    String itemEntered=etTask.getText().toString();
                    arrayAdapter.add(itemEntered);
                    etTask.setText(" ");

                    FileHelper.writeData(items,MainActivity.this);
                        Toast.makeText(MainActivity.this, "Item added", Toast.LENGTH_SHORT).show();
                    break;
                }

            }
        });

        lvTasks.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        items.remove(position);
        arrayAdapter.notifyDataSetChanged();
        Toast.makeText(this, "Item deleted", Toast.LENGTH_SHORT).show();
    }
}
