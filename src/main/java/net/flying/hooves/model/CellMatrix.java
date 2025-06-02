package net.flying.hooves.model;

import com.vaadin.flow.component.Unit;
import net.flying.hooves.components.ui.SudokuCell;
import net.flying.hooves.service.SudokuGenerator;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class CellMatrix {
    private static int INDEX = 1;
    private Cell[][] matrix = new Cell[9][9];
    public CellMatrix() {
        INDEX ++;
    }

    public static int getIndex(){
        return INDEX;
    }

    public void generate(Difficulty difficulty){
        SudokuGenerator sudokuGenerator = new SudokuGenerator();
        int[][] generate = sudokuGenerator.generate();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Cell cell = new Cell(i, j);
                cell.setValue(generate[i][j]);
                cell.setValueVisible(true);
                matrix[i][j] = cell;
                //<theme-editor-local-classname>
            }
        }
        List<Pair<Integer, Integer>> pairs = SudokuGenerator.makePlayable(generate, difficulty.getValue());
        for (Pair<Integer, Integer> pair : pairs) {
            Integer row = pair.getLeft();
            Integer col = pair.getRight();
            matrix[row][col].setValueVisible(false);
        }
    }
    public Cell[][] getMatrix() {
        return matrix;
    }
}
