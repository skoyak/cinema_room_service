package cinema.dto

import cinema.model.Seat
import java.util.*

data class PurchaseResponse(val token: UUID, val ticket: Seat)
