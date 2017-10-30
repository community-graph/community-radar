package radar.kudos.utils

fun String.hashTags() = this.split(" ").filter { it.trim().startsWith("#") }
