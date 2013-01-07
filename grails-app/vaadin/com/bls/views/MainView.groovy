package com.bls.views

import com.bls.City
import com.bluelobsterstudios.vaadin.listeners.ButtonClickListener
import com.bluelobsterstudios.vaadin.listeners.ValueChangeListener
import com.bluelobsterstudios.vaadin.ui.NavigatorPanel
import com.bluelobsterstudios.vaadin.watchers.ChangeWatcher
import com.vaadin.data.Container
import com.vaadin.data.util.IndexedContainer
import com.vaadin.navigator.View
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.Button
import com.vaadin.ui.Label
import com.vaadin.ui.ListSelect
import com.vaadin.ui.Notification
import com.vaadin.ui.VerticalLayout

/**
 * User: youngsoul
 *
 */
class MainView extends NavigatorPanel {

  ListSelect citySelect
  ChangeWatcher cityChangeWatcher = new ChangeWatcher()
  Label cityLabel

  @Override
  void initializeView() {
    println "MainView.initialize"

    setWidth("100%")
    setHeight("100%")
    setCaption("Main View Panel")

    VerticalLayout verticalLayout = new VerticalLayout()
    verticalLayout.setSpacing(true)
    verticalLayout.setMargin(true)

    Label label = new Label("Main View here")
    verticalLayout.addComponent(label)

    Button button = new Button("Next")
    button.addClickListener(new ButtonClickListener({navigator.navigateTo("secondView")}))
    verticalLayout.addComponent(button)


    citySelect = new ListSelect("Please select a city")
    citySelect.with {
      setRows(7)
      setNullSelectionAllowed(false)
      select("Brussels")
      setImmediate(true)
      addValueChangeListener(new ValueChangeListener({event ->
        Notification.show("Selected city: " + event.getProperty().value )

        cityChangeWatcher.valueToWatch = event.getProperty().value
      }) )
    }
    verticalLayout.addComponent(citySelect)
    verticalLayout.addComponent(new Label(value: "Selected City:"))

    cityLabel = new Label(value: "")
    cityChangeWatcher.watch("name", cityLabel, "value", "")
    cityChangeWatcher.watch("name", { newValue ->
      println "City Change Watcher - name changed to $newValue"
    })
    verticalLayout.addComponent(cityLabel)

    setContent(verticalLayout)

  }

  @Override
  void enterView(ViewChangeListener.ViewChangeEvent event) {
    println "MainView.enterView"
    citySelect.removeAllItems()
    List<City> cities = City.all
    Container c = new IndexedContainer()
    cities?.each {
      c.addItem(it)
    }
    citySelect.setContainerDataSource(c)
  }

  @Override
  boolean beforeViewChange(ViewChangeListener.ViewChangeEvent event) {
    println "MainView.beforeViewChange"
    return true
  }

  @Override
  void afterViewChange(ViewChangeListener.ViewChangeEvent event) {

    println "MainView.afterViewChange"
  }
}
