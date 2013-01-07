package com.bls.security

class User {

  String username
  String password
  Date lastLoginDate

    static constraints = {
      username(nullable: false)
      password(nullable: false)
      lastLoginDate(nullable: true)
    }
  static mapping = {
    version false
  }
}
