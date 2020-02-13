import 'dart:async';
import 'dart:typed_data';

import 'package:flutter/services.dart';

class Aescbc {
  static const MethodChannel _channel =
      const MethodChannel('org.jezequel.aescbc');

  static Future<Uint8List> decrypt(
      Uint8List encryptedData, Uint8List key, Uint8List iv) async {
    final Uint8List decrypted =
        await _channel.invokeMethod<Uint8List>('decrypt', <String, dynamic>{
      'key': key,
      'iv': iv,
      'data': encryptedData,
    });
    ;
    return decrypted;
  }

  static Future<Uint8List> encrypt(
      Uint8List plainData, Uint8List key, Uint8List iv) async {
    final Uint8List decrypted =
        await _channel.invokeMethod<Uint8List>('encrypt', <String, dynamic>{
      'key': key,
      'iv': iv,
      'data': plainData,
    });
    ;
    return decrypted;
  }
}
