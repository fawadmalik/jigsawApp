package com.ziadproj.app;

class Piece {
    String name;
    int left;
    int top;
    int right;
    int bottom;

    public Piece(String name, int left, int top, int right, int bottom) {
        this.name = name;
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public boolean isTopLeft() {
        if (this.top == 0 && this.left == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isRightFittingTo(Piece starterPiece) {
        if (this.left + starterPiece.right == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isRightMost(Piece[] puzzle) {
        if (this.right == 0) {
            return true;
        }
        else {
            return false;
        }

    }

    public boolean isBottomFittingTo(Piece leftMostPiece) {
        if (this.top + leftMostPiece.bottom == 0) {
            return true;
        }
        else {
            return false;
        }

    }
}
