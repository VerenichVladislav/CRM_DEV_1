package com.example.aviasales2.entity;

public enum RoomType {
    ONE_SINGLE_BED(3),
    TWO_SINGLE_BEDS(5),
    ONE_DOUBLE_BED(3),
    FOUR_SINGLE_BEDS(1);

    private int numberOfTypes;

    RoomType(int numberOfTypes) {
        this.numberOfTypes = numberOfTypes;
    }
}