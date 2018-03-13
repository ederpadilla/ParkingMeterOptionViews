package ederdev.padilla.driveoptionviews.util

/**
 * Created by ederpadilla on 12/03/18.
 */

class TimeEquivalence(amount: Int, minutes: Int) {
    var amount: Int = 0
        internal set
    var minutes: Int = 0
        internal set

    init {
        this.amount = amount
        this.minutes = minutes
    }

}
