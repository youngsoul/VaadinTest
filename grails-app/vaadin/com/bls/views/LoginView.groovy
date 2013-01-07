package com.bls.views

import com.bls.security.SecurityService
import com.bluelobsterstudios.vaadin.events.EventDispatcher
import com.bluelobsterstudios.vaadin.listeners.ValueChangeListener
import com.bluelobsterstudios.vaadin.ui.NavigatorPanel
import com.vaadin.data.Property
import com.vaadin.grails.Grails
import com.vaadin.navigator.ViewChangeListener
import com.vaadin.ui.PasswordField
import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout

/**
 * User: youngsoul
 *
 */
class LoginView extends NavigatorPanel  {

  String username
  String password


  def userNameValueChange = { Property.ValueChangeEvent evt ->
    username = evt.source.value
    println "username changed2 $username"


  }
  def passwordValueChange = { Property.ValueChangeEvent evt ->
    password = evt.source.value
    println "password changed2 $password"

    SecurityService securityService = Grails.get(SecurityService)
    Boolean isValid = securityService.loginUser(username, password)
    if ( isValid ) {
      println "Login Allowed"
      this.dispatchEvent("loginSuccess", username)
      navigator.navigateTo("mainView")
    } else {
      println "Login NOT Allowed"
    }

  }

  @Override
  void initializeView() {
    println "LoginView.initialize"
    setWidth("100%")
    setHeight("100%")
    VerticalLayout verticalLayout = new VerticalLayout()

    verticalLayout.spacing = true

    TextField userName = new TextField(inputPrompt: "Username", immediate: true)
    userName.addValueChangeListener(new ValueChangeListener(this.&userNameValueChange))
    userName.description = "Enter your username"
    userName.focus()
    verticalLayout.addComponent(userName)

    PasswordField passwordField = new PasswordField(inputPrompt: "Password", immediate: true)
    passwordField.addValueChangeListener(new ValueChangeListener(this.&passwordValueChange))
    verticalLayout.addComponent(passwordField)

    setContent(verticalLayout)
  }

  void enterView(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
    println "LoginView.enterView"
  }

  @Override
  boolean beforeViewChange(ViewChangeListener.ViewChangeEvent event) {
    println "LoginView.beforeViewChange"
    return true
  }

  @Override
  void afterViewChange(ViewChangeListener.ViewChangeEvent event) {
    println "LoginView.afterViewChange"
  }
}
