import com.bls.City
import com.bls.security.User


class BootStrap {

  void createUsers() {
    User admin = new User(username: "admin", password: "admin").save()
  }
  void createCities() {
    new City(name: "Berlin").save()
    new City(name:  "Brussels").save()
    new City(name:  "Helsinki").save()
    new City(name:  "Madrid").save()
    new City(name:  "Oslo").save()
    new City(name:  "Paris").save()
    new City(name:  "Stockholm").save()
  }
    def init = { servletContext ->
      createUsers()
      createCities()

    }
    def destroy = {
    }
}
