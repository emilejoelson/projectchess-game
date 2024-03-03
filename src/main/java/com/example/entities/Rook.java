package com.example.entities;

import com.example.PieceColor;
import com.example.Position;
import com.example.interfaces.ChessPiece;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rook implements ChessPiece {

    private Position position;
    private PieceColor color;
    private boolean moved;

    public Rook(Position position,PieceColor color){
        this.position = position;
        this.color = color;
        this.moved = false;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public boolean isWhite() {
        return color == PieceColor.WHITE;
    }
    @Override
    public boolean isBlack() {
        return color == PieceColor.BLACK;
    }

    @Override
    public List<Position> getPossibleMoves() {
        List<Position> availableMovesSet = new ArrayList<>();
        int x = getPosition().getX();
        int y = getPosition().getY();

        // Add vertical moves except the current position
        for (int i = 0; i < 8; i++) {
            if (i != y) {
                availableMovesSet.add(new Position(x, i));
            }
        }
        // Add horizontal moves except the current position
        for (int i = 0; i < 8; i++) {
            if (i != x) {
                availableMovesSet.add(new Position(i, y));
            }
        }
        return availableMovesSet;
    }


    public  List<Position> getPossibleAttacks(){
      return getPossibleMoves();
    }

    public boolean isMoveValid(Position newPosition){
        List<Position> availableMoves = getPossibleMoves();
        return availableMoves.contains(newPosition);
    }
}
