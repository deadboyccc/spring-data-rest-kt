package dev.dead.springdatarestkt.entities

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Entity
data class Beer(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    var id: UUID? = null,

    @Version
    var version: Int? = null,

    @field:NotNull
    @field:NotBlank
    @field:Size(max = 50)
    @Column(length = 50)
    var beerName: String,

    @field:NotNull
    @Enumerated(EnumType.STRING)
    var beerStyle: BeerStyle,

    @field:NotNull
    @field:NotBlank
    @field:Size(max = 255)
    @Column(unique = true)
    var upc: String,

    var quantityOnHand: Int = 0,

    @field:NotNull
    var price: BigDecimal,

    var createdAt: LocalDateTime? = null,
    var updatedAt: LocalDateTime? = null
) {
    @PrePersist
    fun prePersist() {
        createdAt = LocalDateTime.now()
        updatedAt = LocalDateTime.now()
    }

    @PreUpdate
    fun preUpdate() {
        updatedAt = LocalDateTime.now()
    }
}