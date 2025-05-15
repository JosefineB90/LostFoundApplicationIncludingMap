package com.example.lostfoundapplication;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import java.util.Calendar;

public class CreateNewAdvertActivity extends AppCompatActivity {

    EditText editName, editPhone, editDescription, editDate, editLocation;
    RadioGroup radioGroup;
    RadioButton radioLost, radioFound;
    Button btnSave;

    AppDatabase db;
    LostFoundDao lostFoundDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_advert);

        // Initialize views
        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        editDescription = findViewById(R.id.editDescription);
        editDate = findViewById(R.id.editDate);
        editLocation = findViewById(R.id.editLocation);
        radioGroup = findViewById(R.id.radioGroup);
        radioLost = findViewById(R.id.radioLost);
        radioFound = findViewById(R.id.radioFound);
        btnSave = findViewById(R.id.btnSave);

        // Set up database
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "lost-found-database").allowMainThreadQueries().build();
        lostFoundDao = db.lostFoundDao();

        // Open calendar when date is clicked
        editDate.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    CreateNewAdvertActivity.this,
                    (DatePicker view, int selectedYear, int selectedMonth, int selectedDay) -> {
                        String formattedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        editDate.setText(formattedDate);
                    },
                    year, month, day
            );

            datePickerDialog.show();
        });

        // Save button logic
        btnSave.setOnClickListener(v -> {
            String type = radioLost.isChecked() ? "Lost" : radioFound.isChecked() ? "Found" : "";
            String name = editName.getText().toString().trim();
            String phone = editPhone.getText().toString().trim();
            String description = editDescription.getText().toString().trim();
            String date = editDate.getText().toString().trim();
            String location = editLocation.getText().toString().trim();

            if (type.isEmpty() || name.isEmpty() || phone.isEmpty() ||
                    description.isEmpty() || date.isEmpty() || location.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            LostFoundItem item = new LostFoundItem();
            item.setType(type);
            item.setName(name);
            item.setPhone(phone);
            item.setDescription(description);
            item.setDate(date);
            item.setLocation(location);

            lostFoundDao.insert(item);
            Toast.makeText(this, "Item saved", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
