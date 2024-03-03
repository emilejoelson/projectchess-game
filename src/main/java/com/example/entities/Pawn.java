package com.example.entities;

import com.example.PieceColor;
import com.example.Position;
import com.example.interfaces.ChessPiece;

import java.util.ArrayList;
import java.util.List;

public class Pawn implements ChessPiece {
    private Position position;
    private PieceColor color;
    private boolean moved; // Track if the pawn has moved before

    public Pawn(Position position, PieceColor pieceColor) {
        this.position = position;
        this.color = pieceColor;
        this.moved = false; // Initialize moved to false since pawn starts at its initial position
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
        List<Position> possibleMoves = new ArrayList<>();

        int direction = (color == PieceColor.WHITE) ? 1 : -1;
        int initialRow = (color == PieceColor.WHITE) ? 1 : 6;

        if (position.getY() == initialRow) {
            possibleMoves.add(new Position(position.getX(), position.getY() + direction));
            possibleMoves.add(new Position(position.getX(), position.getY() + 2 * direction));
        } else {
            possibleMoves.add(new Position(position.getX(), position.getY() + direction));
        }

        return possibleMoves;
    }


    @Override
    public List<Position> getPossibleAttacks() {
        List<Position> possibleAttacks = new ArrayList<>();

        int direction = (color == PieceColor.WHITE) ? 1 : -1;

        if (position.getX() + 1 < 8 && position.getY() + direction >= 0 && position.getY() + direction < 8) {
            possibleAttacks.add(new Position(position.getX() + 1, position.getY() + direction));
        }
        if (position.getX() - 1 >= 0 && position.getY() + direction >= 0 && position.getY() + direction < 8) {
            possibleAttacks.add(new Position(position.getX() - 1, position.getY() + direction));
        }
        return possibleAttacks;
    }


    @Override
    public boolean isMoveValid(Position newPosition) {
        // Implement pawn-specific movement rules
        List<Position> possibleMoves = getPossibleMoves();
        return possibleMoves.contains(newPosition);
    }

}
