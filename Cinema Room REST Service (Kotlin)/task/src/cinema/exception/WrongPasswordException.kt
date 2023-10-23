package cinema.exception

class WrongPasswordException(val error: String = "The password is wrong!") : Exception()
