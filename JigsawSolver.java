import java.util.List;
import java.util.ArrayList;

public class JigsawSolver{
    public static void main(String[] args){
        // start with 9 pieces
        List<Piece> puzzle = new ArrayList<>();
        puzzle.add(new Piece("topLeft", 0,0,10,-120));
        puzzle.add(new Piece("top", -10,0,20,100));
        puzzle.add(new Piece("topRight", -20,0,0,30));
        puzzle.add(new Piece("right", -90,-30,0,40));
        puzzle.add(new Piece("bottomRight", -60,-40,0,50));
        puzzle.add(new Piece("bottom", -70,80,60,0));
        puzzle.add(new Piece("bottomLeft", 0,130,70,0));
        puzzle.add(new Piece("left", 0,120,-110,-130));
        puzzle.add(new Piece("middle", 110,-100,90,-80));

        solve(puzzle);
    }

    private static void solve(List<Piece> puzzle){
        int tries = 100;
        List<Piece> solved = new ArrayList<>();
        while(puzzle.size() > 0 && tries-- > 0){
            List<Piece> solvedThisTurn = trySolve(puzzle);
            solved.addAll(solvedThisTurn);
            puzzle.removeAll(solvedThisTurn);
        }
        for(int i = 0; i < solved.size(); i++){
            System.out.print(solved.get(i).getName() + " - ");
            if(i == 2 || i == 5 || i == 8){
                System.out.println("");
            }
        }

        if(puzzle.size() == 0){
            System.out.println("Solved");
        }else {
            System.out.println("Failed");
            System.out.println("Left over pieces\n" + puzzle);
        }
    }

    private static List<Piece> trySolve(List<Piece> puzzle){
        List<Piece> solved = new ArrayList<>();

        for(int i = 0; i < puzzle.size(); i++){
            Piece piece = puzzle.get(i);
            if(piece.isTopLeft()){
                if(!solved.contains(piece)){
                    solved.add(piece);
                }else{
                    continue;
                }
            }
            if(piece.isTop()){
                if(!solved.contains(piece)){
                    solved.add(piece);
                }else{
                    continue;
                }
            }
            if(piece.isTopRight()){
                if(!solved.contains(piece)){
                    solved.add(piece);
                }else{
                    continue;
                }
            }
            if(piece.isLeft()){
                if(!solved.contains(piece)){
                    solved.add(piece);
                }else{
                    continue;
                }
            }
            if(!(piece.isTopLeft() && piece.isTop() && piece.isTopRight() && piece.isRight() &&
                    piece.isBottomRight() && piece.isBottom() && piece.isBottomLeft() && piece.isLeft() )){
                if(!solved.contains(piece)){
                    solved.add(piece);
                }else{
                    continue;
                }
            }
            if(piece.isRight()){
                if(!solved.contains(piece)){
                    solved.add(piece);
                }else{
                    continue;
                }
            }
            if(piece.isBottomLeft()){
                if(!solved.contains(piece)){
                    solved.add(piece);
                }else{
                    continue;
                }
            }
            if(piece.isBottom()){
                if(!solved.contains(piece)){
                    solved.add(piece);
                }else{
                    continue;
                }
            }
            if(piece.isBottomRight()){
                if(!solved.contains(piece)){
                    solved.add(piece);
                }else{
                    continue;
                }
            }
        }
        return solved;
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
        return left == 0;
    }

    public boolean isTop(){
        return top == 0;
    }

    public boolean isRight(){
        return right == 0;
    }

    public boolean isBottom(){
        return bottom == 0;
    }
}