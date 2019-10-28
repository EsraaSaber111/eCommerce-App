package com.example.e_commerce;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.paperdb.Paper;

public class HomePage extends AppCompatActivity {

    @BindView(R.id.logout)
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.logout)
    public void onViewClicked() {
        Paper.book().destroy();
        Intent intent = new Intent(HomePage.this, MainActivity.class);
        startActivity(intent);
    }
}
