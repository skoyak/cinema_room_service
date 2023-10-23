package cinema.exception

data class WrongTokenException(val error: String = "Wrong token!"): Exception()