package com.example;

import com.example.entities.King;
import com.example.entities.Pawn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KingTest {

    private King kingWhite;
    private Pawn pawnWhite;
    private Board board;

    @BeforeEach
    public void setUp() {
        kingWhite = new King(new Position(1, 2), PieceColor.WHITE);
    }

    @Test
    public void testValidMovedWithBlockingPieceVertical() {
        pawnWhite = new Pawn(new Position(1, 3), PieceColor.WHITE);
        board = new Board(kingWhite, pawnWhite);
        assertFalse(board.validMove(kingWhite, 1, 3));
    }

    @Test
    public void testValidMovedWithBlockingPieceDiagonal() {
        pawnWhite = new Pawn(new Position(2, 3), PieceColor.WHITE);
        board = new Board(kingWhite, pawnWhite);
        assertFalse(board.validMove(kingWhite, 2, 3));
    }

    @Test
    public void testValidMovedWithOutBlockingPieceHorizontal() {
        King kingWhite1 = new King(new Position(1, 2), PieceColor.WHITE);
        board = new Board(kingWhite1);
        assertTrue(board.validMove(kingWhite1, 0, 2));
    }

    @Test
    public void testValidMovedOutOfBound() {
        King kingBlack = new King(new Position(1, 1), PieceColor.BLACK);
        board = new Board(kingBlack);
        assertFalse(board.validMove(kingBlack, 8, 2));
    }

    @Test
    public void testValidMovedForOccupiedPosition() {
        pawnWhite = new Pawn(new Position(4, 1), PieceColor.WHITE);
        board = new Board(kingWhite, pawnWhite);
        assertFalse(board.validMove(kingWhite, 4, 1));
    }

    @Test
    public void testGetPossibleMovesBlackKing() {
        kingWhite = new King(new Position(1, 3), PieceColor.BLACK);
        List<Position> possibleMoves = kingWhite.getPossibleMoves();
        List<Position> expectedMoves = Arrays.asList(
                new Position(0, 3),
                new Position(1, 2),
                new Position(2, 3),
                new Position(2, 4),
                new Position(0, 2),
                new Position(2, 2),
                new Position(0, 4)
        );

        assertEquals(expectedMoves, possibleMoves);
    }

    @Test
    public void testGetPossibleAttacksKing() {
        kingWhite = new King(new Position(3, 3), PieceColor.WHITE);
        List<Position> possibleAttacks = kingWhite.getPossibleAttacks();
        List<Position> expectedAttacks = Arrays.asList(
                new Position(2, 3), new Position(3, 2),
                new Position(4, 3), new Position(3, 4),
                new Position(4, 4), new Position(2, 2),
                new Position(4, 2), new Position(2, 4)
        );
        assertEquals(expectedAttacks, possibleAttacks);
    }

    @Test
    public void testGetPossibleAttacksBlackKing() {
        King blackKing = new King(new Position(2, 5), PieceColor.BLACK);
        List<Position> possibleAttacks = blackKing.getPossibleAttacks();
        List<Position> expectedAttacks = Arrays.asList(
                new Position(1, 5), new Position(2, 4),
                new Position(3, 5), new Position(2, 6),
                new Position(3, 6), new Position(1, 4),
                new Position(3, 4), new Position(1, 6)
        );
        assertEquals(expectedAttacks, possibleAttacks);
    }
}
