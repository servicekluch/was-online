const val secondsInMinute = 60
const val secondsInHour = 3_600
const val secondsInDay = 86_400

fun main() {

    print("Введите имя: ")
    val userName: String = readLine() ?: return

    if (!userName.isBlank()) {
        print("Количество секунд с момента выхода из сети? ")
        printText(userName, readLine()?.toInt() ?: return)
    }
}

fun printText(userName: String, secAgo: Int) {
    println(message = "$userName был(а) в сети ${agoToText(secAgo)}")
}


private fun agoToText(secAgo: Int): String {

    return when (secAgo) {
        in 0..secondsInMinute -> "только что"
        in secondsInMinute + 1..secondsInHour ->
            "${secAgo / secondsInMinute} ${numDeclension(secAgo / secondsInMinute, TimeType.Minutes)} назад"
        in secondsInHour + 1..secondsInDay  ->
            "${secAgo / secondsInHour}  ${numDeclension(secAgo / secondsInHour, TimeType.Hours)} назад"
        in secondsInDay + 1..2 * secondsInDay  -> "сегодня"
        in 2 * secondsInDay  + 1..3 * secondsInDay  -> "вчера"
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