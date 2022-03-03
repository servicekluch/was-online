const val SECONDS_IN_MINUTE = 60
const val SECONDS_IN_HOUR = 3_600
const val SECONDS_IN_DAY = 86_400

fun main() {

    print("Введите имя: ")
    val userName: String = readLine() ?: return

    if (!userName.isBlank()) {
        print("Количество секунд с момента выхода из сети? ")
        printText(userName, readLine()?.toUInt() ?: return)
    }
}

fun printText(userName: String, secAgo: UInt) {
    println(message = "$userName был(а) в сети ${agoToText(secAgo.toInt())}")
}


private fun agoToText(secAgo: Int): String {

    return when (secAgo) {
        in 0..SECONDS_IN_MINUTE -> "только что"
        in SECONDS_IN_MINUTE + 1..SECONDS_IN_HOUR ->
            "${secAgo / SECONDS_IN_MINUTE} ${numDeclension(secAgo / SECONDS_IN_MINUTE, TimeType.Minutes)} назад"
        in SECONDS_IN_HOUR + 1..SECONDS_IN_DAY  ->
            "${secAgo / SECONDS_IN_HOUR}  ${numDeclension(secAgo / SECONDS_IN_HOUR, TimeType.Hours)} назад"
        in SECONDS_IN_DAY + 1..2 * SECONDS_IN_DAY  -> "сегодня"
        in 2 * SECONDS_IN_DAY  + 1..3 * SECONDS_IN_DAY  -> "вчера"
        else -> "давно"
    }
}

private fun numDeclension(
    num: Int,
    timeType: TimeType
): String {
    val divisionBy10 = num.mod(10)
    val divisionBy100 = num.mod(100)
    return when {
        (divisionBy10 == 1) && (divisionBy100 != 11) ->
            when (timeType) {
                TimeType.Minutes -> "минуту"
                TimeType.Hours -> "час"
            }
        (2 <= divisionBy10) && (divisionBy10 <= 4) && ((divisionBy100 < 10) || (divisionBy100 >= 20)) ->
            when (timeType) {
                TimeType.Minutes -> "минуты"
                TimeType.Hours -> "часа"
            }
        else ->
            when (timeType) {
                TimeType.Minutes -> "минут"
                TimeType.Hours -> "часов"
            }
    }
}

private enum class TimeType {
    Minutes, Hours
}