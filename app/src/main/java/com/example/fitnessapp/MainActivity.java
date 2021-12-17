package com.example.fitnessapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

//login page
public class MainActivity extends AppCompatActivity {

    private EditText userName;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;
    private TextView userRegistration;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    User user ;
    String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = (EditText)findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etPassword);
        Info = (TextView)findViewById(R.id.tvInfo);
        Login = (Button)findViewById(R.id.btnLogin);
        userRegistration = (TextView)findViewById(R.id.tvRegister);


        Info.setText("No of attempts remaining: 5");

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        //to check if user has logged in the app
        //checks with the database if the user has logged in if he has it takes it to the next activity without asking to login again
        FirebaseUser user= firebaseAuth.getCurrentUser();
        if(user != null){
            finish();
            startActivity(new Intent(MainActivity.this, profile_activity.class));
        }

        userRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, sign_up_activity.class));
            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(userName.getText().toString().trim(), Password.getText().toString().trim());
            }
        });
    }

    private void validate(String userName, String Password){
        progressDialog.setMessage("Please wait until you are verified");
        progressDialog.show();
        Log.i("loginAuth",userName + " "+ Password);
        firebaseAuth.signInWithEmailAndPassword(userName,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    String userId = firebaseAuth.getCurrentUser().getUid();

//                    db.collection("userData").document(userId).get()
//                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                                @Override
//                                public void onSuccess(@NonNull @NotNull DocumentSnapshot documentSnapshot) {
//                                    user= documentSnapshot.toObject(User.class);
//                                    if(user.isFlag())
//                                    {
//                                        Intent intent = new Intent(MainActivity.this, GetAccountDetails.class);
//                                        intent.putExtra("Email",userName);
//                                        intent.putExtra("Password",Password);
//                                        startActivity(intent);
//                                    }
//                                    else
//                                    {
//                                        startActivity(new Intent(MainActivity.this, profile_activity.class));
//                                    }
//                                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
//                                }
//                            });
                    Bundle extras = getIntent().getExtras();
                    if (extras == null) {
                       flag="false";
                    }
                    else
                    {
                        flag = extras.getString("Flag");
                    }
                    if(flag.equalsIgnoreCase("true"))
                    {
                        Intent intent = new Intent(MainActivity.this, GetAccountDetails.class);
                        intent.putExtra("Email",userName);
                        intent.putExtra("Password",Password);
                        startActivity(intent);
                    }
                    else
                    {
                        startActivity(new Intent(MainActivity.this, profile_activity.class));
                    }
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                }else
                {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                    Log.i("loginAuth",userName + " "+ Password+ " 2");
                    counter--;
                    Info.setText("No of attempts remaining: " + counter);
                    progressDialog.dismiss();
                    if(counter == 0){
                        Login.setEnabled(false);
                    }
                }
            }
        });
    }
}