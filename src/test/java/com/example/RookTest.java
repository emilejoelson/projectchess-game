package com.example;

import com.example.entities.Pawn;
import com.example.entities.Rook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RookTest {

    private Rook rookWhite;
    private Pawn pawnWhite;
    private Board board;

    @BeforeEach
    public void setUp() {
        rookWhite = new Rook(new Position(1, 1), PieceColor.WHITE);
    }

    @Test
    public void testValidMovedWithBlockingPiece() {
        pawnWhite = new Pawn(new Position(1, 3), PieceColor.WHITE);
        board = new Board(rookWhite, pawnWhite);
        assertFalse(board.validMove(rookWhite, 1, 4));
    }

    @Test
    public void testValidMovedWithOutBlockingPiece() {
        Rook rookWhite1 = new Rook(new Position(1, 1), PieceColor.WHITE);
        Rook rookWhite2 = new Rook(new Position(4, 1), PieceColor.WHITE);
        board = new Board(rookWhite1, rookWhite2);
        assertTrue(board.validMove(rookWhite1, 3, 1));
    }

    @Test
    public void testValidMovedOutOfBound() {
        Rook rookBlack = new Rook(new Position(1, 1), PieceColor.BLACK);
        board = new Board(rookBlack);
        assertFalse(board.validMove(rookBlack, 8, 2));
    }

    @Test
    public void testValidMovedForOccupiedPosition() {
        Rook rookWhite = new Rook(new Position(1, 1), PieceColor.WHITE);
        pawnWhite = new Pawn(new Position(1, 2), PieceColor.WHITE);
        board = new Board(rookWhite, pawnWhite);
        assertFalse(board.validMove(rookWhite, 1, 2));
    }

    @Test
    public void testGetPossibleMovesBlackRook() {
        rookWhite = new Rook(new Position(3, 3), PieceColor.BLACK);
        List<Position> possibleMoves = rookWhite.getPossibleMoves();
        List<Position> expectedMoves = Arrays.asList(
                new Position(3, 0), new Position(3, 1), new Position(3, 2), new Position(3, 4),
                new Position(3, 5), new Position(3, 6), new Position(3, 7), new Position(0, 3),
                new Position(1, 3), new Position(2, 3), new Position(4, 3), new Position(5, 3),
                new Position(6, 3), new Position(7, 3));
        assertEquals(expectedMoves, possibleMoves);
    }

    @Test
    public void testGetPossibleAttacksRook() {
        rookWhite = new Rook(new Position(3, 3), PieceColor.WHITE);
        List<Position> possibleAttacks = rookWhite.getPossibleAttacks();
        List<Position> expectedAttacks = Arrays.asList(
                new Position(3, 0), new Position(3, 1), new Position(3, 2), new Position(3, 4),
                new Position(3, 5), new Position(3, 6), new Position(3, 7), new Position(0, 3),
                new Position(1, 3), new Position(2, 3), new Position(4, 3), new Position(5, 3),
                new Position(6, 3), new Position(7, 3));
        assertEquals(expectedAttacks, possibleAttacks);
    }

    @Test
    public void testGetPossibleAttacksBlackRook() {
        rookWhite = new Rook(new Position(2, 5), PieceColor.BLACK);
        List<Position> possibleAttacks = rookWhite.getPossibleAttacks();
        List<Position> expectedAttacks = Arrays.asList(
                new Position(2, 0), new Position(2, 1), new Position(2, 2), new Position(2, 3),
                new Position(2, 4), new Position(2, 6), new Position(2, 7), new Position(0, 5),
                new Position(1, 5), new Position(3, 5), new Position(4, 5), new Position(5, 5),
                new Position(6, 5), new Position(7, 5)
        );
        assertEquals(expectedAttacks, possibleAttacks);
    }
}
