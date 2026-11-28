# j-antplus

Java Library for speaking with ANT / ANT+ devices through a USB dongle.

> [!NOTE]
> This is a fork of [glever/j-antplus](https://github.com/glever/j-antplus) that support **Android Platform** and extend support of **Bicycle Power Bike** profile. This library is used by [flutter_antplus](https://codeberg.org/heyteacher/flutter_antplus)

## Table of Contents
<!-- TOC -->

- [j-antplus](#j-antplus)
  - [Table of Contents](#table-of-contents)
  - [Features](#features)
    - [Bicycle Power profile](#bicycle-power-profile)
    - [Heart Rate profile](#heart-rate-profile)
    - [Bicycle Speed and Cadence profile](#bicycle-speed-and-cadence-profile)
    - [Fitness Equipment Control (FE-C) profile](#fitness-equipment-control-fe-c-profile)
  - [Installing](#installing)
  - [Compatibility notes](#compatibility-notes)
    - [Android](#android)
    - [Linux](#linux)
    - [Windows](#windows)
  - [Current status](#current-status)
  - [For Development](#for-development)
    - [Publish on maven central](#publish-on-maven-central)
      - [Configure GPG](#configure-gpg)

<!-- /TOC -->

## Features

I set up this project trying to talk to my personal ANT+ devices.
Goal is to implement needed ant messages and ANT+ datapages in order to talk to following set of devices:

### **Bicycle Power profile**

Tested with **Garmin Vector 3** and **Favero Assioma Duo** . Check out `PowerTest_Main` under `src/test`

Data pages tested:

- **PowerDataPage10PowerOnly** (`0x10`)
  - *Instantaneous Power* Watts
  - *Instantaneous Cadence* RPM
  - *Pedal Power Distribution* %

- **PowerDataPage13TorqueEffectivenessPedalSmoothness** (`0x13`)
  - *Left Torque Effectiveness* %
    - *Left Pedal Smoothness* %
    - *Right Pedal Smoothness* %
    - *Combined Pedal Smoothness* enabled

- **PowerDataSubPage01CrankParameters** (`0x01`)
  > [!IMPORTANT] received after sending `DataPage70Request` (`0x46`) page `0x02` sub page `0x01`
  - *Crank Length* mm

- **PowerDataSubPage254AdvancedCapabilities2** (`0xFE`)
  > [!IMPORTANT] received after sending `DataPage70Request` (`0x46`) page `0x02` sub age `0xFE`
  - *4 Hz Transmission* enabled / supported
  - *8 Hz Transmission* enabled / supported
  - *Power Phase* enabled / supported
  - *PCO* enabled / supported
  - *Rider Position* enabled / supported
  - *Torque Barycenter* enabled / supported

- **DataPage82BatteryStatus**
  - *Battery Status*: `NEW`, `GOOD`, `OK`, `LOW`, `CRITICAL`, `INVALID`

### **Heart Rate profile**

Tested with **Garmin HRM3SS**, **Polar H9**, **GEOID HS500**, **Decathlon HMR BELT** and **Magene H64**. Check out `HrmTest_Main` under `src/test`

Data pages tested:

- **HrmDataPage0Default** (`0x00`)
  - *Computed Heart Rate* BPM

- **HrmDataPage7BatteryStatus** (`0x07`)
  - *Battery Status*: `NEW`, `GOOD`, `OK`, `LOW`, `CRITICAL`, `INVALID`

> [!WARNING]
> No support for legacy format (yet, because I don't own a legacy device). If you need it, check out HrmChannel to sniff the datapage 1st bit alternating every 4 packages.

### **Bicycle Speed and Cadence profile**

- **Cadence sensor**
  Tested with **Garmin Cadence Sensor** and **CYCPLUS C3 Mini**.
  The sensor only measures the number of crank revolutions, the actual cadence has to be calculated by a consumer of this library from two different measurements.
  Check out `CadenceTest_Main` under `src/test`

  Data Page testes:

  - **SpeedCadenceDataPage0Default** (`0x00`)
    - *Instantaneous Cadence* RPM calculated from `cumulativeRevolutions` and `eventTime`
  
  - **SpeedCadenceDataPage4BatteryStatus** (`0x04`)
    - *Battery Status*: `NEW`, `GOOD`, `OK`, `LOW`, `CRITICAL`, `INVALID`

- **Speed sensor**
  - Basic support. Tested with **Garmin Speed Sensor**.
  - The sensor only measures the number of wheel revolutions, the actual speed has to be calculated by a consumer of this library in conjunction with the wheel's diameter.
  Check out `SpeedTest_Main` under `src/test`

- **Combined speed and cadence sensor**
  - Basic support. Not tested with a physical device yet. Check out `SpeedAndCadenceTest_Main` under `src/test`

### **Fitness Equipment Control (FE-C) profile**

- Basic support. Tested with **Elite Suito** and **Tacx**. Check out `FecTest_Main` under `src/test`
- Not all data pages implemented, yet.
- TODO: Send command to adjust resistance

## Installing

- Gradle

  ```kotlin
  implementation("io.github.heyteacher:j-antplus:$1")
  ```

- Maven

  ```xml
  <dependency>
      <groupId>io.github.heyteacher</groupId>
      <artifactId>j-antplus</artifactId>
      <version>$1</version>
  </dependency>
  ```

## Compatibility notes

### Android

It uses built-in `android.hardware.usb` instead of `usb4java-javax`

See [flutter_antplus](https://codeberg.org/heyteacher/flutter_antplus) example for configure android application

### Linux

Tested on `Ubuntu`: you need to give access to the usb interface for current user.
I followed this approach (which may give too wide permissions to your taste): <https://askubuntu.com/a/930338>  

```bash
echo 'SUBSYSTEM=="usb", MODE="0660", GROUP="plugdev"' > /etc/udev/rules.d/00-usb-permissions.rules
udevadm control --reload-rules
```

update: on `Manjaro`, above didn't work (permission denied on the file), but this works

```bash
echo 'ATTRS{idVendor}=="fcf", ATTRS{idProduct}=="1009", MODE="0666"' > /etc/udev/rules.d/99-my-user-permissions.rules
udevadm control --reload-rules
```

After this, unplug and re-plug the dongle.

### Windows

This project uses `usb4java-javax`, which underneath uses `libusb`.
There seems to be a mismatch between the default `windows10 WHQL` driver (`libusb 1.2.40.201`, provided by dynastream) and `libusb`.
I resolved this by using the "Zadig" tool, select the ant-m usb stick (you may need to go to "Options" -> "list all devices")
and *downgrade* the driver to 1.2.6.0 (which is actually the latest `libusb` driver I could find on the net).

## Current status

Codebase still unstable but foundation is shaping up. Expect a few heavy refactors until things stabilize.
Check out the Hrm/SpeedTest/Cadence/Fec examples to get started. Many thanks to <https://github.com/JohnAZoidberg> for help with implementing most of the devices.

## For Development

- [Set up the Android 14 SDK](https://developer.android.com/about/versions/14/setup-sdk) and
  set the environment variable `ANDROID_HOME`

### Publish on maven central

- install [GitHub CLI](https://github.com/cli/cli#installation) (`gh` command)

- install [git-cliff](https://git-cliff.org/docs/installation/) for update `CHANGELOG.md` on release a new version

- [Register to Publish Via the Central Portal](https://central.sonatype.org/register/central-portal/)

  Logging with the `GitHub` account automatically is created a namespace `io.github.<user>`

- Setup `pom.xml` with your information (`groupId`, `url`, `developer`, `scm` )

- [configure GPG](#configure-gpg)

- [Generating a Portal Token for Publishing](https://central.sonatype.org/publish/generate-portal-token/)

  and set up your `~/.m2/settings.xml`  

- [Connect your GitHub account via SSH](https://docs.github.com/en/authentication/connecting-to-github-with-ssh)

- run

  ```ssh
  maven release:prepare release:perform
  ```

- go [Publishing Settings](https://central.sonatype.com/publishing) and click `Publish`

  After 10 minutes, the package is published

#### Configure GPG

You need to configure Gpg used by maven to publish on maven central.

- generate a new key pair without password and set as email address
  the same of `Maven Central` account (i.e <heyteacher70@gmail.com>)
  
  ```bash
  gpg --gen-key
  ```

- show the keys and check latest created

  ```bash
  gpg --list-keys
  ```

- upload the key to `keys.openpgp.org`

  ```bash
  gpg --keyserver  keys.openpgp.org --send-keys <UID>
  ```

- click the confirmation link you'll receive via email and follow the instruction for link your
  email to gpg public key uploaded
