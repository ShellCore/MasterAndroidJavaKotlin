package com.shell.android.minitwitter.extensions

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shell.android.minitwitter.rest.services.tweets.response.Tweet
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.StringWriter

fun getTweetsFromJsonRawFile(context : Context, rawSource: Int): List<Tweet>? {
    val json = getTweetsJsonFromRaw(context, rawSource)
    return if (json.isNotEmpty()) {
        val type = object : TypeToken<List<Tweet>>() {}.type
        Gson().fromJson(json, type)
    } else {
        null
    }
}

fun getTweetsJsonFromRaw(context : Context, rawResource : Int): String {
    val iStream = context.resources.openRawResource(rawResource)
    val writer = StringWriter()
    val buffer = CharArray(DEFAULT_BUFFER_SIZE)
    iStream.use { inputStream ->
        val reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
        var n : Int
        do {
            n = reader.read(buffer)
            if (n == -1)
                break
            writer.write(buffer, 0, n)
        } while (true)
    }

    return writer.toString()
}