# Changelog

All notable changes to this project will be documented in this file. See [conventional commits](https://www.conventionalcommits.org/) for commit guidelines.

## [j-antplus-0.2.0] - 2026-11-18

### 🛠️ Build [0.2.0]

- Generate and commit `CHANGELOG.md` during `release:prepare` goal ([b8521ad](http://github.com/heyteacher/j-antplus/commit/b8521ad5826afa18ea7c5c93e57fb04d35acc575)) by **heyteacher**

### 📚 Documentation [0.2.0]

- Update `README` ([48aaf87](http://github.com/heyteacher/j-antplus/commit/48aaf87fdfbe477ab36f87a0011db571e6463d96)) by **heyteacher**
- Add missing javadoc ([996844b](http://github.com/heyteacher/j-antplus/commit/996844bf1875a3032a3f8c3515eec7b19628175e)) by **heyteacher**

### ⚙️ Miscellaneous Tasks [0.2.0]

- Corrected typo and removed unused imports ([cafbec7](http://github.com/heyteacher/j-antplus/commit/cafbec7160bb4d75a007d97f4a6b63bfb03ec7ca)) by **heyteacher**

## [j-antplus-0.0.1] - 2026-11-17

### 🚀 Features [0.0.1]

- Add sub-page number management in `DataPage70Request` ([ea92656](http://github.com/heyteacher/j-antplus/commit/ea9265611fad64ec8086ce3fbfb50d96fb1c1bf6)) by **heyteacher**
- Add page 0x02 Get Set Parameters and sub pages 0x01 crank parameters and 0xFE (254) Advanced Capabilities 2 for Power Bike profile ([65dc539](http://github.com/heyteacher/j-antplus/commit/65dc539f2043671df95374e237d27d1c55159054)) by **heyteacher**
- Support android devices via `android.hardware.usb` API ([9f6bac6](http://github.com/heyteacher/j-antplus/commit/9f6bac60d2406ba6a1591a914c316cbab9fd332a)) by **heyteacher**
- Add ANT+ device `getSerialName` and `getName`  from Product Information Page (`0X52`) ([47182ad](http://github.com/heyteacher/j-antplus/commit/47182adea6d59c11d4009077a1cbc75bcfe17a4e)) by **heyteacher**

### 🐛 Bug Fixes [0.0.1]

- Manage capabilities response message of 6 bytes without advanced options ([6891a9b](http://github.com/heyteacher/j-antplus/commit/6891a9b1c1556d14d5d529c3ad6d6621b4a8dda5)) by **heyteacher**
- Rename `AchnowledgeDataMessage` in `AcknowledgeDataMessage` ([2ca0790](http://github.com/heyteacher/j-antplus/commit/2ca07900c5c8767c519a2b98969237f2a2a79052)) by **heyteacher**
- Make `getChannelNumber` public ([adc062e](http://github.com/heyteacher/j-antplus/commit/adc062e481374e0b23f60d0b1c0b2a85ad7fdd2c)) by **heyteacher**
- Make `dataPageBytes` final ([a23cf42](http://github.com/heyteacher/j-antplus/commit/a23cf42b3fdd9179d21f1afe19cdc42507614538)) by **heyteacher**
- Support language level 24 (without flexible constructors) ([b7c6f39](http://github.com/heyteacher/j-antplus/commit/b7c6f39666f7b0191552de1a57d65296f9e057a1)) by **heyteacher**
- Release android usb interface on close + code review ([f9c8268](http://github.com/heyteacher/j-antplus/commit/f9c8268cc53cde54d9be42e71408590990d8ca3a)) by **heyteacher**
- `closeChannel` send a close channel message to open channel before send unassign message and make public `closeChannel(byte channelNumber)` ([bbd8090](http://github.com/heyteacher/j-antplus/commit/bbd80908f0e50cebbb2285fdbdb837420eb4154a)) by **heyteacher**
- Add speed and cadence pages to registry ([e2cb676](http://github.com/heyteacher/j-antplus/commit/e2cb676d23ac0c66868302ecc2c37a51b350700d)) by **heyteacher**
- Uniform `deviceNumber` and `name` implementation between Power Bike, HRM and Speed and Cadence ([a5015c5](http://github.com/heyteacher/j-antplus/commit/a5015c52b0d5431907c92c307a3a16c659bfd929)) by **heyteacher**
- On close channel send unassign message even if close message throw exception + log enhancement ([36d5ebd](http://github.com/heyteacher/j-antplus/commit/36d5ebd19cf99b5c2110da591e1a82f07486d945)) by **heyteacher**
- Rewrite `AntMessageRegistry` with `Constructor Reference` replacing Java Reflection `Class.getCostructor` which fails with (Android optimization)[https://developer.android.com/topic/performance/app-optimization/keep-rules-overview] ([58a4ac2](http://github.com/heyteacher/j-antplus/commit/58a4ac2aad6cc3fd214bcf454ab20f1558270b8b)) by **heyteacher**
- Add `androidLogger` to android `AntUsbDevice.initialize` and in `Log` forward log to android logger if set. ([284c89b](http://github.com/heyteacher/j-antplus/commit/284c89bc07d4252f4cc7057309c03648b1799850)) by **heyteacher**

### 🛠️ Build [0.0.1]

- Set Android Studio jar dependencies,upgrade gradle and pom java version to 22 ([13af09f](http://github.com/heyteacher/j-antplus/commit/13af09f787dc9adebc0df0d7497096ab4b2311fe)) by **heyteacher**
- Add profile android based on system property `android` for android builds + set source and target to sdk 24 ([0fc2c3a](http://github.com/heyteacher/j-antplus/commit/0fc2c3a5bbb4046c8a406a9554df0e6a16d9beac)) by **heyteacher**
- Add release plugin and satisfy maven central requirements ([15d2a3b](http://github.com/heyteacher/j-antplus/commit/15d2a3b86128f4a76153c78b4f5a2f9945a65b74)) by **heyteacher**

### 💼 Other [0.0.1]

- Implement toString for logging ([c6bb01f](http://github.com/heyteacher/j-antplus/commit/c6bb01f3e55804623db90d12348dd2129d104e6c)) by **Glen Vermeylen**
- ChannelEventOrResponseCode.EVENT_RX_FAIL should have been 0x02 ([96727e5](http://github.com/heyteacher/j-antplus/commit/96727e5a91439ed90d0720c1b0f4bf0f564a7348)) by **Glen Vermeylen**
- Mention HrmTest_Main as starting point ([4fc05dc](http://github.com/heyteacher/j-antplus/commit/4fc05dcfe1ca5731cfee26c514c9ed7e22ddf024)) by **Glen Vermeylen**
- Describe support of new devices ([507c86a](http://github.com/heyteacher/j-antplus/commit/507c86a54346df98d947b516b9d96e7286bcda2e)) by **Daniel Schaefer**
- Add isInitialized property ([2986f7a](http://github.com/heyteacher/j-antplus/commit/2986f7a043c8ccf578d8e0fd5e4e208e44e9a08b)) by **Daniel Schaefer**
- Reuse same AntUsbDevice ([63bc201](http://github.com/heyteacher/j-antplus/commit/63bc201c9eedb7d1707aef6c1d6abde7f0666efe)) by **Daniel Schaefer**

### 🧪 Testing [0.0.1]

- Handle page 0x00 Default and get computed Heart Rate in bpm ([209626e](http://github.com/heyteacher/j-antplus/commit/209626e34794607c5467f0079c25dc723525dbe1)) by **heyteacher**
- Handle all power bike pages included Get/Set Parameters page and Battery Status +  send ACK message for request parameters and set crank lenght (commented) ([f246009](http://github.com/heyteacher/j-antplus/commit/f24600983ea4dbecf9eac446e0c76b1285d91475)) by **heyteacher**

### ⚙️ Miscellaneous Tasks [0.0.1]

- Set INFO log level USB reader, USB writer, USB  device and Message Registry ([ed84a34](http://github.com/heyteacher/j-antplus/commit/ed84a34e529e8fb9a16e464e5c5d7b0bf9c366bb)) by **heyteacher**
- Reorganize logger instructions ([733e29d](http://github.com/heyteacher/j-antplus/commit/733e29dccf52479e15889548251bb0c86a39773c)) by **heyteacher**
- Trim buffer in log message ([fdb0505](http://github.com/heyteacher/j-antplus/commit/fdb05059e5ebdffd7a32570329c1441947208bb2)) by **heyteacher**
- Resolve warning ([14e5bd4](http://github.com/heyteacher/j-antplus/commit/14e5bd443393b3cc8c6462e6df24af2007fee506)) by **heyteacher**
- Corrected typo ([18e9f6f](http://github.com/heyteacher/j-antplus/commit/18e9f6fc4ba1e273f9169ff19a56faf06d9f961d)) by **heyteacher**
- Log only 15 bytes read ([e1b54c8](http://github.com/heyteacher/j-antplus/commit/e1b54c81fe90fb29c572dcd9abe3fd6d9791719a)) by **heyteacher**
- Corrected typo ([4324a78](http://github.com/heyteacher/j-antplus/commit/4324a780f0e267f7b6700ef0a2365700a14eb056)) by **heyteacher**
- Log page recognized from ant message received from sensor ([0391eb3](http://github.com/heyteacher/j-antplus/commit/0391eb35300cc9929e66f21438fb186dd31122b0)) by **heyteacher**
- Warning corrected ([20a31a3](http://github.com/heyteacher/j-antplus/commit/20a31a3a4c88d37269afcd9e940a6591f77df709)) by **heyteacher**
