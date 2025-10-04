package dev.dead.springdatarestkt.repositories

import dev.dead.springdatarestkt.entities.Beer
import dev.dead.springdatarestkt.entities.BeerStyle
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface BeerRepository : JpaRepository<Beer, UUID> {

    fun findAllByBeerName(beerName: String, pageable: Pageable): Page<Beer>

    fun findAllByBeerStyle(beerStyle: BeerStyle, pageable: Pageable): Page<Beer>

    fun findAllByBeerNameAndBeerStyle(beerName: String, beerStyle: BeerStyle, pageable: Pageable): Page<Beer>

    fun findByUpc(upc: String): Beer?
}
