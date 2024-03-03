package com.example;

import com.example.entities.Bishop;
import com.example.entities.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BishopTest {

    private Bishop bishopWhite;
    private Pawn pawnWhite;
    private Board board;

    @BeforeEach
    public void setUp() {
        bishopWhite = new Bishop(new Position(2, 2), PieceColor.WHITE);
    }

    @Test
    public void testValidMovedWithBlockingPiece() {
        pawnWhite = new Pawn(new Position(4, 4), PieceColor.WHITE);
        board = new Board(bishopWhite, pawnWhite);
        assertFalse(board.validMove(bishopWhite, 6, 6));
    }

    @Test
    public void testValidMovedWithOutBlockingPiece() {
        Bishop bishopWhite1 = new Bishop(new Position(1, 1), PieceColor.WHITE);
        Bishop bishopWhite2 = new Bishop(new Position(4, 4), PieceColor.WHITE);
        board = new Board(bishopWhite2, bishopWhite1);
        assertTrue(board.validMove(bishopWhite1, 2, 2));
    }

    @Test
    public void testValidMovedOutOfBound() {
        Bishop bishopWhite = new Bishop(new Position(1, 1), PieceColor.BLACK);
        board = new Board(bishopWhite);
        assertFalse(board.validMove(bishopWhite, 8, 2));
    }

    @Test
    public void testValidMovedForOccupiedPosition() {
        bishopWhite = new Bishop(new Position(1, 1), PieceColor.WHITE);
        pawnWhite = new Pawn(new Position(3, 3), PieceColor.WHITE);
        board = new Board(bishopWhite, pawnWhite);
        assertFalse(board.validMove(bishopWhite, 3, 3));
    }

    @Test
    public void testGetPossibleMovesBlackBishop() {
        bishopWhite = new Bishop(new Position(3, 3), PieceColor.BLACK);
        List<Position> possibleMoves = bishopWhite.getPossibleMoves();
        List<Position> expectedMoves = Arrays.asList(
                new Position(4, 4), new Position(5, 5), new Position(6, 6), new Position(7, 7),
                new Position(2, 2), new Position(1, 1), new Position(0, 0), new Position(4, 2),
                new Position(5, 1), new Position(6, 0), new Position(2, 4), new Position(1, 5),
                new Position(0, 6));
        assertEquals(expectedMoves, possibleMoves);
    }

    @Test
    public void testGetPossibleAttacksBishop() {
        bishopWhite = new Bishop(new Position(3, 3), PieceColor.WHITE);
        List<Position> possibleAttacks = bishopWhite.getPossibleAttacks();
        List<Position> expectedAttacks = Arrays.asList(
                new Position(4, 4), new Position(5, 5), new Position(6, 6), new Position(7, 7),
                new Position(2, 2), new Position(1, 1), new Position(0, 0), new Position(4, 2),
                new Position(5, 1), new Position(6, 0), new Position(2, 4), new Position(1, 5),
                new Position(0, 6));
        assertEquals(expectedAttacks, possibleAttacks);
    }

    @Test
    public void testGetPossibleAttacksBlackBishop() {
        bishopWhite = new Bishop(new Position(2, 5), PieceColor.BLACK);
        List<Position> possibleAttacks = bishopWhite.getPossibleAttacks();
        List<Position> expectedAttacks = Arrays.asList(
                new Position(3, 6), new Position(4, 7), new Position(1, 4), new Position(0, 3),
                new Position(3, 4), new Position(4, 3), new Position(5, 2), new Position(6, 1),
                new Position(7, 0), new Position(1, 6), new Position(0, 7)
        );
        assertEquals(expectedAttacks, possibleAttacks);
    }
}
