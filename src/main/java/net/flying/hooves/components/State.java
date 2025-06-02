package net.flying.hooves.components;

import com.vaadin.flow.internal.JsonUtils;
import com.vaadin.signals.NumberSignal;
import com.vaadin.signals.ValueSignal;
import elemental.json.JsonObject;
import net.flying.hooves.model.Cell;
import net.flying.hooves.model.CellMatrix;
import net.flying.hooves.model.Difficulty;

public class State {
    private final ValueSignal<Cell> selectedCell = new ValueSignal<>(Cell.class);
    private final ValueSignal<Difficulty> difficultySignal = new ValueSignal<>(Difficulty.class);
    private final ValueSignal<CellMatrix> cellMatrix = new ValueSignal<>(CellMatrix.class);
    private final ValueSignal<Boolean> completed = new ValueSignal<>(false);
    private final ValueSignal<Boolean> failed = new ValueSignal<>(false);
    private final NumberSignal errorCountSignal = new NumberSignal(0);

    public State() {
        difficultySignal.value(Difficulty.MEDIUM);
    }

    public void setDifficulty(Difficulty difficulty) {
        difficultySignal.value(difficulty);
    }

    public Difficulty getDifficulty() {
        return difficultySignal.value();
    }

    public Cell getCell(int row, int column) {
        CellMatrix matrix = cellMatrix.value();
        if (matrix == null) {
            return null;
        }
        return matrix.getMatrix()[row][column];
    }

    public void setSelectedCell(Cell cell) {
        selectedCell.value(cell);
    }

    public ValueSignal<Cell> getSelectedCell() {
        return selectedCell;
    }

    public void setCellMatrix(CellMatrix matrix) {
        cellMatrix.value(matrix);
        this.completed.value(false);
        this.failed.value(false);
    }

    public CellMatrix getCellMatrix() {
        return cellMatrix.value();
    }

    public void updateCell(Cell cell) {
        CellMatrix real = getCellMatrix();
        JsonObject jsonObject = JsonUtils.beanToJson(real);
        CellMatrix copy = JsonUtils.readValue(jsonObject, CellMatrix.class);
        copy.getMatrix()[cell.getRow()][cell.getColumn()] = cell;
        setCellMatrix(copy);
        if (cell.getUserValue() != cell.getValue()) {
            errorCountSignal.incrementBy(1);
        }
        if(errorCountSignal.valueAsInt() >= 3){
            failed.value(true);
        }
        this.completed.value(isGameCompleted(getCellMatrix()));
    }

    private boolean isGameCompleted(CellMatrix matrix) {
        for (Cell[] rows : matrix.getMatrix()) {
            for (Cell cell : rows) {
                if (cell.getUserValue() != cell.getValue()) {
                    return false;
                }
            }
        }
        return true;
    }
    public ValueSignal<Boolean> getFailed(){
        return failed;
    }
    public ValueSignal<Boolean> getCompleted(){
        return completed;
    }
    public NumberSignal getErrorCountSignal() {
        return errorCountSignal;
    }
}
