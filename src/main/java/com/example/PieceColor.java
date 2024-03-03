package com.example;

public enum PieceColor {
    WHITE("white"),
    BLACK("black");
    private String color;
    PieceColor(String color){
        this.color = color;
    }

    String getColor(){
        return color;
    }
    void setColor(String color){
        this.color = color;
    }
}
