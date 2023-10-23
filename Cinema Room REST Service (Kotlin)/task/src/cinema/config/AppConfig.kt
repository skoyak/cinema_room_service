package cinema.config

import cinema.handler.TheaterHandler
import cinema.model.Seat
import cinema.persistence.TheaterRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {

    @Bean
    fun theaterRepository(): TheaterRepository {

        val theaterRepository = TheaterRepository()
        val rowNum = 9
        val colNum = 9

        theaterRepository.capacity = rowNum to colNum

        theaterRepository.seating =
                (1..rowNum).map { rowNum ->
                    (1..colNum).map { colNum ->
                        when (rowNum) {
                            in 1..4 -> Seat(column = colNum, row = rowNum, price = 10) to false
                            else -> Seat(column = colNum, row = rowNum, price = 8) to false
                        }
                    }
                }.flatten().toMap().toMutableMap()

        return theaterRepository
    }

    @Bean
    fun theaterHandler(theaterRepository: TheaterRepository): TheaterHandler{
        return TheaterHandler(theaterRepository = theaterRepository)
    }

}