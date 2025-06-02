package net.flying.hooves.components.ui;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.dom.Style;
import com.vaadin.signals.Signal;
import net.flying.hooves.components.State;
import net.flying.hooves.model.Cell;
import net.flying.hooves.model.CellMatrix;

public class SudokuTable extends Div {
    private int currentGameIndex = 0;
    private final SudokuCell[][] cells = new SudokuCell[9][9];
    private final State state;
    private final float WIDTH = 600;
    private final float HEIGHT = 600;

    public SudokuTable(State state) {
        this.state = state;
        getStyle()
                .setPosition(Style.Position.RELATIVE)
                .setDisplay(Style.Display.GRID)
                .set("grid-template-columns", "repeat(9, 1fr)");

        Signal.effect(() -> {
            if (state.getCellMatrix() != null && CellMatrix.getIndex() != currentGameIndex) {
                for (SudokuCell[] cell : cells) {
                    for (SudokuCell sudokuCell : cell) {
                        if (sudokuCell != null) {
                            sudokuCell.removeFromParent();
                        }
                    }
                }
                createGame(state.getCellMatrix());
            }

        });
        Signal.effect(() -> {
            Cell value = state.getSelectedCell().value();
            if (value != null) {
                Notification.show(value.getRow() + ", " + value.getColumn() + " is selected");
            }
        });
        Div messageDiv = new Div();
        messageDiv.setWidth("100%");
        messageDiv.setHeight("100%");
        messageDiv.getStyle()
                .setDisplay(Style.Display.FLEX)
                .setZIndex(100)
                .setPosition(Style.Position.ABSOLUTE)
                .setTextAlign(Style.TextAlign.CENTER)
                .setJustifyContent(Style.JustifyContent.CENTER)
                .setBackgroundColor("rgba(100,100,100,0.5)")
                .setFontSize("xxx-large")
                .setAlignItems(Style.AlignItems.CENTER);
        messageDiv.setVisible(false);
        add(messageDiv);
        Signal.effect(() -> {
            if (Boolean.TRUE.equals(state.getFailed().value())) {
                messageDiv.setText("You failed the game!");
            } else if (Boolean.TRUE.equals(state.getCompleted().value())) {
                messageDiv.setText("You completed the game!");
            }
            messageDiv.setVisible(Boolean.TRUE.equals(state.getCompleted().value()) || Boolean.TRUE.equals(state.getFailed().value()));
        });
    }

    private void createGame(CellMatrix matrix) {
        for (int i = 0; i < matrix.getMatrix().length; i++) {
            for (int j = 0; j < matrix.getMatrix()[i].length; j++) {
                cells[i][j] = new SudokuCell(i, j, state);
                //<theme-editor-local-classname>
                addClassName("sudoku-cell");
                cells[i][j].setWidth(WIDTH / (float) 9, Unit.PIXELS);
                cells[i][j].setHeight(HEIGHT / (float) 9, Unit.PIXELS);
                add(cells[i][j]);
            }
        }
    }
}
