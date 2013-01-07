package com.bls

import com.bls.security.SecurityService
import com.bluelobsterstudios.vaadin.events.EventDispatcher
import com.bluelobsterstudios.vaadin.listeners.ValueChangeListener
import com.vaadin.data.Property
import com.vaadin.grails.Grails
import com.vaadin.navigator.Navigator
import com.vaadin.navigator.View
import com.vaadin.ui.PasswordField
import com.vaadin.ui.TextField
import com.vaadin.ui.VerticalLayout

/**
 * User: youngsoul
 *
 */

class LoginForm extends VerticalLayout  {

  @Delegate EventDispatcher eventDispatcher = new EventDispatcher()

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
    } else {
      println "Login NOT Allowed"
    }

    Navigator n = new Navigator()

  }


  @Override
  void attach() {
    super.attach()

    spacing = true

    TextField userName = new TextField(inputPrompt: "Username", immediate: true)
    userName.addValueChangeListener(new ValueChangeListener(this.&userNameValueChange))
    userName.description = "Enter your username"
    userName.focus()
    addComponent(userName)

    PasswordField passwordField = new PasswordField(inputPrompt: "Password", immediate: true)
    passwordField.addValueChangeListener(new ValueChangeListener(this.&passwordValueChange))
    addComponent(passwordField)

  }
}
