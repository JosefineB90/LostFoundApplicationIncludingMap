package com.example.lostfoundapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

public class ShowItemsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LostFoundAdapter adapter;
    AppDatabase db;
    LostFoundDao lostFoundDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_items);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up the Room database
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "lost-found-database").allowMainThreadQueries().build();
        lostFoundDao = db.lostFoundDao();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Load the updated list of items whenever the activity becomes visible
        List<LostFoundItem> items = lostFoundDao.getAllItems();

        adapter = new LostFoundAdapter(items, item -> {
            Intent intent = new Intent(ShowItemsActivity.this, ItemDetailActivity.class);
            intent.putExtra("itemId", item.getId());
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
    }
}
