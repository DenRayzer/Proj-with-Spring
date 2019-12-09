package com.example.demo.front;

import com.example.demo.entity.Person;
import com.example.demo.service.PersonService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "personView")
public class MainView extends VerticalLayout {
    private PersonService personService;

    private Grid<Person> grid;
    private TextField textField;
    private Button button;

    @Autowired
    public MainView(PersonService personService) {
        this.personService = personService;
        grid = new Grid<>(Person.class);
        grid.setColumns("name", "age");
        grid.asSingleSelect().addValueChangeListener(e ->
                Notification.show("person selected " + e.getValue().getName(), 1000, Notification.Position.BOTTOM_END));

        button = new Button("New person", VaadinIcon.PLUS.create());
        button.addClickListener(e -> Notification.show("button clicked", 1000, Notification.Position.BOTTOM_END));

        textField = new TextField();
        textField.setPlaceholder("Filter by person name");
        textField.setClearButtonVisible(true);
        textField.setValueChangeMode(ValueChangeMode.EAGER);
        textField.addValueChangeListener(e -> listPersons(e.getValue()));

        HorizontalLayout actions = new HorizontalLayout(textField, button);
        add(actions, grid);

        listPersons("");
    }

    void listPersons(String filterText) {
        if (StringUtils.isEmpty(filterText)) {
            grid.setItems(personService.getAllPersons());
        } else {
            grid.setItems(personService.search(filterText));
        }
    }
}
