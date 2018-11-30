package org.protokruft

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.squareup.kotlinpoet.FileSpec
import java.io.StringWriter

fun Any.check(i: Int, list: List<FileSpec>, root: String) {
    val w = StringWriter()
    list[i - 1].writeTo(w)
    assertThat(w.toString(), equalTo(contentOfResource(javaClass, "/$root$i.ktt")))
}

private fun contentOfResource(owner: Class<*>, r: String): String {
    return owner.getResourceAsStream(r)?.let { stream ->
        stream.reader().use {
            it.readText()
        }
    } ?: throw IllegalArgumentException("Unable to locate $r in relation to ${owner.name}")
}