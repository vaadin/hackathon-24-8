package net.flying.hooves.views;

import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import net.flying.hooves.components.State;
import net.flying.hooves.components.ui.NewGameLayout;
import net.flying.hooves.components.ui.NumberPickerLayout;
import net.flying.hooves.components.ui.SudokuTable;

@PageTitle("SudokuView")
@AnonymousAllowed
@Route(value = "")
public class SudokuView extends Main {
    private final State state = new State();
    private final SudokuTable table = new SudokuTable(state);
    public SudokuView() {

        VerticalLayout layout = new VerticalLayout();
        layout.add(new NewGameLayout(state));
        layout.setAlignItems(FlexComponent.Alignment.CENTER);
        layout.setWidth("100%");
        layout.setHeight("100%");
        layout.add(table);
        layout.add(new Hr());
        layout.add(new NumberPickerLayout(state));
        add(layout);
    }
}
