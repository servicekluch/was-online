fun main() {

    print("Введите имя: ")
    val userName: String = readLine() ?: return

    if (!userName.isBlank()) {
        print("Количество секунд с момента выхода из сети? ")
        printText(userName, (readLine()?.toLong() ?: return).toInt())
    }
}

fun printText(userName: String, secAgo: Int) {
    println(message = "$userName был(а) в сети ${agoToText(secAgo)}")
}

private fun agoToText(secAgo: Int): String {

    var secondsInMinute = 60
    var secondsInHour = 3_600
    var secondsInDay = 86_400
    var minutes = secAgo / secondsInMinute
    var hours = secAgo / secondsInHour

    var minute_One = "минуту"
    var minute_Few = "минуты"
    var minute_Many = "минут"
    var hour_One = "час"
    var hour_Few = "часа"
    var hour_Many = "часов"

    return when (secAgo) {
        in 0..secondsInMinute -> "только что"
        in secondsInMinute + 1..secondsInHour ->
            "$minutes ${numDeclension(minutes, minute_One, minute_Few, minute_Many)} назад"
        in secondsInHour + 1..secondsInDay  ->
            "$hours ${numDeclension(hours, hour_One, hour_Few, hour_Many)} назад"
        in secondsInDay + 1..2 * secondsInDay  -> "сегодня"
        in 2 * secondsInDay  + 1..3 * secondsInDay  -> "вчера"
        else -> "давно"
    }
}

private fun numDeclension(
    num: Int,
    one: String,
    few: String,
    many: String
): String {
    var divisionBy10 = num.mod(10)
    var divisionBy100 = num.mod(100)
    return when {
        (divisionBy10 == 1) && (divisionBy100 != 11) -> one
        (2 <= divisionBy10) && (divisionBy10 <= 4) && ((divisionBy100 < 10) || (divisionBy100 >= 20)) -> few
        else -> many
    }
}
