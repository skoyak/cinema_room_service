package cinema.dto

import cinema.model.Seat
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class GetSeatsResponse(val totalRows: Int, val totalColumns: Int, val availableSeats: List<Seat>)
