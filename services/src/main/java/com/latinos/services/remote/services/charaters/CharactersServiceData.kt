package com.latinos.services.remote.services.charaters

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class CharactersServiceData {
    //TODO: refactor a un interceptor¿?
    companion object {
        val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val API_KEY = "7067eef13865fb719522b26487b3d68e"
        const val PRIVATE_KEY = "6bad476e124cd76e5e96f50c684f328862825d60"
        const val limit = "50"
        fun hash(): String {
            val input = "$timeStamp$PRIVATE_KEY$API_KEY"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
        }
    }
}