package com.mukul.model;

public class Room {
    private final int id;
    private final String phone;
    private final boolean isReserved;

    public Room(int id, String phone, boolean isReserved) {
        this.id = id;
        this.phone = phone;
        this.isReserved = isReserved;
    }

    public int getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isReserved() {
        return isReserved;
    }
}
