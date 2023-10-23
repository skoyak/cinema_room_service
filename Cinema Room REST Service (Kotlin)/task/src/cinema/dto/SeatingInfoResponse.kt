package cinema.dto

import cinema.model.Seat

data class BookingResponse(val totalRows: Int, val totalColumns: Int, val availableSeats: List<Seat>, val bookedSeats: List<Seat>)