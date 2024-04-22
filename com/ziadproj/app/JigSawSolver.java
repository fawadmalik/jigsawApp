package com.ziadproj.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JigSawSolver {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("puzzle.txt"));
        String[] lines = new String[100];
        int totalLinesInFile = 0;
        while (scanner.hasNextLine()) {
            lines[totalLinesInFile] = scanner.nextLine();
            totalLinesInFile++;
        }
        scanner.close();
        System.out.println(totalLinesInFile);
        Piece[] puzzle = new Piece[totalLinesInFile];
        for (int i = 0; i < totalLinesInFile; i++){
            System.out.println(lines[i]);
            String line = lines[i];
            String[] lineParts = line.split(",");
            String name = lineParts[0];
            int left = Integer.parseInt(lineParts[1]);
            int top = Integer.parseInt(lineParts[2]);
            int right = Integer.parseInt(lineParts[3]);
            int bottom = Integer.parseInt(lineParts[4]);
            puzzle[i] = new Piece(name, left, top, right, bottom);
        }
        solve(puzzle);
    }
}
