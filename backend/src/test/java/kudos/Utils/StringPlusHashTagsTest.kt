package kudos.Utils

import kudos.utils.hashTags
import org.junit.Test
import org.junit.Assert.*

class StringPlusHashTagsTest {

    @Test
    fun shouldExtractHashTags() {
        val text = "Hosting the Local Google Suncoast meetup and discussing #Kotlin w/@traversoft https://t.co/XM5erRNn5z\""
        val tags = text.hashTags()
        assertEquals(1, tags.size)
        assertEquals("#Kotlin", tags[0])
    }
}