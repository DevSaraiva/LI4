package com.example.MinhoPark.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.MinhoPark.R;

import androidx.appcompat.app.AppCompatActivity;

public class FavouritesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        Button buttonFavs = findViewById(R.id.buttonBackFavs);

        buttonFavs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FavouritesActivity.this, MainActivity.class);    //@click go back to MainActivity (initial menu)
                startActivity(intent);
            }
        });
    }
}