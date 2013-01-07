package app

import com.bls.views.LoginView
import com.bls.views.MainView
import com.bls.views.SecondView
import com.bluelobsterstudios.vaadin.listeners.NavigatorViewChangeListener
import com.vaadin.navigator.Navigator
import com.vaadin.server.VaadinRequest
import com.vaadin.ui.UI

/**
 * User: youngsoul
 *
 * Test Application Entry Point
 *
 */

class ApplicationUI extends UI {
  @Override
  protected void init(VaadinRequest request) {


    Navigator navigator = new Navigator(this,this)

    LoginView loginView = new LoginView(navigator: navigator)

    // example of adding an event listener for a custom lightweight event dispatched by
    // the login view upon successful login.
    loginView.addEventListener("loginSuccess",{
      println "Login View - Login was successful!"
    })

    navigator.addView("loginView", loginView)
    navigator.addView("mainView", new MainView(navigator: navigator))
    navigator.addView("secondView", new SecondView(navigator: navigator))

    navigator.addViewChangeListener(new NavigatorViewChangeListener())

    navigator.navigateTo("loginView")
  }
}
