package cinema.model

data class Stats(val totalRows: Int, val totalColumns: Int, val availableSeats: List<Seat>, val bookedSeats: List<Seat>)
