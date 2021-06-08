package sg.edu.rp.c346.id20011155.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText doTask;
    Button btnAdd;
    Button btnDlt;
    Button btnClear;
    ListView lvTask;
    Spinner spnYesNo;

    ArrayList<String> alTask;
    ArrayAdapter<String> aaTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        doTask = findViewById(R.id.doTask);
        btnAdd = findViewById(R.id.add);
        btnClear = findViewById(R.id.clear);
        lvTask = findViewById(R.id.lv);
        btnDlt = findViewById(R.id.delete);
        spnYesNo = findViewById(R.id.spinnerTask);

        alTask = new ArrayList<>();

        aaTask = new ArrayAdapter<> (MainActivity.this, android.R.layout.simple_list_item_1, alTask);
        lvTask.setAdapter(aaTask);

        spnYesNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        btnAdd.setEnabled(true);
                        btnDlt.setEnabled(false);
                        doTask.setText("");
                        doTask.setHint("Type in a new task here");
                        break;
                    case 1:
                        btnAdd.setEnabled(false);
                        btnDlt.setEnabled(true);
                        doTask.setText("");
                        doTask.setHint("Type in the index of the task to be removed");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String task = doTask.getText().toString();

                if (!doTask.getText().toString().equalsIgnoreCase("")) {
                    alTask.add(task);
                    aaTask.notifyDataSetChanged();
                } else if (doTask.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(MainActivity.this, "Please Indicate Task!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alTask.isEmpty()==true) {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_LONG).show();
                } else if (doTask.getText().toString().equalsIgnoreCase("")) {
                    Toast.makeText(MainActivity.this, "Please Indicate Index!", Toast.LENGTH_LONG).show();
                } else {
                    String num = doTask.getText().toString();
                    if (Integer.parseInt(num) > alTask.size()-1) {
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_LONG).show();
                    } else {
                        alTask.remove(Integer.parseInt(doTask.getText().toString()));
                        aaTask.notifyDataSetChanged();
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alTask.isEmpty() == false) {
                    doTask.setText("");
                    alTask.clear();
                    aaTask.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Nothing to be cleared", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}