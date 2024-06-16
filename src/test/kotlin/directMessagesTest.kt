import org.junit.Test

import org.junit.Assert.*

class directMessagesTest {

    @Test
    fun add() {
        val dm = directMessages()
        val message1 = Messages(4, "Meow", true, true)
        dm.add(1, message1)

        val result = dm.getChats().size
        assertEquals(1, result)
    }

    @Test
    fun editMessage() {
        val dm = directMessages()
        val text = "Da"
        val message1 = Messages(1, "Meow", true, true)
        dm.add(1, message1)
        dm.editMessage(1, text, message1)

        val result = message1.text
        assertEquals(text, result)
    }

    @Test
    fun deleteMessage() {
        val dm = directMessages()
        val message1 = Messages(1, "Meow", true, true)
        val message2 = Messages(5, "Yes", false, false)
        dm.add(1, message1)
        dm.add(5, message2)
        dm.deleteMessage(1, 1)

        val result = dm.getChats().size
        assertEquals(2, result)
    }

    @Test
        (expected = ChatNotFoundException::class)
    fun deleteMessageEx() {
        val dm = directMessages()
        val message1 = Messages(1, "Meow", true, true)
        val message2 = Messages(5, "Yes", false, false)
        dm.add(1, message1)
        dm.add(5, message2)
        dm.deleteMessage(2, 1)
    }

    @Test
    fun deleteChat() {
        val dm = directMessages()
        val message1 = Messages(1, "Meow", true, true)
        dm.add(1, message1)

        val result = dm.deleteChat(1)
        assertEquals(true, result)
    }

    @Test
    fun getUnreadChatsCount() {
        val dm = directMessages()
        val message1 = Messages(1, "Meow", true, true)
        dm.add(1, message1)

        val result = dm.getUnreadChatsCount()
        assertEquals(0, result)
    }

    @Test
    fun getChats() {
        val dm = directMessages()
        val message1 = Messages(1, "Meow", true, true)
        dm.add(1, message1)

        val result = dm.getChats().size
        assertEquals(1, result)
    }

    @Test
    fun getMessages() {
        val dm = directMessages()
        val message1 = Messages(1, text = "Hello", true, false)
        dm.add(1, message1)
        val result = dm.getMessages(1)
        assertEquals(message1, result)
    }

    @Test
        (expected = ChatNotFoundException::class)
    fun getMessagesEx() {
        val dm = directMessages()
        val message1 = Messages(1, text = "Hello", true, false)
        dm.add(1, message1)
        dm.getMessages(5)
    }

    @Test
    fun getMessagesOnChat() {
        val dm = directMessages()
        val message1 = Messages(1, "Meow", true, true)
        val message2 = Messages(5, "Yes", false, false)
        dm.add(1, message1)
        dm.add(5, message2)
        val list = listOf(message2)
        val result = dm.getMessagesOnChat(5,1)
        assertEquals(list, result)
    }
    @Test
        (expected = ChatNotFoundException::class)
    fun getMessagesOnChatEx() {
        val dm = directMessages()
        val message1 = Messages(1, "Meow", true, true)
        val message2 = Messages(5, "Yes", false, false)
        dm.add(1, message1)
        dm.add(5, message2)
        dm.getMessagesOnChat(2,3)
    }
}