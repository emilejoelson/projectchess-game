package com.example.entities;

import com.example.PieceColor;
import com.example.Position;
import com.example.interfaces.ChessPiece;

import java.util.ArrayList;
import java.util.List;

public class Queen implements ChessPiece {

    private  Position position;
    private PieceColor color;
    private  boolean moved;
    public Queen(Position position,PieceColor color){
        this.position = position;
        this.color = color;
        moved = false;
    }
    @Override
    public Position getPosition() {
        return position;
    }
    @Override
    public boolean isBlack() {
        return color == PieceColor.BLACK;
    }

    @Override
    public boolean isWhite() {
        return color == PieceColor.WHITE;
    }


    @Override
    public List<Position> getPossibleMoves() {
        List<Position> availableMoves = new  ArrayList<>();
        int [][] directions = {{1,1},{-1,-1},{1,-1},{-1,1}};
        for(int i=0;i<8;i++){
            if(i!=getPosition().getX()){
                availableMoves.add(new Position(i,getPosition().getY()));
            }
            if(i!=getPosition().getY()){
                availableMoves.add(new Position(getPosition().getX(),i));
            }
        }
         for(int [] dir : directions){
             int dx = dir[0];
             int dy = dir[1];
             for(int i=1;i<8;i++){
                 int newX = getPosition().getX()+i*dx;
                 int newY = getPosition().getY()+i*dy;
                 Position diagonaLine = new Position(newX,newY) ;
                 if(isValidMoves(diagonaLine)){
                     availableMoves.add(diagonaLine);
                 }
             }

         }
        return availableMoves;
    }
    @Override
    public List<Position> getPossibleAttacks() {
       return  getPossibleMoves();
    }

    @Override
    public boolean isMoveValid(Position position) {
        List<Position> availableMoves = getPossibleMoves();
        return availableMoves.contains(position);
    }

    private boolean isValidMoves(Position position){
        int x = position.getX();
        int y = position.getY();
        return  x>=0 && x<8 && y>=0 && y<8;
    }
}
