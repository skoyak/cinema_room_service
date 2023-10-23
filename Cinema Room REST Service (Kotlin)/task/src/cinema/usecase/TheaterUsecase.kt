package cinema.handler

import cinema.dto.BookingResponse
import cinema.dto.PurchaseResponse
import cinema.dto.ReturnResponse
import cinema.exception.BusinessException
import cinema.model.Stats
import cinema.persistence.TheaterRepository
import java.util.*

interface TheaterUsecase {

    fun bookSeat(rowNum: Int, colNum: Int): PurchaseResponse

    fun returnTicket(ticketToken: UUID): ReturnResponse

    fun getStats(password: String?): Stats

}