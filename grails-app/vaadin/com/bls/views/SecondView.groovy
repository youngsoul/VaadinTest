package com.bls.views

import com.bluelobsterstudios.vaadin.listeners.ButtonClickListener
import com.bluelobsterstudios.vaadin.listeners.ItemClickListener
import com.bluelobsterstudios.vaadin.ui.NavigatorPanel
import com.vaadin.data.Item
import com.vaadin.data.util.IndexedContainer
import com.vaadin.event.ItemClickEvent
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.Button
import com.vaadin.ui.Label
import com.vaadin.ui.Table
import com.vaadin.ui.VerticalLayout
import com.vaadin.ui.themes.Reindeer

/**
 * User: youngsoul
 *
 */
class SecondView extends NavigatorPanel  {

  Table table = new Table("Test Table");

  @Override
  void initializeView() {
    println "SecondView.initallizeView"

    setWidth("100%")
    setHeight("100%")
    setCaption("Second View Panel")
    setStyleName(Reindeer.PANEL_LIGHT)

    VerticalLayout verticalLayout = new VerticalLayout()
    verticalLayout.setSpacing(true)
    verticalLayout.setMargin(true)

    Label label = new Label("Second View here")
    verticalLayout.addComponent(label)

    Button button = new Button("Prev")
    button.addClickListener(new ButtonClickListener({navigator.navigateTo("mainView")}))
    verticalLayout.addComponent(button)

    table.with {
      setWidth("100%")
      setHeight("170px")
      setSelectable(true)
      setMultiSelect(true)
      setImmediate(true)
      setColumnReorderingAllowed(true)
      setColumnCollapsingAllowed(true)
    }
    table.addItemClickListener( new ItemClickListener({ ItemClickEvent event ->

      println "Column Selected: ${event.propertyId} for itemId: ${event.itemId}"

    }))

    IndexedContainer container = new IndexedContainer();
    container.addContainerProperty("FirstName", String.class,null);
    container.addContainerProperty("LastName", String.class,null);
    container.addContainerProperty("UserName", String.class,null);

    100.times { i ->
      Item item = container.addItem(i)
      item.getItemProperty("FirstName").setValue("FN $i".toString())
      item.getItemProperty("LastName").setValue("LN $i".toString())
      item.getItemProperty("UserName").setValue("UN $i".toString())
    }
    table.setContainerDataSource(container)
    verticalLayout.addComponent(table)

    setContent(verticalLayout)
  }

  @Override
  void enterView(ViewChangeListener.ViewChangeEvent event) {
    println "SecondView.enterView"
  }

  @Override
  boolean beforeViewChange(ViewChangeListener.ViewChangeEvent event) {
    println "SecondView.beforeViewChange"
    return true
  }

  @Override
  void afterViewChange(ViewChangeListener.ViewChangeEvent event) {

    println "SecondView.afterViewChange"
  }
}
