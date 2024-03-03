package com.example;

import com.example.entities.*;
import com.example.interfaces.ChessPiece;

import java.util.List;

public class Board {
    private static final int BOARD_SIZE = 8;

    private ChessPiece[][] board;

    public Board(ChessPiece... pieces) {
        this.board = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
        for (ChessPiece piece : pieces) {
            Position position = piece.getPosition();
            board[position.getX()][position.getY()] = piece;
        }
    }

    public boolean validMove(ChessPiece piece, int newX, int newY) {
        Position currentPosition = piece.getPosition();
        if (newX < 0 || newX >= BOARD_SIZE || newY < 0 || newY >= BOARD_SIZE) {
            return false;
        }
        ChessPiece pieceAtNewPosition = getPieceAt(newX, newY);
        if (pieceAtNewPosition != null && pieceAtNewPosition.isWhite() == piece.isWhite() && pieceAtNewPosition.isBlack() == piece.isBlack()) {
            return false;
        }
        if (!piece.isMoveValid(new Position(newX, newY))) {
            return false; // The move is not valid according to the chess piece's rules
        }
        if (piece instanceof Pawn && Math.abs(newX - currentPosition.getX()) == 0) {
            int direction = ((Pawn) piece).isWhite() ? 1 : -1;
            ChessPiece pieceInFront = getPieceAt(newX, currentPosition.getY() + direction);
            if (pieceInFront != null) {
                return false;
            }
        }
        if (piece instanceof Rook) {
            // Check for friendly pieces adjacent to and in line with the rook
            if (newX == currentPosition.getX()) { // Moving vertically
                int direction = Integer.compare(newY, currentPosition.getY());
                for (int y = currentPosition.getY() + direction; y != newY; y += direction) {
                    ChessPiece pieceInLine = getPieceAt(newX, y);
                    if (pieceInLine != null && pieceInLine.isWhite() == piece.isWhite()) {
                        return false; // There is a friendly piece in the line of movement
                    }
                }
            } else if (newY == currentPosition.getY()) { // Moving horizontally
                int direction = Integer.compare(newX, currentPosition.getX());
                for (int x = currentPosition.getX() + direction; x != newX; x += direction) {
                    ChessPiece pieceInLine = getPieceAt(x, newY);
                    if (pieceInLine != null && pieceInLine.isWhite() == piece.isWhite()) {
                        return false; // There is a friendly piece in the line of movement
                    }
                }
            } else {
                // Not moving vertically or horizontally, invalid move for a Rook
                return false;
            }
        }
        if(piece instanceof Knight){
            List<Position> posibleMoves = piece.getPossibleMoves();
            for(Position moves : posibleMoves){
                if(getPieceAt(moves.getX(),moves.getY())!=null && getPieceAt(moves.getX(),moves.getY()).isWhite() == piece.isWhite()){
                    return  false;
                }
            }
        }
        if(piece instanceof Bishop){
            int dx = Integer.compare(newX,currentPosition.getY());
            int dy = Integer.compare(newX, currentPosition.getY());
            int steps = Math.abs(newX-currentPosition.getX());

            for(int i=1;i<steps;i++){
                int x = currentPosition.getX() + (dx*i);
                int y = currentPosition.getY() + (dy*i);
                ChessPiece pieceInline = getPieceAt(x,y);
                if(pieceInline != null && pieceInline.isWhite() == piece.isWhite()){
                    return  false;
                }
            }
        }
        if(piece instanceof Queen){
            if (newX == currentPosition.getX() || newY == currentPosition.getY()) { // Moving vertically or horizontally
                int directionX = Integer.compare(newX, currentPosition.getX());
                int directionY = Integer.compare(newY, currentPosition.getY());
                if (directionX != 0) { // Moving horizontally
                    for (int x = currentPosition.getX() + directionX; x != newX; x += directionX) {
                        ChessPiece pieceInLine = getPieceAt(x, newY);
                        if (pieceInLine != null && pieceInLine.isWhite() == piece.isWhite()) {
                            return false; // There is a friendly piece in the line of movement
                        }
                    }
                } else { // Moving vertically
                    for (int y = currentPosition.getY() + directionY; y != newY; y += directionY) {
                        ChessPiece pieceInLine = getPieceAt(newX, y);
                        if (pieceInLine != null && pieceInLine.isWhite() == piece.isWhite()) {
                            return false; // There is a friendly piece in the line of movement
                        }
                    }
                }
            } else if (Math.abs(newX - currentPosition.getX()) == Math.abs(newY - currentPosition.getY())) { // Moving diagonally
                int dx = Integer.compare(newX, currentPosition.getX());
                int dy = Integer.compare(newY, currentPosition.getY());
                int steps = Math.abs(newX - currentPosition.getX());

                for (int i = 1; i < steps; i++) {
                    int x = currentPosition.getX() + (dx * i);
                    int y = currentPosition.getY() + (dy * i);
                    ChessPiece pieceInline = getPieceAt(x, y);
                    if (pieceInline != null && pieceInline.isWhite() == piece.isWhite()) {
                        return false;
                    }
                }
            } else { // Invalid move for a Rook
                return false;
            }

        }
        return true;
    }

    public ChessPiece getPieceAt(int x, int y) {
        if (x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE) {
            return null; // Out of bounds
        }
        return board[x][y];
    }
}
