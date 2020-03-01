package com.wonder.youth.mvvmtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE="com.wonder.youth.mvvmtest.EXTRA_TITLE";
    public static final String EXTRA_ID="com.wonder.youth.mvvmtest.EXTRA_ID";
    public static final String EXTRA_DESCRIPTION="com.wonder.youth.mvvmtest.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY="com.wonder.youth.mvvmtest.EXTRA_PRIORITY";

    private EditText editTexttitle;
    private EditText editTextdescription;
    private NumberPicker numberpicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTexttitle=findViewById(R.id.edittexttitle);
        editTextdescription=findViewById(R.id.edittextdescription);
        numberpicker=findViewById(R.id.numberpicker);

        numberpicker.setMinValue(1);
        numberpicker.setMaxValue(10);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        Intent intent=getIntent();
        if (intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            editTexttitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextdescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            numberpicker.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));
        }else {

            setTitle("Add Note");
        }

    }
    private void saveNote(){
        String title=editTexttitle.getText().toString();
        String description=editTextdescription.getText().toString();
        int priority=numberpicker.getValue();
        if (title.trim().isEmpty()|| description.trim().isEmpty()){
            Toast.makeText(this, "Plaease insert a title and description", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data=new Intent();
        data.putExtra(EXTRA_TITLE,title);
        data.putExtra(EXTRA_DESCRIPTION,description);
        data.putExtra(EXTRA_PRIORITY,priority);
        int id=getIntent().getIntExtra(EXTRA_ID,-1);
        if (id!=-1){
            data.putExtra(EXTRA_ID,id);
        }

        setResult(RESULT_OK,data);
        finish();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.save_note:
                saveNote();
                return true;

                default:
                    return  super.onOptionsItemSelected(item);
        }
    }
}
