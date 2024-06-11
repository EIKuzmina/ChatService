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
        chats.getOrPut(id) { Chat() }.message += message
    }

    fun editMessage(id: Int, text: String, messages: Messages) {
        if (id == messages.id) {
            messages.text = text
        }
    }

    fun deleteMessage(id: Int, idChat: Int) {
        chats[id]?.message?.find { it.id == idChat }?.also {
            it.delete = true
            if ((chats[id]?.message?.count
                { message: Messages -> !message.delete } ?: 0) == 0
            ) {
                deleteChat(id)
            }
        }
    }

    fun deleteChat(idChat: Int): Boolean {
        chats.remove(idChat)
        return true
    }

    fun getUnreadChatsCount() =
        chats.values.count { chat -> chat.message.any { !it.read } }

    fun getChats(): Map<Int, Chat> {
        return chats
    }

    fun getMessages(id: Int) =
        chats[id]?.message?.lastOrNull { !it.delete } ?: throw ChatNotFoundException("Нет сообщений")

    fun getMessagesOnChat(idUsers: Int, count: Int) =
        chats[idUsers]?.message?.filter { it.id >= idUsers && !it.delete }
            ?.take(count)?.onEach { it.read = true }
}