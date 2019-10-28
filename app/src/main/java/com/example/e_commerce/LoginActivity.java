package com.example.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e_commerce.prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rey.material.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    CheckBox rememberMeChkb;
    @BindView(R.id.forget_password_link)
    TextView forgetPasswordLink;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.admin_panel_link)
    TextView adminPanelLink;
    private FirebaseAuth firebaseAuth;
    String useremail;
    String userpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        email=findViewById(R.id.login_phone_number_input);
        password=findViewById(R.id.login_password_input);
        rememberMeChkb=findViewById(R.id.remember_me_chkb);
        Paper.init(this);

        firebaseAuth = FirebaseAuth.getInstance();
    }


    public void login(View view) {

         useremail = email.getText().toString().trim();
         userpassword = password.getText().toString().trim();

        if (TextUtils.isEmpty(useremail)) {
            Toast.makeText(LoginActivity.this, "please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(userpassword)) {
            Toast.makeText(LoginActivity.this, "please enter password", Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(useremail, userpassword)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), HomePage.class));
                            if(rememberMeChkb.isChecked()){
                                Paper.book().write(Prevalent.email,useremail);
                                Paper.book().write(Prevalent.password,userpassword);
                            }


                        } else {
                            Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }
}
