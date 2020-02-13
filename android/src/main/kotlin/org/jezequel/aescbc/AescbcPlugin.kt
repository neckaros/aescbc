package org.jezequel.aescbc

import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
/** AescbcPlugin */
public class AescbcPlugin: FlutterPlugin, MethodCallHandler {
    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        val channel = MethodChannel(flutterPluginBinding.flutterEngine.dartExecutor, "org.jezequel.aescbc")
        channel.setMethodCallHandler(AescbcPlugin());
    }
    companion object {
        @JvmStatic
        fun registerWith(registrar: Registrar) {
            val channel = MethodChannel(registrar.messenger(), "org.jezequel.aescbc")
            channel.setMethodCallHandler(AescbcPlugin())
        }
    }

    override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
        when (call.method) {
            "decrypt" -> Decryptor(Mode.DECRYPT, call, result).handle()
            "encrypt" -> Decryptor(Mode.ENCRYPT, call, result).handle()
            else -> result.notImplemented()
        }
    }



    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    }
}
