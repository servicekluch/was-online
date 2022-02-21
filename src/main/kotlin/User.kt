class User(private val userName: String) {
    private val nowText = "только что"
    private val todayText = "сегодня"
    private val yesterdayText = "вчера"
    private val longAgoText = "давно"
    private val minutesText = "минуту"
    private val minutsText = "минуты"
    private val minutText = "минут"
    private val hourText = "час"
    private val houraText = "часа"
    private val hoursText = "часов"
    var secAgo: Long = Long.MAX_VALUE

    fun printText() {
        println(message = "$userName был(а) в сети ${agoToText(secAgo)}")
    }

    private fun agoToText(secAgo: Long): String {
        val minutes = secAgo / 60L
        val hours = secAgo / 3600L
        return when (secAgo) {
            in 0..60L -> nowText
            in 60L + 1..3600L ->
                "$minutes ${numDeclension(minutes, minutesText, minutsText, minutText)} назад"
            in 3600L + 1..86400L ->
                "$hours ${numDeclension(hours, hourText, houraText, hoursText)} назад"
            in 86400L + 1..2 * 86400L -> todayText
            in 2 * 86400L + 1..3 * 86400L -> yesterdayText
            else -> longAgoText
        }
    }

    private fun numDeclension(
        num: Long,
        textNumDeclension: String,
        textNumDeclension1: String,
        textNumDeclension2: String
    ): String {
        return when {
            num.mod(100L) == 11L -> textNumDeclension
            num.mod(10L) == 1L -> textNumDeclension1
            num.mod(10L) == 2L -> textNumDeclension2
            else -> textNumDeclension
        }
    }

}