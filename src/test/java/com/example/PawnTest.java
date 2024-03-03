package com.example;

import com.example.entities.Pawn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PawnTest {

    private Pawn pawnWhite;
    private Pawn pawnBlack;
    private Board board;

    @BeforeEach
    public void setUp() {
        pawnWhite = new Pawn(new Position(1, 1), PieceColor.WHITE);
        pawnBlack = new Pawn(new Position(1, 2), PieceColor.BLACK);
    }

    @Test
    public void testValidMovedWithBlockingPiece() {
        board = new Board(pawnWhite, pawnBlack);
        assertFalse(board.validMove(pawnBlack, 1, 3));
    }

    @Test
    public void testValidMovedWithOutBlockingPiece() {
        pawnBlack = new Pawn(new Position(1, 3), PieceColor.BLACK);
        board = new Board(pawnWhite, pawnBlack);
        assertTrue(board.validMove(pawnBlack, 1, 2));
    }

    @Test
    public void testValidMovedOutOfBound() {
        board = new Board(pawnWhite);
        assertFalse(board.validMove(pawnWhite, -1, 2));
    }

    @Test
    public void testValidMovedForOccupiedPosition() {
        board = new Board(pawnWhite, pawnBlack);
        assertFalse(board.validMove(pawnBlack, 1, 2));
    }

    @Test
    public void testGetPossibleMovesWhitePawn() {
        List<Position> possibleMoves = pawnWhite.getPossibleMoves();
        List<Position> expectedMoves = Arrays.asList(new Position(1, 2), new Position(1, 3));
        assertEquals(expectedMoves, possibleMoves);
    }

    @Test
    public void testGetPossibleMovesBlackPawn() {
        List<Position> possibleMoves = pawnBlack.getPossibleMoves();
        List<Position> expectedMoves = Arrays.asList(new Position(1, 1));
        assertEquals(expectedMoves, possibleMoves);
    }

    @Test
    public void testGetPossibleMovesWhitePawnAfterMove() {
        pawnWhite = new Pawn(new Position(1, 2), PieceColor.WHITE);
        List<Position> possibleMoves = pawnWhite.getPossibleMoves();
        assertTrue(possibleMoves.contains(new Position(1, 3)));
    }

    @Test
    public void testGetPossibleMovesBlackPawnAfterMove() {
        pawnBlack = new Pawn(new Position(1, 5), PieceColor.BLACK);
        List<Position> possibleMoves = pawnBlack.getPossibleMoves();
        assertTrue(possibleMoves.contains(new Position(1, 4)));
    }

    @Test
    public void testGetPossibleAttacksWhitePawn() {
        Pawn whitePawn = new Pawn(new Position(3, 3), PieceColor.WHITE);
        List<Position> possibleAttacks = whitePawn.getPossibleAttacks();
        List<Position> expectedAttacks = Arrays.asList(new Position(4, 4), new Position(2, 4));
        assertEquals(expectedAttacks, possibleAttacks);
    }

    @Test
    public void testGetPossibleAttacksBlackPawn() {
        Pawn blackPawn = new Pawn(new Position(2, 4), PieceColor.BLACK);
        List<Position> possibleAttacks = blackPawn.getPossibleAttacks();
        List<Position> expectedAttacks = Arrays.asList(new Position(3, 3), new Position(1, 3));
        assertEquals(expectedAttacks, possibleAttacks);
    }
}
