package net.flying.hooves.components.ui;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.signals.Signal;
import net.flying.hooves.components.State;
import net.flying.hooves.model.Cell;

public class NumberPickerLayout extends HorizontalLayout {
    public NumberPickerLayout(State state) {
        setWidth(100, Unit.PERCENTAGE);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        for (int i = 0; i < 9; i++) {
            Button btn = new Button(String.valueOf(i + 1));
            add(btn);
            Signal.effect(() -> {
                if(Boolean.TRUE.equals(state.getCompleted().value())){
                    btn.setEnabled(false);
                    return;
                }
                if(Boolean.TRUE.equals(state.getFailed().value())){
                    btn.setEnabled(false);
                    return;
                }
                if (state.getSelectedCell() == null || state.getSelectedCell().value() == null) {
                    btn.setEnabled(false);
                    return;
                }
                Cell cell = state.getSelectedCell().value();
                btn.setEnabled(!cell.isValueVisible() && (cell.getValue() != cell.getUserValue()));
            });
            btn.addClickListener(e -> {
                int userSelectedBtnValue = Integer.parseInt(btn.getText());
                Cell cell = state.getSelectedCell().value();
                if (cell != null) {
                    cell.setUserValue(userSelectedBtnValue);
                    state.updateCell(cell);
                }
            });
        }
    }
}
