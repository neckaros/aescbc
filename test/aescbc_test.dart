import 'dart:typed_data';

import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:aescbc/aescbc.dart';

void main() {
  const MethodChannel channel = MethodChannel('org.jezequel.aescbc');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return Uint8List(16);
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('decrypt', () async {
    expect(await Aescbc.decrypt(Uint8List(16), Uint8List(16), Uint8List(16)),
        Uint8List(16));
  });
}
