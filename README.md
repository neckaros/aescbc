# aescbc

A new flutter plugin to do decryption nativeley using AES CBC

## Getting Started

very very early version:
- Support only android
- dart code helper not yet in this repo

## usage
use PKCS7 padding
key is created with:
```
static Uint8List deriveKey(dynamic password,
      {String salt = '',
      int iterationCount = ITERATION_COUNT,
      int derivedKeyLength = KEY_SIZE}) {
    if (password == null || password.isEmpty) {
      throw new ArgumentError('password must not be empty');
    }

    if (password is String) {
      password = utf8.encode(password);
    }

    Uint8List saltBytes = base64.decode(salt);
    Pbkdf2Parameters params =
        new Pbkdf2Parameters(saltBytes, iterationCount, derivedKeyLength);
    KeyDerivator keyDerivator = new PBKDF2KeyDerivator(Mac('SHA-1/HMAC'));
    keyDerivator.init(params);

    return keyDerivator.process(password);
  }
```
using pointy castle

#decrypt
```
if (foundation.defaultTargetPlatform == TargetPlatform.android) {
      var result = Aescbc.decrypt(data, key.bytes, iv);
      return result;
    } else {
        ... decrypt in dart
```
#encrypt
```
if (foundation.defaultTargetPlatform == TargetPlatform.android) {
      var result = Aescbc.encrypt(data, key.bytes, iv);
      return result;
    } else {
        ... decrypt in dart
```