package sg.edu.rp.c346.id20018354.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView ettask;
    ToggleButton btnAdd,btnDelete;
    Button btnClear;
    ListView lvTask;
    Spinner spnAddDelete;
    ArrayList<String> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ettask=findViewById(R.id.editTextAddTask);
        btnAdd=findViewById(R.id.toggleButtonAdd);
        btnClear=findViewById(R.id.buttonClear);
        lvTask=findViewById(R.id.ToDoList);
        btnDelete=findViewById(R.id.toggleButtonDelete);
        spnAddDelete=findViewById(R.id.spinner);
        taskList=new ArrayList<String>();
        ArrayAdapter<String> tasks = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,taskList);
        lvTask.setAdapter(tasks);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTask=ettask.getText().toString();
                taskList.add(newTask);
                tasks.notifyDataSetChanged();
                ettask.setText("");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskList.clear();
                tasks.notifyDataSetChanged();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (taskList.size() == 0) {
                    Toast.makeText(getApplicationContext(), "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    int index = Integer.parseInt(ettask.getText().toString());
                    if (taskList.size() <= index) {
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        taskList.remove(index);
                        tasks.notifyDataSetChanged();
                        ettask.setText(null);
                    }
                }
            }
        });
        spnAddDelete.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        ettask.setHint("Type in a new task here");
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        break;
                    case 1:
                        ettask.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}