package com.example.entities;

import com.example.PieceColor;
import com.example.Position;
import com.example.interfaces.ChessPiece;

import java.util.ArrayList;
import java.util.List;

public class King implements ChessPiece {
     private  Position position;
     private PieceColor color;
     private  boolean moved ;
     public  King(Position position , PieceColor color){
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
        List<Position> availableMoves = new ArrayList<>();
        int [] dirVerticals = {-1,1};
        for(int i : dirVerticals){
            if(i!= getPosition().getY()){
                availableMoves.add(new Position(i+ getPosition().getX(), getPosition().getY()));
            }
            if(i!= getPosition().getX()){
                availableMoves.add(new Position(getPosition().getX(), getPosition().getY()+i));
            }
        }
      int [][] directions = {{1,1},{-1,-1},{1,-1},{-1,1}};
        for(int [] dir : directions){
            int dx = dir[0];
            int dy = dir[1];
            Position diagonal = new Position(getPosition().getX()+dx,getPosition().getY()+dy);
            if(isValidMoves(diagonal)){
                availableMoves.add(diagonal);
            }
        }
        return  availableMoves;
    }

    @Override
    public List<Position> getPossibleAttacks() {
    return getPossibleMoves();
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
