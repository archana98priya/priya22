package com.example.archanapriya.client_order;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private EditText email,pass;
    DatabaseReference mDatabase;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email =(EditText) findViewById(R.id.editText);
        pass = (EditText) findViewById(R.id.editText2);

        mAuth = FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("users");
    }

    public void onButtonClick2(View view){
        final String email_text= email.getText().toString().trim();
        String pass_text = pass.getText().toString().trim();

        if(!TextUtils.isEmpty(email_text) && !TextUtils.isEmpty(pass_text))
        {
            mAuth.createUserWithEmailAndPassword(email_text,pass_text).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    String user_id = mAuth.getCurrentUser().getUid();
                    DatabaseReference current_user = mDatabase.child(user_id);
                    current_user.child("name").setValue(email_text);
                }
            });
        }
    }
}
