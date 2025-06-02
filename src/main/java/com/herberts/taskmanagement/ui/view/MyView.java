package com.herberts.taskmanagement.ui.view;

import com.herberts.base.ui.component.ViewToolbar;
import com.herberts.taskmanagement.domain.Task;
import com.herberts.taskmanagement.service.TaskService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.dashboard.Dashboard;
import com.vaadin.flow.component.dashboard.DashboardWidget;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.streams.DownloadHandler;
import com.vaadin.flow.theme.lumo.LumoUtility;
import jakarta.annotation.security.PermitAll;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Optional;

import static com.vaadin.flow.spring.data.VaadinSpringDataHelpers.toSpringPageRequest;

@Route("my-view")
@PageTitle("My view")
@Menu(order = 1, icon = "vaadin:clipboard-check", title = "My view")
@PermitAll // When security is enabled, allow all authenticated users
public class MyView extends Main {


    private TextField fileNameField;
    private TextArea fileContentField;


    public MyView() {
        var dashboard = new Dashboard();
        dashboard.setEditable(true);
        dashboard.add(createDownloadDashboardWidget());
        add(dashboard);
    }

    private DashboardWidget createDownloadDashboardWidget() {
        fileNameField = new TextField();
        fileContentField = new TextArea();
        var layout = new VerticalLayout(fileNameField, fileContentField, createMenuBarWithDownloadItem());

        var widget = new DashboardWidget(layout);
        widget.setHeaderContent(new Div());
        return widget;
    }

    private MenuBar createMenuBarWithDownloadItem() {
        var menuBar = new MenuBar();
        menuBar.addItem(createDownloadLink());
        var secondItem = menuBar.addItem("Download through sub menu.");
        secondItem.getSubMenu().addItem(createDownloadLink());

        return menuBar;
    }

    private Anchor createDownloadLink() {
        return new Anchor(e -> {
            e.setFileName(fileNameField.getValue() + ".txt");
            e.setContentType("text/plain");
            try {
                e.getOutputStream().write(fileContentField.getValue().getBytes(StandardCharsets.UTF_8));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            e.getUI().access(() -> Notification.show("\"%s\" Download completed".formatted(fileContentField.getValue() + ".txt")));
        }, "Download here");
    }
}
