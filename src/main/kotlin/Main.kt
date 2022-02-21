fun main() {
    println("Введите имя")
    val user = readLine()?.let { User(it) }
    println("Количество секунд с момента выхода из сети?")
    if (user != null) {
        user.secAgo = readLine()?.toLong()!!
        user.printText()
    }
}
