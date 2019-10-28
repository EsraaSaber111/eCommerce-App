package com.example.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.e_commerce.prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    @BindView(R.id.main_login_btn)
    Button mainLoginBtn;
    @BindView(R.id.main_join_now_btn)
    Button mainJoinNowBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Paper .init(this);
        firebaseAuth = FirebaseAuth.getInstance();
        String useremail= Paper.book().read(Prevalent.email);
        String userpassword= Paper.book().read(Prevalent.password);
        if (useremail != "" && userpassword != "")
        {
            if(!TextUtils.isEmpty(useremail) && !TextUtils.isEmpty(useremail))
            {
                AllowAccess(useremail,userpassword);
            }

        }

    }

    private void AllowAccess(String email, String password) {
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(),HomePage.class));



                        } else {
                            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }


    public void register(View view) {
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void login(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }



}
