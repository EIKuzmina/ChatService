data class Messages(
    val id: Int, var text: String, var read: Boolean, var delete: Boolean,
)

data class Chat(
    var message: List<Messages> = mutableListOf()
)

class ChatNotFoundException(message: String) : RuntimeException(message)

class directMessages {
    private var chats = mutableMapOf<Int, Chat>()

    fun add(id: Int, message: Messages) {
        chats.getOrPut(id)
        { Chat() }.message += message
    }

    fun editMessage(id: Int, text: String, messages: Messages) {
        chats.asSequence()
            .filter { id == messages.id }
            .let { messages.text = text }
    }

    fun deleteMessage(id: Int, idChat: Int) {
        chats[id]
            .let { it?.message ?: throw ChatNotFoundException("Нет сообщений") }
            .asSequence()
            .take(idChat)
            .onEach { it.delete = true }
    }

    fun deleteChat(idChat: Int): Boolean {
        chats.remove(idChat)
        return true
    }

    fun getUnreadChatsCount() =
        chats.values
            .count {
                it.message
                    .any { !it.read && !it.delete }
            }

    fun getChats(): Map<Int, Chat> {
        return chats
    }

    fun getMessages(id: Int) =
        chats[id]?.message?.lastOrNull { !it.delete } ?: throw ChatNotFoundException("Нет сообщений")

    fun getMessagesOnChat(idUsers: Int, count: Int): List<Messages> =
        chats[idUsers]
            .let { it?.message ?: throw ChatNotFoundException("Нет сообщений") }
            .asSequence()
            .filter { it.id >= idUsers && !it.delete }
            .take(count)
            .onEach { it.read = true }
            .toList()
}