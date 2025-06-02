package net.flying.hooves.components.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.signals.NumberSignal;
import com.vaadin.signals.Signal;
import net.flying.hooves.components.State;
import net.flying.hooves.model.CellMatrix;
import net.flying.hooves.model.Difficulty;

public class NewGameLayout extends HorizontalLayout {
    public NewGameLayout(State state) {
        ComboBox<Difficulty> difficultyComboBox = new ComboBox<>();
        difficultyComboBox.setItems(Difficulty.values());
        add(difficultyComboBox);
        difficultyComboBox.addValueChangeListener(event -> {
            if (event.getValue() != null) {
                state.setDifficulty(event.getValue());
            }
        });
        Button button = new Button("New Game");
        button.addClickListener(event -> {
            Difficulty difficulty = state.getDifficulty();
            if (difficulty == null) {
                return;
            }
            CellMatrix matrix = new CellMatrix();
            matrix.generate(difficulty);
            state.setCellMatrix(matrix);
        });
        difficultyComboBox.setValue(state.getDifficulty());
        add(button);
        Span remainingErrorCountSpan = new Span();
        add(remainingErrorCountSpan);
        Signal.effect(() -> {
            NumberSignal errorCountSignal = state.getErrorCountSignal();
            if (errorCountSignal != null && errorCountSignal.valueAsInt() != 3)  {
                remainingErrorCountSpan.setText("Remaining error count: " +  (3 - errorCountSignal.valueAsInt()));
            }
        });
    }
}
