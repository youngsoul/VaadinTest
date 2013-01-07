package app

import com.vaadin.server.VaadinRequest
import com.vaadin.ui.Label
import com.vaadin.ui.UI
import com.vaadin.ui.VerticalLayout

/**
 * User: youngsoul
 *
 */
class HelloWorld extends UI {

  @Override
  protected void init(VaadinRequest vaadinRequest) {
    getPage().setTitle("Hello Vaadin World from Grails")

    VerticalLayout layout = new VerticalLayout()
    layout.addComponent(new Label("Hello Vaadin/Grails World"))
    setContent(layout)
  }
}
