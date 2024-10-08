# Sculk-Server [![Latest release](https://img.shields.io/github/release/RealBauHD/sculk-server.svg)](https://github.com/RealBauHD/sculk-server/releases/latest) [![Build Status](https://img.shields.io/github/actions/workflow/status/RealBauHD/sculk-server/gradle.yml?branch=master)](https://github.com/RealBauHD/sculk-server/actions) [![Discord](https://img.shields.io/discord/1104864435060879440.svg?logo=discord&label=)](https://discord.gg/Gmxwzz2rA9)

Minecraft-Server implementation that aims to be efficient and have a good API.

This software is not ready for production use!

## Benefits

- no overhead from unused vanilla features
- more stable and modern api without the use of NMS

## Disadvantages

- no vanilla features (when you need them, but you can implement things yourself)
- no support for Bukkit plugins

## For Developers
##### Gradle
```kotlin
repositories {
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
    maven("https://libraries.minecraft.net")
}

dependencies {
    compileOnly("eu.sculkpowered.server:api:1.0.0-SNAPSHOT")
    annotationProcessor("eu.sculkpowered.server:api:1.0.0-SNAPSHOT")
}
```
##### Example Plugins
- https://github.com/SculkPowered/example-plugin
- https://github.com/SculkPowered/flux
- https://github.com/SculkPowered/LuckPerms