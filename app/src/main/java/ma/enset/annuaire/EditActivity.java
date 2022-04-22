package ma.enset.annuaire;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class EditActivity extends AppCompatActivity {
    AppDataBase database;
    EditText firstName;
    EditText lastName;
    EditText job;
    EditText phone;
    EditText email;
    String string;
    TextView id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        database=AppDataBase.getInstance(this);

       
        setContentView(R.layout.activity_edit);

        firstName = (EditText) findViewById(R.id.firstNameInput);
        lastName = (EditText) findViewById(R.id.lastNameInput);
        job = (EditText) findViewById(R.id.jobInput);
        phone = (EditText) findViewById(R.id.phoneInput);
        email = (EditText) findViewById(R.id.emailInput);

        Intent i = getIntent();
        if(i.hasExtra("ID")){
            string = i.getStringExtra("ID");
        }
        Contact contact = database.contactDAO().findByID(Integer.parseInt(string));


        lastName.setText(contact.getLastName());
        firstName.setText(contact.getFirstName());
        job.setText(contact.getJob());
        phone.setText(contact.getPhone());
        email.setText(contact.getEmail());
        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(EditActivity.this, MainActivity.class);
                startActivity(myIntent);
            }
        });

    }

    public void save(View view){

        database.contactDAO().update(new Contact(Integer.parseInt(string),lastName.getText().toString(),firstName.getText().toString(),job.getText().toString(),phone.getText().toString(),email.getText().toString()));
        Toast.makeText(EditActivity.this,"Contact modifié !",Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(EditActivity.this, MainActivity.class);
        startActivity(myIntent);
    }


}