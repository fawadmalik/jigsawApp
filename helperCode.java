        Piece[] solvedPuzzle = new Piece[puzzle.length];
        Piece topLeft = getTopLeftPiece(puzzle);
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


    private static Piece getTopLeftPiece(Piece[] puzzle) {
        Piece topLeftPiece = null;
        for(int i = 0; i < puzzle.length; i++){
            if(puzzle[i].isTopLeft()){
                topLeftPiece = puzzle[i];
                puzzle[i] = null;
                shiftPiecesLeft(i, puzzle);
                break;
            }
        }
        return topLeftPiece;
    }


    private static void addToSolvedPuzzle(Piece piece, Piece[] puzzle) {
        for (int i = 0; i < puzzle.length; i++){
            if(puzzle[i]==null){
                puzzle[i] = piece;
                break;
            }
        }
    }


    private static Piece findNextRightPiece(Piece[] puzzle, Piece givenPiece) {
        Piece nextRightPiece = null;
        for(int i = 0; i < puzzle.length; i++){
            if(puzzle[i].isRightFittingTo(givenPiece)){
                nextRightPiece = puzzle[i];
                puzzle[i] = null;
                shiftPiecesLeft(i, puzzle);
                break;
            }
        }
        return nextRightPiece;
    }

    private static void shiftPiecesLeft(int startAt, Piece[] puzzle) {
        for(int i = startAt; i < puzzle.length - 1; i++){
            puzzle[i] = puzzle[i + 1];
        }
        puzzle[puzzle.length - 1] = null;

    }

        Piece[] puzzle = new Piece[]{
                new Piece("right", 60, -50, 0, 70),
                new Piece("topLeft", 0, 0, 10, 20),
                new Piece("middle", 80, -70, -60, 90),
                new Piece("bottomLeft", 0, -100, 110, 0),
                new Piece("left", 0, -20, -80, 100),
                new Piece("bottomMiddle", -110, -90, 120, 0),
                new Piece("topMiddle", -10, 0, 30, 40),
                new Piece("bottomRight", -120, -70, 0, 0),
                new Piece("topRight", -30, 0, 0, 50)
        };
