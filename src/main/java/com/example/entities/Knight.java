package com.example.entities;

import com.example.PieceColor;
import com.example.Position;
import com.example.interfaces.ChessPiece;

import java.util.ArrayList;
import java.util.List;

public class Knight implements ChessPiece
{
    private  Position position;
    private PieceColor color;
    private  boolean moved;
    public  Knight(Position position,PieceColor color){
        this.position = position;
        this.color = color;
        moved = false;
    }
    @Override
    public Position getPosition() {
        return  position;
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
        int currentX = getPosition().getX();
        int currentY = getPosition().getY();

        int[] dx = {1, -1, -2, 2, 1, -1, -2, 2};
        int[] dy = {2, 2, 1, 1, -2, -2, -1, -1};

        for(int i= 0;i<8;i++){
            int x = currentX +dx[i];
            int y = currentY + dy[i];
            Position newPosition = new Position(x,y);
            if(isValidPosition(newPosition)){
                availableMoves.add(newPosition);
            }
        }
        return  availableMoves;
    }

    @Override
    public List<Position> getPossibleAttacks() {
     return  getPossibleMoves();
    }

    @Override
    public boolean isMoveValid(Position newPosition){
        List<Position> availableMoves = getPossibleMoves();
        return  availableMoves.contains(newPosition);
    }

    public  boolean isValidPosition(Position position){
        return position.getX() >= 0 && position.getX() < 8
                && position.getY() >= 0 && position.getY() < 8;
    }
}
