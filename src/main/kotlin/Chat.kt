class Chat (){
    val messages : MutableList<Message> = mutableListOf()

    fun readMessages(){
        for (message in this.messages)
            message.read = true
    }

    fun unreadMessagesB() : Boolean {
        return messages.filter { !it.read }.isEmpty()
    }

    override fun toString(): String {
        var result : String = "[ chat "
        messages.forEach { message ->
            result += " $message"
        }
        return result + "]"
    }
}