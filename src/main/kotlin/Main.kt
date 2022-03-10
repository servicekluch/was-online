const val SECONDS_IN_MINUTE = 60u
const val SECONDS_IN_HOUR = 3_600u
const val SECONDS_IN_DAY = 86_400u

fun main() {

    print("Введите имя: ")
    val userName: String = readLine() ?: return

    if (!userName.isBlank()) {
        print("Количество секунд с момента выхода из сети? ")
        printText(userName, readLine()?.toUInt() ?: return)
    }
}

fun printText(userName: String, secAgo: UInt) {
    println(message = "$userName был(а) в сети ${agoToText(secAgo)}")
}


private fun agoToText(secAgo: UInt): String {

    return when (secAgo) {
        in 0u..SECONDS_IN_MINUTE -> "только что"
        in SECONDS_IN_MINUTE + 1u..SECONDS_IN_HOUR -> {
            val minutes: UInt = secAgo / SECONDS_IN_MINUTE
            "${minutes} ${numDeclension(minutes, "минуту", "минуты", "минут")} назад" }
        in SECONDS_IN_HOUR + 1u..SECONDS_IN_DAY  -> {
            val hours: UInt = secAgo / SECONDS_IN_HOUR
            "${hours}  ${numDeclension(hours, "час", "часа", "часов")} назад" }
        in SECONDS_IN_DAY + 1u..2u * SECONDS_IN_DAY  -> "сегодня"
        in 2u * SECONDS_IN_DAY  + 1u..3u * SECONDS_IN_DAY  -> "вчера"
        else -> "давно"
    }
}

private fun numDeclension(
    num: UInt,
    one: String,
    few: String,
    many: String
): String {
    val divisionBy10: UInt = num.mod(10u)
    val divisionBy100: UInt = num.mod(100u)
    return when {
        (divisionBy10 == 1u) && (divisionBy100 != 11u) -> one
        (2u <= divisionBy10) && (divisionBy10 <= 4u) && ((divisionBy100 < 10u) || (divisionBy100 >= 20u)) -> few
        else -> many
    }
}