package com.example.lostfoundapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "lost_found_items")
public class LostFoundItem {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String type;       // "Lost" or "Found"
    private String name;
    private String phone;
    private String description;
    private String date;
    private String location;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    // Required for Room's delete() method to match entities correctly
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof LostFoundItem)) return false;
        LostFoundItem other = (LostFoundItem) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
