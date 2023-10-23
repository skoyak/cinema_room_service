package cinema.persistence

import cinema.model.Seat
import java.util.*

class TheaterRepository() {

    var seating = mutableMapOf<Seat, Boolean>()
    var capacity: Pair<Int, Int> = 0 to 0
    var tickets = mutableListOf<Pair<UUID, Seat>>(UUID.randomUUID() to Seat(-1,-1,-1))

}