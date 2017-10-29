package kudos.utils

fun String.hashTags() = this.split(" ").filter { it.trim().startsWith("#") }