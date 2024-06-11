fun main() {
    val dm = directMessages()

    val mes = Messages(1,"Hello", true,false)
    dm.add(1, Messages(4, "Meow", true, true))
    dm.add(2, Messages(5, "Yes", false, false))


    println(mes)
    println(dm.getChats())
    println(dm.getMessages(2))
}