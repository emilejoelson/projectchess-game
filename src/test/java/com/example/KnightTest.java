package com.example;

import com.example.entities.Knight;
import com.example.entities.Pawn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KnightTest {

    private Knight knightWhite;
    private Pawn pawnWhite;
    private Board board;

    @BeforeEach
    public void setUp() {
        knightWhite = new Knight(new Position(1, 0), PieceColor.WHITE);
    }

    @Test
    public void testValidMovedWithBlockingPiece() {
        pawnWhite = new Pawn(new Position(0, 2), PieceColor.WHITE);
        board = new Board(knightWhite, pawnWhite);
        assertFalse(board.validMove(knightWhite, 0, 2));
    }

    @Test
    public void testValidMovedWithOutBlockingPiece() {
        pawnWhite = new Pawn(new Position(1, 1), PieceColor.WHITE);
        board = new Board(knightWhite, pawnWhite);
        assertTrue(board.validMove(knightWhite, 0, 2));
    }

    @Test
    public void testValidMovedOutOfBound() {
        knightWhite = new Knight(new Position(1, 1), PieceColor.WHITE);
        board = new Board(knightWhite);
        assertFalse(board.validMove(knightWhite, -2, 2));
    }

    @Test
    public void testValidMovedForOccupiedPosition() {
        knightWhite = new Knight(new Position(1, 1), PieceColor.WHITE);
        Pawn pawnBlack = new Pawn(new Position(0, 3), PieceColor.WHITE);
        board = new Board(knightWhite, pawnBlack);
        assertFalse(board.validMove(pawnBlack, 0, 3));
    }

    @Test
    public void testGetPossibleMovesWhiteKnight() {
        knightWhite = new Knight(new Position(1, 0), PieceColor.WHITE);
        List<Position> possibleMoves = knightWhite.getPossibleMoves();
        List<Position> expectedMoves = Arrays.asList(new Position(2, 2), new Position(0, 2), new Position(3, 1));
        assertEquals(expectedMoves, possibleMoves);
    }

    @Test
    public void testGetPossibleMovesBlackKnight() {
        Knight blackKnight = new Knight(new Position(6, 7), PieceColor.BLACK);
        List<Position> possibleMoves = blackKnight.getPossibleMoves();
        List<Position> expectedMoves = Arrays.asList(new Position(7, 5), new Position(5, 5), new Position(4, 6));
        assertEquals(expectedMoves, possibleMoves);
    }

    @Test
    public void testGetPossibleMovesWhiteKnightAfterMove() {
        knightWhite = new Knight(new Position(1, 0), PieceColor.WHITE);
        List<Position> possibleMoves = knightWhite.getPossibleMoves();
        assertTrue(possibleMoves.contains(new Position(0, 2)));
    }

    @Test
    public void testGetPossibleMovesBlackKnightAfterMove() {
        Knight blackKnight = new Knight(new Position(1, 1), PieceColor.BLACK);
        List<Position> possibleMoves = blackKnight.getPossibleMoves();
        assertTrue(possibleMoves.contains(new Position(3, 0)));
    }

    @Test
    public void testGetPossibleAttacksWhiteKnight() {
        knightWhite = new Knight(new Position(3, 3), PieceColor.WHITE);
        List<Position> possibleAttacks = knightWhite.getPossibleAttacks();
        List<Position> expectedAttacks = Arrays.asList(
                new Position(4, 5), new Position(2, 5),
                new Position(1, 4), new Position(5, 4),
                new Position(4, 1), new Position(2, 1),
                new Position(1, 2), new Position(5, 2));
        assertEquals(expectedAttacks, possibleAttacks);
    }

    @Test
    public void testGetPossibleAttacksBlackKnight() {
        Knight blackKnight = new Knight(new Position(2, 4), PieceColor.BLACK);
        List<Position> possibleAttacks = blackKnight.getPossibleAttacks();
        List<Position> expectedAttacks = Arrays.asList(
                new Position(3, 6), new Position(1, 6),
                new Position(0, 5), new Position(4, 5),
                new Position(3, 2), new Position(1, 2),
                new Position(0, 3), new Position(4, 3)
        );
        assertEquals(expectedAttacks, possibleAttacks);
    }
}
