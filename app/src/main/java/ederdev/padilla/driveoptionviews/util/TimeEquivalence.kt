package ederdev.padilla.driveoptionviews.util

/**
 * Created by ederpadilla on 12/03/18.
 */

class TimeEquivalence(amount: Int, minutes: Int, hoursString : String , minutesString : String) {
    var amount: Int = 0
        internal set
    var minutes: Int = 0
        internal set
    var minutesString: String = "0"
        internal set
    var hoursString: String = "0"
        internal set
    init {
        this.amount = amount
        this.minutes = minutes
        this.hoursString = hoursString
        this.minutesString = minutesString
    }

}
