package org.jezequel.aescbc

import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.PluginRegistry
import java.util.concurrent.Executors
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

enum class Mode {
    ENCRYPT, DECRYPT
}

class Decryptor(private val mode: Mode, private val call: MethodCall, result: MethodChannel.Result) : ResultHandler(result) {

    companion object {
        @JvmStatic
        private val executor = Executors.newFixedThreadPool(5)
    }

    fun handle() {
        executor.execute handleExecutor@ {
            var processed: ByteArray?
            val key = call.argument<ByteArray>("key")
            val iv = call.argument<ByteArray>("iv")
            val data = call.argument<ByteArray>("data")
            if (data == null) {
                replyError("001", "Please provide initial data", null)
                return@handleExecutor
            }
            if (key == null) {
                replyError("001", "Please provide key", null)
                return@handleExecutor
            }
            if (iv == null) {
                replyError("001", "Please provide IV", null)
                return@handleExecutor
            }
            if (mode == Mode.DECRYPT) {
                processed = decrypt(data, key, iv)
                reply(processed)
            } else if (mode == Mode.ENCRYPT) {
                processed = encrypt(data, key, iv)
                reply(processed)
            }
        }
    }

    private val cypherInstance = "AES/CBC/PKCS7Padding"
    @Throws(Exception::class)
    fun decrypt(data: ByteArray, key: ByteArray, iv: ByteArray): ByteArray {

        val skeySpec = SecretKeySpec(key, "AES")
        val cipher = Cipher.getInstance(cypherInstance)
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, IvParameterSpec(iv/*initializationVector.getBytes()*/))
        return cipher.doFinal(data)
    }

    @Throws(Exception::class)
    fun encrypt(data: ByteArray, key: ByteArray, iv: ByteArray): ByteArray {

        val skeySpec = SecretKeySpec(key, "AES")
        val cipher = Cipher.getInstance(cypherInstance)
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, IvParameterSpec(iv/*initializationVector.getBytes()*/))
        return cipher.doFinal(data)
    }
}