package com.example.interfaces;

import com.example.Position;

import java.util.List;

public interface ChessPiece {
    public Position getPosition();
    public boolean isBlack();
    public boolean isWhite();
    public List<Position> getPossibleMoves();
    public List<Position> getPossibleAttacks();
    public boolean isMoveValid(Position position);

}
