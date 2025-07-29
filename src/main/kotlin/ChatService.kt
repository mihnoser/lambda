class ChatService {
    val chats = hashMapOf<List<Int>, Chat>()

    override fun toString(): String {
        var result : String = "Состояние чатов:\n"
        chats.forEach { (key, value) ->
            result += "$key $value \n"
        }
        return result
    }

    fun getMessage(userId: Int, count: Int): List<Message> {
        val chat = chats.get<Any, Chat>(userId) ?: throw NoSuchChatExeption
        return chat.messages.takeLast(count).onEach { it.read = true }
    }

    fun printChats() = println(chats)

    fun addMessage(userIds : List<Int>, message : Message) : Chat {
        return chats.getOrPut(userIds) {
            Chat()
        }.apply {
            messages.add(message)
        }
    }

    fun deleteMessage(userIds : List<Int>, message : Message) : Chat {
        return chats.getOrPut(userIds) {
            Chat()
        }.apply {
            messages.remove(message)
        }
    }

    fun getMessagesFromChat(userIds : List<Int>) : List<Message> {
        val chat = chats.filter { entry ->  entry.key.containsAll(userIds) }.values.first().messages
        chat.forEach { message -> message.read = true }
        return chat
    }

    fun readChat(userIds : List<Int>) : Boolean{
        chats.filter { entry ->  entry.key.containsAll(userIds) }.values.first().readMessages()
        return true
    }

    fun deleteChat(userIds : List<Int>) : Boolean{
        return chats.remove(userIds) != null
    }

    fun getChats(userId : Int) : List<Chat>{
        return chats.filter { entry ->  entry.key.contains(userId)}.values.toList()
    }

    fun getChat(userIds: List<Int>): List<Chat> {
        return chats.filter { entry -> entry.key.containsAll(userIds) }.values.toList()
    }

    fun getUnreadChatsCount(userId : Int) : Int {
        return chats.filter { entry ->  entry.key.contains(userId)}.values.filter { !it.unreadMessagesB() }.count()
    }

    fun getLastMessages(listOf: List<Int>): List<String> = chats.values.map { chat ->
        chat.messages.lastOrNull()?.text ?: "нет сообщений"
    }


}

object NoSuchChatExeption : Throwable() {
    private fun readResolve(): Any = NoSuchChatExeption
}