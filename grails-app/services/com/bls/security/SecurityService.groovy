package com.bls.security

class SecurityService {

    Boolean loginUser(String username, String password) {

      User user = User.findByUsernameAndPassword(username, password)
      if ( user ) {
        user.lastLoginDate = new Date()
        user.save()
        return Boolean.TRUE
      } else {
        return Boolean.FALSE
      }
    }
}
