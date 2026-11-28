# Changelog

All notable changes to this project will be documented in this file. See [conventional commits](https://www.conventionalcommits.org/) for commit guidelines.

## [j-antplus-0.4.1] - 2026-11-28

### 🛠️ Build [0.4.1]

- Prepare for next development iteration ([25ad989](http://github.com/heyteacher/j-antplus/commit/25ad989d9ebd128414e68883886758fa955dde06)) by **heyteacher**
- Add script `tool/release.sh` which run maven release prepare and perform ([8882c19](http://github.com/heyteacher/j-antplus/commit/8882c1908cd47d8a33f40fdaf7a4905796d08d17)) by **heyteacher**
- Add GitHub release automation script in perform release goal ([4a127f5](http://github.com/heyteacher/j-antplus/commit/4a127f5075780302ed060981792765dd1b4d2c14)) by **heyteacher**

### 📚 Documentation [0.4.1]

- Enhanced `README.md` adding TOC, formatting markdown and extending features ([7db806e](http://github.com/heyteacher/j-antplus/commit/7db806e83f992521e96aa0d415751fc9a67b6660)) by **heyteacher**

## [j-antplus-0.4.0] - 2026-11-26

### 🚀 Features [0.4.0]

- Add constructor to BroadcastDataMessage for payload transmission ([262bcd0](http://github.com/heyteacher/j-antplus/commit/262bcd0a8b16c283080f7fddb2ef3d17e515808f)) by **heyteacher**

### 🛠️ Build [0.4.0]

- Prepare for next development iteration ([4e33a53](http://github.com/heyteacher/j-antplus/commit/4e33a5383c8ebab607da7a2d6b7cc5502ab7b36a)) by **heyteacher**
- Prepare release j-antplus-0.4.0 ([9946c0f](http://github.com/heyteacher/j-antplus/commit/9946c0f6fe34adc4e4b838c7f9a0f0723d6dbeb4)) by **heyteacher**

### 🚜 Refactor [0.4.0]

- Add debug logging and improve handling in AndroidAntUsbDevice ([20f28ec](http://github.com/heyteacher/j-antplus/commit/20f28ec11ef23a979c18c0b87bb6c9c658c5b324)) by **heyteacher**
- Replace `DataPageBuilder` with constructor function in AbstractDataPageRegistry ([28873fa](http://github.com/heyteacher/j-antplus/commit/28873fa256cccef35f9e3b113728f297e986326a)) by **heyteacher**

### 📚 Documentation [0.4.0]

- Update CHANGELOG ([50ba842](http://github.com/heyteacher/j-antplus/commit/50ba8427ac85719a6b6aeb186e8cb1f911366fd7)) by **heyteacher**

### 🎨 Styling [0.4.0]

- Format code and update javadoc comments ([1e45749](http://github.com/heyteacher/j-antplus/commit/1e45749a8b9315f7147ea5099edb9904d9768f9d)) by **heyteacher**

### ⚙️ Miscellaneous Tasks [0.4.0]

- Remove intellij configuration file ([a724e2f](http://github.com/heyteacher/j-antplus/commit/a724e2f83ab00bc4ed71e8b27ef44dface2ba084)) by **heyteacher**
- Enable `autoPublish` for central publishing plugin and format pom.xml ([b3dd00f](http://github.com/heyteacher/j-antplus/commit/b3dd00f5fb964e2ddaa802be20786eaffbb5eeb1)) by **heyteacher**

## [j-antplus-0.3.1] - 2026-11-20

### 🛠️ Build [0.3.1]

- Prefix `build:` to commits in order to be compliant with conventional commits ([922cde7](http://github.com/heyteacher/j-antplus/commit/922cde7c0eba81157a4b72734eb756e5de8168a7)) by **heyteacher**
- Prepare release j-antplus-0.3.1 ([643ea1d](http://github.com/heyteacher/j-antplus/commit/643ea1d4e4320197fd85091a50f95161e53e99f1)) by **heyteacher**

### 🚜 Refactor [0.3.1]

- In `*Channel` constructors, if `deviceNumber` is null set to `DEVICE_NUMBER_WILDCARD` and upgrade pom dependencies ([583083c](http://github.com/heyteacher/j-antplus/commit/583083c45c3bf9a881ab6aafcb36068b27d78f4e)) by **heyteacher**

### 📚 Documentation [0.3.1]

- Update CHANGELOG ([e874e3b](http://github.com/heyteacher/j-antplus/commit/e874e3b7e645471d84917c2c97b2971f904d1b31)) by **heyteacher**
- Update CHANGELOG ([1936a76](http://github.com/heyteacher/j-antplus/commit/1936a762bf346d123780352c9eeff0be32149d5f)) by **heyteacher**

## [j-antplus-0.3.0] - 2026-11-19

### 🛠️ Build [0.3.0]

- Warning corrected adding encoding,  release compiler and replacing `scm.tag` with `project.scm.tag` ([c9fd892](http://github.com/heyteacher/j-antplus/commit/c9fd8923db38fab3b79447689005fec066c7e003)) by **heyteacher**

### 🚜 Refactor [0.3.0]

- [**breaking**] Change `deviceNumber` type from `byte[]` to `Long` in constructor and store it in `AntChannel` adding getter and setter ([1dc6a9d](http://github.com/heyteacher/j-antplus/commit/1dc6a9d6bd83715f1ac006ca45346858b1c11262)) by **heyteacher**

### 📚 Documentation [0.3.0]

- Update CHANGELOG ([4c0a9ea](http://github.com/heyteacher/j-antplus/commit/4c0a9ea0508a869e78bb9b82e287fd2dd7d60cf8)) by **heyteacher**

## [j-antplus-0.2.0] - 2026-11-18

### 🛠️ Build [0.2.0]

- Generate and commit `CHANGELOG.md` during `release:prepare` goal ([b8521ad](http://github.com/heyteacher/j-antplus/commit/b8521ad5826afa18ea7c5c93e57fb04d35acc575)) by **heyteacher**

### 📚 Documentation [0.2.0]

- Update `README` ([48aaf87](http://github.com/heyteacher/j-antplus/commit/48aaf87fdfbe477ab36f87a0011db571e6463d96)) by **heyteacher**
- Add missing javadoc ([996844b](http://github.com/heyteacher/j-antplus/commit/996844bf1875a3032a3f8c3515eec7b19628175e)) by **heyteacher**
- Update CHANGELOG ([2b5e7fe](http://github.com/heyteacher/j-antplus/commit/2b5e7fe02cdb4d4bb762946d1b94ed6a00c3652c)) by **heyteacher**

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
