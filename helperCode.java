        Piece[] solvedPuzzle = new Piece[puzzle.length];
        Piece topLeft = getTopLeftPieceNew(puzzle);
        addToSolvedPuzzle(topLeft, solvedPuzzle);
        int solvedPieces = 1;

        Piece first = topLeft;
        for(int i = 0; i < puzzle.length; i++){
            Piece nextRightPiece = findNextRightPiece(puzzle, first);
            addToSolvedPuzzle(nextRightPiece, solvedPuzzle);
            solvedPieces++;
            if(nextRightPiece.isRightMost(puzzle)){
                System.out.println("solved 1st row. Pieces so far: " + solvedPieces);
                printPuzzle(solvedPuzzle);
                break;
            }
            first = nextRightPiece;
        }
        int piecesInFirstRow = solvedPieces;

        while(solvedPieces < puzzle.length) {
            Piece nextFirst = getFirstLeftFittingBottomOf(solvedPuzzle[solvedPieces - piecesInFirstRow], puzzle);
            addToSolvedPuzzle(nextFirst, solvedPuzzle);
            solvedPieces++;

            for (int i = 0; i < puzzle.length; i++) {
                Piece nextRightPiece = findNextRightPiece(puzzle, nextFirst);
                addToSolvedPuzzle(nextRightPiece, solvedPuzzle);
                solvedPieces++;
                if (nextRightPiece.isRightMost(puzzle)) {
                    System.out.println("solved another row. Pieces so far: " + solvedPieces);
                    printPuzzle(solvedPuzzle);
                    break;
                }else {
                    nextFirst = nextRightPiece;
                }
            }
        }
