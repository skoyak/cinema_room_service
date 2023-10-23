package cinema.handler

import cinema.dto.BookingResponse
import cinema.dto.PurchaseResponse
import cinema.dto.ReturnResponse
import cinema.exception.BusinessException
import cinema.exception.WrongPasswordException
import cinema.exception.WrongTokenException
import cinema.model.Seat
import cinema.model.Stats
import cinema.persistence.TheaterRepository
import java.util.*

class TheaterHandler(
        private val theaterRepository: TheaterRepository
) : TheaterUsecase {
    override fun bookSeat(rowNum: Int, colNum: Int): PurchaseResponse {
        val seatMap = theaterRepository.seating.filter { it.key.row == rowNum && it.key.column == colNum }

        checkNotNull(seatMap.keys.firstOrNull()) { throw BusinessException("The number of a row or a column is out of bounds!") }

        check(!seatMap.values.first()) { throw BusinessException("The ticket has been already purchased!") }

        val seat = Seat(
                row = seatMap.keys.first().row,
                column = seatMap.keys.first().column,
                price = seatMap.keys.first().price
        )

        val token = UUID.randomUUID()

        theaterRepository.seating[seatMap.keys.first()] = true
        theaterRepository.tickets.add(token to seat)

        return PurchaseResponse(
                token = token,
                ticket = seat)
    }

    override fun returnTicket(ticketToken: UUID): ReturnResponse {
        val ticket =
                requireNotNull(
                        theaterRepository.tickets.firstOrNull {
                            it.first == ticketToken
                        }
                ) { throw WrongTokenException() }
                        .also {
                            theaterRepository.tickets = theaterRepository.tickets.filterNot { repoPair ->
                                repoPair.first == it.first
                            }.toMutableList()

                            theaterRepository.seating[it.second] = false
                        }

        return ReturnResponse(returnedTicket = ticket.second)
    }

    override fun getStats(password: String?): Stats {

        check(password == "super_secret") { throw WrongPasswordException() }

        val availableSeats = theaterRepository.seating.filter { !it.value }.keys.toList()
        val bookedSeats = theaterRepository.seating.filter { it.value }.keys.toList()

        return Stats(theaterRepository.capacity.first, theaterRepository.capacity.second, availableSeats, bookedSeats)
    }

}