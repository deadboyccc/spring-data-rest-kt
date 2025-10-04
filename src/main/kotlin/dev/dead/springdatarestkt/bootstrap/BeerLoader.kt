package dev.dead.springdatarestkt.bootstrap

import dev.dead.springdatarestkt.entities.Beer
import dev.dead.springdatarestkt.entities.BeerStyle
import dev.dead.springdatarestkt.repositories.BeerRepository
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.math.BigInteger
import java.util.concurrent.ThreadLocalRandom
import jakarta.transaction.Transactional

@Component
class BeerLoader(private val beerRepository: BeerRepository) {

    companion object {
        private val log = LoggerFactory.getLogger(BeerLoader::class.java)

        const val BEER_1_UPC = "0631234200036"
        const val BEER_2_UPC = "9122089364369"
        const val BEER_3_UPC = "0083783375213"
        const val BEER_4_UPC = "4666337557578"
        const val BEER_5_UPC = "8380495518610"
        const val BEER_6_UPC = "5677465691934"
        const val BEER_7_UPC = "5463533082885"
        const val BEER_8_UPC = "5339741428398"
        const val BEER_9_UPC = "1726923962766"
        const val BEER_10_UPC = "8484957731774"
        const val BEER_11_UPC = "6266328524787"
        const val BEER_12_UPC = "7490217802727"
        const val BEER_13_UPC = "8579613295827"
        const val BEER_14_UPC = "2318301340601"
        const val BEER_15_UPC = "9401790633828"
        const val BEER_16_UPC = "4813896316225"
        const val BEER_17_UPC = "3431272499891"
        const val BEER_18_UPC = "2380867498485"
        const val BEER_19_UPC = "4323950503848"
        const val BEER_20_UPC = "4006016803570"
        const val BEER_21_UPC = "9883012356263"
        const val BEER_22_UPC = "0583668718888"
        const val BEER_23_UPC = "9006801347604"
        const val BEER_24_UPC = "0610275742736"
        const val BEER_25_UPC = "6504219363283"
        const val BEER_26_UPC = "7245173761003"
        const val BEER_27_UPC = "0326984155094"
        const val BEER_28_UPC = "1350188843012"
        const val BEER_29_UPC = "0986442492927"
        const val BEER_30_UPC = "8670687641074"
    }

    /**
     * Runs once the application is fully ready (after context refresh).
     * Using ApplicationReadyEvent avoids timing problems with proxying/transactions.
     */
    @EventListener(ApplicationReadyEvent::class)
    @Transactional
    fun onApplicationReady() {
        val currentCount = beerRepository.count()
        log.debug("BeerLoader triggered. current count = {}", currentCount)

        if (currentCount > 0L) {
            log.debug("Beers already present, skipping initial load.")
            return
        }

        val rnd = ThreadLocalRandom.current()

        val beers = listOf(
            buildBeer("Mango Bobs", BeerStyle.ALE, BEER_1_UPC, rnd),
            buildBeer("Galaxy Cat", BeerStyle.PALE_ALE, BEER_2_UPC, rnd),
            buildBeer("No Hammers On The Bar", BeerStyle.WHEAT, BEER_3_UPC, rnd),
            buildBeer("Blessed", BeerStyle.STOUT, BEER_4_UPC, rnd),
            buildBeer("Adjunct Trail", BeerStyle.STOUT, BEER_5_UPC, rnd),
            buildBeer("Very GGGreenn", BeerStyle.IPA, BEER_6_UPC, rnd),
            buildBeer("Double Barrel Hunahpu's", BeerStyle.STOUT, BEER_7_UPC, rnd),
            buildBeer("Very Hazy", BeerStyle.IPA, BEER_8_UPC, rnd),
            buildBeer("SR-71", BeerStyle.STOUT, BEER_9_UPC, rnd),
            buildBeer("Pliny the Younger", BeerStyle.IPA, BEER_10_UPC, rnd),
            buildBeer("Blessed", BeerStyle.STOUT, BEER_11_UPC, rnd),
            buildBeer("King Krush", BeerStyle.IPA, BEER_12_UPC, rnd),
            buildBeer("PBS Porter", BeerStyle.PORTER, BEER_13_UPC, rnd),
            buildBeer("Pinball Porter", BeerStyle.STOUT, BEER_14_UPC, rnd),
            buildBeer("Golden Budda", BeerStyle.STOUT, BEER_15_UPC, rnd),
            buildBeer("Grand Central Red", BeerStyle.LAGER, BEER_16_UPC, rnd),
            buildBeer("Pac-Man", BeerStyle.STOUT, BEER_17_UPC, rnd),
            buildBeer("Ro Sham Bo", BeerStyle.IPA, BEER_18_UPC, rnd),
            buildBeer("Summer Wheatly", BeerStyle.WHEAT, BEER_19_UPC, rnd),
            buildBeer("Java Jill", BeerStyle.LAGER, BEER_20_UPC, rnd),
            buildBeer("Bike Trail Pale", BeerStyle.PALE_ALE, BEER_21_UPC, rnd),
            buildBeer("N.Z.P", BeerStyle.IPA, BEER_22_UPC, rnd),
            buildBeer("Stawberry Blond", BeerStyle.WHEAT, BEER_23_UPC, rnd),
            buildBeer("Loco", BeerStyle.PORTER, BEER_24_UPC, rnd),
            buildBeer("Spocktoberfest", BeerStyle.STOUT, BEER_25_UPC, rnd),
            buildBeer("Beach Blond Ale", BeerStyle.ALE, BEER_26_UPC, rnd),
            buildBeer("Bimini Twist IPA", BeerStyle.IPA, BEER_27_UPC, rnd),
            buildBeer("Rod Bender Red Ale", BeerStyle.ALE, BEER_28_UPC, rnd),
            buildBeer("Floating Dock", BeerStyle.SAISON, BEER_29_UPC, rnd),
            buildBeer("El Hefe", BeerStyle.WHEAT, BEER_30_UPC, rnd)
        )

        beerRepository.saveAllAndFlush(beers)
        // If repository is JpaRepository, the transaction will be committed when this method returns.
        val afterCount = beerRepository.count()
        log.debug("Initial load finished. new count = {}", afterCount)
    }

    private fun buildBeer(name: String, style: BeerStyle, upc: String, rnd: ThreadLocalRandom): Beer {
        val price = BigDecimal(BigInteger.valueOf(rnd.nextInt(10000).toLong()), 2)
        val qty = rnd.nextInt(5000)
        return Beer(
            beerName = name,
            beerStyle = style,
            upc = upc,
            price = price,
            quantityOnHand = qty
        )
    }
}
