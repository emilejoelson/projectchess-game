package com.example;

import com.example.entities.Pawn;
import com.example.entities.Queen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QueenTest {

    private Queen queenWhite;
    private Pawn pawnWhite;
    private Board board;

    @BeforeEach
    public void setUp() {
        queenWhite = new Queen(new Position(1, 2), PieceColor.WHITE);
    }

    @Test
    public void testValidMovedWithBlockingPieceVertical() {
        pawnWhite = new Pawn(new Position(1, 3), PieceColor.WHITE);
        board = new Board(queenWhite, pawnWhite);
        assertFalse(board.validMove(queenWhite, 1, 4));
    }

    @Test
    public void testValidMovedWithBlockingPieceDiagonal() {
        pawnWhite = new Pawn(new Position(3, 4), PieceColor.WHITE);
        board = new Board(queenWhite, pawnWhite);
        assertFalse(board.validMove(queenWhite, 5, 6));
    }

    @Test
    public void testValidMovedWithOutBlockingPieceHorizontal() {
        Queen queenWhite1 = new Queen(new Position(1, 2), PieceColor.WHITE);
        Queen queenWhite2 = new Queen(new Position(4, 2), PieceColor.WHITE);
        board = new Board(queenWhite2, queenWhite1);
        assertTrue(board.validMove(queenWhite1, 2, 2));
    }

    @Test
    public void testValidMovedOutOfBound() {
        Queen queenBlack = new Queen(new Position(1, 1), PieceColor.BLACK);
        board = new Board(queenBlack);
        assertFalse(board.validMove(queenBlack, 8, 2));
    }

    @Test
    public void testValidMovedForOccupiedPosition() {
        queenWhite = new Queen(new Position(3, 3), PieceColor.WHITE);
        pawnWhite = new Pawn(new Position(1, 1), PieceColor.WHITE);
        board = new Board(queenWhite, pawnWhite);
        assertFalse(board.validMove(queenWhite, 1, 1));
    }

    @Test
    public void testGetPossibleMovesBlackQueen() {
        queenWhite = new Queen(new Position(1, 3), PieceColor.BLACK);
        List<Position> possibleMoves = queenWhite.getPossibleMoves();
        List<Position> expectedMoves = Arrays.asList(
                new Position(0, 3), new Position(1, 0), new Position(1, 1), new Position(2, 3),
                new Position(1, 2), new Position(3, 3), new Position(4, 3), new Position(1, 4),
                new Position(5, 3), new Position(1, 5), new Position(6, 3), new Position(1, 6),
                new Position(7, 3), new Position(1, 7), new Position(2, 4), new Position(3, 5),
                new Position(4, 6), new Position(5, 7), new Position(0, 2), new Position(2, 2),
                new Position(3, 1), new Position(4, 0), new Position(0, 4)
        );

        assertEquals(expectedMoves, possibleMoves);
    }

    @Test
    public void testGetPossibleAttacksQueen() {
        Queen whiteQueen = new Queen(new Position(3, 3), PieceColor.WHITE);
        List<Position> possibleAttacks = whiteQueen.getPossibleAttacks();
        List<Position> expectedAttacks = Arrays.asList(
                new Position(0, 3), new Position(3, 0), new Position(1, 3), new Position(3, 1),
                new Position(2, 3), new Position(3, 2), new Position(4, 3), new Position(3, 4),
                new Position(5, 3), new Position(3, 5), new Position(6, 3), new Position(3, 6),
                new Position(7, 3), new Position(3, 7), new Position(4, 4), new Position(5, 5),
                new Position(6, 6), new Position(7, 7), new Position(2, 2), new Position(1, 1),
                new Position(0, 0), new Position(4, 2), new Position(5, 1), new Position(6, 0),
                new Position(2, 4), new Position(1, 5), new Position(0, 6));
        assertEquals(expectedAttacks, possibleAttacks);
    }

    @Test
    public void testGetPossibleAttacksBlackQueen() {
        Queen blackQueen = new Queen(new Position(2, 5), PieceColor.BLACK);
        List<Position> possibleAttacks = blackQueen.getPossibleAttacks();
        List<Position> expectedAttacks = Arrays.asList(
                new Position(0, 5), new Position(2, 0), new Position(1, 5), new Position(2, 1),
                new Position(2, 2), new Position(3, 5), new Position(2, 3), new Position(4, 5),
                new Position(2, 4), new Position(5, 5), new Position(6, 5), new Position(2, 6),
                new Position(7, 5), new Position(2, 7), new Position(3, 6), new Position(4, 7),
                new Position(1, 4), new Position(0, 3), new Position(3, 4), new Position(4, 3),
                new Position(5, 2), new Position(6, 1), new Position(7, 0), new Position(1, 6),
                new Position(0, 7)
        );
        assertEquals(expectedAttacks, possibleAttacks);
    }
}
