package net.flying.hooves.components.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.signals.Signal;
import net.flying.hooves.components.State;
import net.flying.hooves.model.Cell;

public class SudokuCell extends Button {

    public SudokuCell(int rowIndex, int colIndex, State state) {
        super();
        setWidth("100%");
        setHeight("100%");
        int subMatrixColIndex = colIndex / 3;
        int subMatrixRowIndex = rowIndex / 3;

        if(subMatrixColIndex + subMatrixRowIndex == 0 || (subMatrixColIndex + subMatrixRowIndex) % 2 == 0){
            getStyle().setBackgroundColor("rgb(220,220,220)");
        }
        addClickListener(e -> {
            Cell cell = state.getCell(rowIndex, colIndex);
            state.setSelectedCell(cell);
        });
        Signal.effect(() -> {
            Cell cellFromState = state.getCell(rowIndex, colIndex);
            if(cellFromState == null){
                return;
            }
            if(cellFromState.getUserValue() != 0){
                setText(String.valueOf(cellFromState.getUserValue()));
                if(cellFromState.getValue() != cellFromState.getUserValue()){
                    addClassName("error");
                }else{
                    addClassName("success");
                }
            }else if(cellFromState.isValueVisible()){
                setText(String.valueOf(cellFromState.getValue()));
            }
        });
        Signal.effect(() -> {
            Cell value = state.getSelectedCell().value();
            if(value != null && value.getRow() == rowIndex && value.getColumn() == colIndex){
                this.addClassName("selected");
            }else{
                this.removeClassName("selected");
            }
        });
    }
}
