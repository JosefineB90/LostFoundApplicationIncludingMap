package com.example.lostfoundapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import com.example.lostfoundapplication.AppDatabase;
import com.example.lostfoundapplication.CreateNewAdvertActivity;
import com.example.lostfoundapplication.LostFoundDao;
import com.example.lostfoundapplication.R;
import com.example.lostfoundapplication.ShowItemsActivity;

public class MainActivity extends AppCompatActivity {

    Button createNewAdvertButton, showAllItemsButton;
    AppDatabase db;
    LostFoundDao lostFoundDao;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize database
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "lost-found-database").allowMainThreadQueries().build();
        lostFoundDao = db.lostFoundDao();

        // Bind buttons
        createNewAdvertButton = findViewById(R.id.createNewAdvertButton);
        showAllItemsButton = findViewById(R.id.showAllItemsButton);

        // Navigate to Create New Advert screen
        createNewAdvertButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateNewAdvertActivity.class);
            startActivity(intent);
        });

        // Navigate to Show All Items screen
        showAllItemsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ShowItemsActivity.class);
            startActivity(intent);
        });
    }
}
