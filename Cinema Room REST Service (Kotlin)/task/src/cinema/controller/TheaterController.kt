package cinema.controller

import cinema.dto.*
import cinema.handler.TheaterHandler
import cinema.model.ReturnRequest
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class TheaterController(
        private val theaterHandler: TheaterHandler
) {

    @GetMapping("/seats")
    fun getSeats(): ResponseEntity<GetSeatsResponse> {

        val stats = theaterHandler.getStats("super_secret")

        return ResponseEntity(
                GetSeatsResponse(
                        totalRows = stats.totalRows,
                        totalColumns = stats.totalColumns,
                        availableSeats = stats.availableSeats
                ),
                HttpStatus.OK)
    }

    @PostMapping("/purchase")
    fun purchaseSeat(@RequestBody request: PurchaseRequest): ResponseEntity<PurchaseResponse> {
        return ResponseEntity(theaterHandler.bookSeat(request.row, request.column), HttpStatus.OK)
    }

    @PostMapping("/return")
    fun returnTicket(@RequestBody request: ReturnRequest): ResponseEntity<String> {

        return ResponseEntity(
                jacksonObjectMapper()
                        .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                        .writeValueAsString(theaterHandler.returnTicket(ticketToken = request.token)),
                HttpStatus.OK)
    }

    @GetMapping("/stats")
    fun getStarts(@RequestParam password: String?): ResponseEntity<GetStatsResponse> {

        val bookingInfo = theaterHandler.getStats(password = password)

        val availableSeats = bookingInfo.availableSeats.size
        val bookedSeats = bookingInfo.bookedSeats.size
        val income = bookingInfo.bookedSeats.sumOf { it.price }

        return ResponseEntity(
                GetStatsResponse(
                        currentIncome = income,
                        numberOfPurchasedTickets = bookedSeats,
                        numberOfAvailableSeats = availableSeats
                ),
                HttpStatus.OK
        )    }

}