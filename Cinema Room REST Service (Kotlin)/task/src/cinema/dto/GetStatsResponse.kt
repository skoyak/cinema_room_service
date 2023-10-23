package cinema.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class GetStatsResponse(
        val currentIncome: Int,
        val numberOfAvailableSeats: Int,
        val numberOfPurchasedTickets: Int
)
