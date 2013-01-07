package com.bls

class City {

  String name

  static constraints = {
  }
  static mapping = {
    version false
  }

  @Override
  public String toString() {
    return name
  }
}
