package dev.dead.springdatarestkt.repositories

import dev.dead.springdatarestkt.entities.Beer
import dev.dead.springdatarestkt.entities.BeerStyle
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.UUID

@RepositoryRestResource(path = "beer", collectionResourceRel = "beer")
interface BeerRepository : JpaRepository<Beer, UUID> {

    fun findAllByBeerName(beerName: String, pageable: Pageable): Page<Beer>

    fun findAllByBeerStyle(beerStyle: BeerStyle, pageable: Pageable): Page<Beer>

    fun findAllByBeerNameAndBeerStyle(beerName: String, beerStyle: BeerStyle, pageable: Pageable): Page<Beer>

    fun findByUpc(upc: String): Beer?
}
