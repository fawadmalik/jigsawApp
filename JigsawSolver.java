import java.util.List;
import java.util.ArrayList;

public class JigsawSolver{
    public static void main(String[] args){
        // start with 9 pieces
        Piece [] puzzle = new Piece[]{
                new Piece("topLeft", 0, 0, 10, -120),
                new Piece("top", -10, 0, 20, 100),
                new Piece("topRight", -20, 0, 0, 30),
                new Piece("right", -90, -30, 0, 40),
                new Piece("bottomRight", -60, -40, 0, 50),
                new Piece("bottom", -70, 80, 60, 0),
                new Piece("bottomLeft", 0, 130, 70, 0),
                new Piece("left", 0, 120, -110, -130),
                new Piece("middle", 110, -100, 90, -80)
        };

        solve(puzzle);
    }

    private static void solve(Piece[] puzzle){
        int endIndex = 0;
        int tries = 100;
        int puzzleSize = puzzle.length;
        Piece[] solved = new Piece[puzzleSize];
        int solvedIndex = 0;

        for(int i = 0; i < puzzleSize; i++){
            Piece aPiece = puzzle[i];
            if(aPiece.isTopLeft()){
                solved[solvedIndex] = aPiece;
                puzzle[i] = null;
            }
        }
        while(tries-- > 0 && solvedIndex < puzzleSize){
            Piece referencePiece = solved[solvedIndex - endIndex];
            for(int i = 0; i < puzzleSize; i++){
                Piece aPiece = puzzle[i];
                if(aPiece == null){
                    continue;
                }
                if(referencePiece.fits(aPiece)){
                    solvedIndex++;
                    solved[solvedIndex] = aPiece;
                    puzzle[i] = null;
                    break;
                }
            }
            Piece solvedPiece = solved[solvedIndex];
            if(solvedPiece.isRight() || solvedPiece.isTopRight() || solvedPiece.isBottomRight()){
                if(endIndex == 0){
                    endIndex = solvedIndex;
                }
            }
        }
    }
}

class Piece{
    String name;
    int left;
    int top;
    int right;
    int bottom;

    public Piece(String name, int left, int top, int right, int bottom){
        this.name = name;
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public String getName(){
        return name;
    }

    public boolean fits(Piece piece){
        int pieceLeft = piece.left;
        if(right == pieceLeft){
            return true;
        }
        int pieceTop = piece.top;
        if(bottom == pieceTop){
            return true;
        }
        return false;
    }

    public boolean isTopLeft(){
        return left == 0 && top == 0;
    }

    public boolean isTopRight(){
        return top == 0 && right == 0;
    }

    public boolean isBottomLeft(){
        return left == 0 && bottom == 0;
    }

    public boolean isBottomRight(){
        return bottom == 0 && right == 0;
    }

    public boolean isLeft(){
        return left == 0 && top != 0 && right != 0 && bottom != 0;
    }

    public boolean isTop(){
        return top == 0 && left != 0 && right != 0 && bottom != 0;
    }

    public boolean isRight(){
        return right == 0 && top != 0 && left != 0 && bottom != 0;
    }

    public boolean isBottom(){
        return bottom == 0 && top != 0 && right != 0 && left != 0;
    }
}