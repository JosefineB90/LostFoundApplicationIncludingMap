package com.example.lostfoundapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class ItemDetailActivity extends AppCompatActivity {

    private TextView textType, textDescription, textDate, textLocation;
    private Button buttonRemove;

    private AppDatabase db;
    private LostFoundDao lostFoundDao;
    private LostFoundItem currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);

        // Bind views
        textType = findViewById(R.id.textType);
        textDescription = findViewById(R.id.textDescription);
        textDate = findViewById(R.id.textDate);
        textLocation = findViewById(R.id.textLocation);
        buttonRemove = findViewById(R.id.buttonRemove);

        if (buttonRemove == null) {
            Toast.makeText(this, "REMOVE button not found", Toast.LENGTH_LONG).show();
            return;
        }

        // Init database
        db = Room.databaseBuilder(getApplicationContext(),
                        AppDatabase.class, "lost-found-database")
                .allowMainThreadQueries()
                .build();

        lostFoundDao = db.lostFoundDao();

        // Get item ID
        int itemId = getIntent().getIntExtra("itemId", -1);
        if (itemId == -1) {
            Toast.makeText(this, "Invalid item ID", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Get item from DB
        currentItem = lostFoundDao.getItemById(itemId);
        if (currentItem == null) {
            Toast.makeText(this, "Item not found in DB", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Populate UI
        textType.setText("Type: " + currentItem.getType());
        textDescription.setText("Description: " + currentItem.getDescription());
        textDate.setText("Date: " + currentItem.getDate());
        textLocation.setText("Location: " + currentItem.getLocation());

        // Handle button click
        buttonRemove.setOnClickListener(v -> {
            if (currentItem != null && currentItem.getId() > 0) {
                lostFoundDao.delete(currentItem);
                Toast.makeText(this, "Item removed", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "Could not delete item", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
