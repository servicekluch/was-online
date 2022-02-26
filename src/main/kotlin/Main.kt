fun main() {

    print("Введите имя\n")
    val userName: String = readLine() ?: return

    print("Количество секунд с момента выхода из сети?\n")
    if (userName.isEmpty()) {
        print("Пустая строка")
    } else {
        val secAgo = (readLine()?.toLong() ?: return)
        printText(userName, secAgo.toInt())
    }
}


fun printText(userName: String, secAgo: Int) {
    println(message = "$userName был(а) в сети ${agoToText(secAgo)}")
}

private fun agoToText(secAgo: Int): String {

    val secInMinutes = 60
    val secInHours = 3_600
    val secInDays = 86_400

    return when (secAgo) {
        in 0..secInMinutes -> "только что"
        in secInMinutes + 1..secInHours ->
            "${secAgo / secInMinutes} ${numDeclension((secAgo / secInMinutes),"минуту", "минут", "минуты")} назад"
        in secInHours + 1..secInDays  ->
            "${secAgo / secInHours} ${numDeclension((secAgo / secInMinutes),"час", "часов", "часа")} назад"
        in secInDays  + 1..2 * secInDays  -> "сегодня"
        in 2 * secInDays  + 1..3 * secInDays  -> "вчера"
        else -> "давно"
    }
}

private fun numDeclension(
    num: Int,
    one: String,
    few: String,
    many: String
): String {
    return when {
        (num.mod(10) == 1) && (num.mod(100) != 11) -> one
        (2 <= num.mod(10)) && (num.mod(10) <= 4) && ((num.mod(100) < 10) || (num.mod(100) >= 20)) -> many
        else -> few
    }
}