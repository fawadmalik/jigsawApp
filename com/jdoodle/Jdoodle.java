package com.jdoodle;

import java.util.Random;

public class Jdoodle {
    public static void main(String[] args) {
        Piece [] puzzle = new Piece[] {
                new Piece("topleft", 0, 0, 20, 30),
                new Piece("top", -20, 0, 40, 50),
                new Piece("top1", -40, 0, 60, 70),
                new Piece("top2", -60, 0, 80, 90),
                new Piece("topright", -80, 0, 0, 100),
                new Piece("right", 110, -100, 0, 120),
                new Piece("right1", 130, -120, 0, 140),
                new Piece("bottomright", 150, -140, 0, 0),
                new Piece("bottom2", 160, 170, -150, 0),
                new Piece("bottom1", 180, 190, -160, 0),
                new Piece("bottom", 200, 210, -180, 0),
                new Piece("bottomleft", 0, 220, -200, 0),
                new Piece("left1", 0, 230, 240, -220),
                new Piece("left", 0, -30, 250, -230),
                new Piece("middle", -250, -50, 260, 270),
                new Piece("middle1", -260, -70, 280, 290),
                new Piece("middle2", -280, -90, -110, 300),
                new Piece("middle5", 310, -300, -130, -170),
                new Piece("middle4", 320, -290, -310, -190),
                new Piece("middle3", -240, -270, -320, -210)
        };
        Piece [] scrambledPuzzle = scramble(puzzle);
        solve(scrambledPuzzle);
    }

    private static Piece[] scramble(Piece[] puzzle) {
        int count = puzzle.length;
        Piece [] scrambled = new Piece[count];
        for(int i = 0; i < count; i++){
            Random random = new Random();
            boolean foundPlace = false;
            while(foundPlace == false){
                int aRandom = random.nextInt(count);
                if(scrambled[aRandom] == null){
                    scrambled[aRandom] = puzzle[i];
                    foundPlace = true;
                }else {
                    System.out.println(aRandom + " is already taken. keep trying");
                }
            }
        }
        return scrambled;
    }

    private static void solve(Piece[] puzzle){
        System.out.println("Before solving");
        printPuzzle(null, puzzle);

        int puzzleSize = puzzle.length;

        Piece[] starterRow = new Piece[puzzleSize];
        findStarterPiece(null, starterRow, puzzle);
        solveRow(starterRow, puzzle);

        int piecesPerRow = 0;
        for(int i = 0; i < starterRow.length; i++){
            if(starterRow[i] != null) {
                piecesPerRow++;
            }
        }

        int numberOfRows = puzzleSize/piecesPerRow;
        Piece[][] puzzleRows = new Piece[numberOfRows][piecesPerRow];
        for(int i = 0; i < piecesPerRow; i++){
            puzzleRows[0][i] = starterRow[i];
        }

        for(int i = 1; i < numberOfRows; i++){
            findStarterPiece(puzzleRows[i-1][0], puzzleRows[i], puzzle);
            solveRow(puzzleRows[i], puzzle);
        }

        printPuzzle(puzzleRows, puzzle);
        // find the right piece that fits and move it to solved array
        // set it to null in puzzle
        // check to see if it is the right most piece
        // - if yes then width = index of solved at this point

        // now use this width to collect rows for the rest of the puzzle
    }

    private static void printPuzzle(Piece[][] puzzleRows, Piece[] puzzle) {
        if(puzzleRows == null){
            for (int i = 0; i < puzzle.length; i++){
                System.out.println(puzzle[i].name);
            }
        }else {
            System.out.println();
            for (Piece[] row : puzzleRows) {
                for (Piece piece : row) {
                    System.out.printf("%11s - ", piece.name);
                }
                System.out.println();
            }
        }
    }

    private static void findStarterPiece(Piece lastStarterPiece, Piece[] starterRow, Piece[] puzzle) {
        if(lastStarterPiece == null){

        }
        for(int i = 0; i < puzzle.length; i++){
            Piece aPiece = puzzle[i];
            if(lastStarterPiece == null){
                if(aPiece.isTopLeft()){
                    starterRow[0] = aPiece;
                    puzzle[i] = null;
                    break;
                }
            }else {
                if(aPiece == null) continue;
                if(aPiece.isFitForLastStarterPieceBottom(lastStarterPiece)){
                    starterRow[0] = aPiece;
                    puzzle[i] = null;
                    break;
                }
            }
        }
    }

    private static void findNextAnchorPiece(Piece lastAnchorPiece, Piece[] nextRow, Piece[] puzzle) {
        for(int i = 0; i < puzzle.length; i++){
            Piece aPiece = puzzle[i];
            if(aPiece == null) continue;
            if(aPiece.isFitForLastStarterPieceBottom(lastAnchorPiece)){
                nextRow[0] = aPiece;
                puzzle[i] = null;
                break;
            }
        }
    }

    private static void solveRow(Piece[] row, Piece[] puzzle) {
        int puzzleSize = puzzle.length;
        int index = 0;
        boolean rowIsSolved = false;
        int tries = 0;
        while(rowIsSolved == false && tries++ < puzzleSize){
            Piece referencePiece = row[index];

            for(int i = 0; i < puzzleSize; i++){
                Piece aPiece = puzzle[i];
                if(aPiece == null) continue;
                if(aPiece.isARightFitFor(referencePiece)){
                    index++;
                    row[index] = aPiece;
                    puzzle[i] = null;
                    break;
                }
            }
            if(row[index].isRightEdge()){
                break;
            }
        }
    }
}
class Piece {
    String name;
    int left;
    int top;
    int right;
    int bottom;
    public Piece(String newName, int newLeft, int newTop, int newRight, int newBottom){
        name = newName;
        left = newLeft;
        top = newTop;
        right = newRight;
        bottom = newBottom;
    }
    public boolean isTopLeft(){
        return left == 0 && top == 0;
    }

    public boolean isARightFitFor(Piece referencePiece) {
        if(referencePiece.right + left == 0){
            return true;
        }
        return false;
    }

    public boolean isRightEdge() {
        // is right edge if it is topright, justright or bottomright
        // topright
        return right == 0;
    }

    public boolean isFitForLastStarterPieceBottom(Piece referencePiece) {
        if(referencePiece.bottom + top == 0){ return true; }
        return false;
    }
}