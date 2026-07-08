<p align="center">
<img width="800" src="https://github.com/Sefiraat/SlimeTinker/blob/master/images/logo/logo.svg"><br><br>
</p>

# SlimeTinker

[![Build Status](https://Slimefun5.github.io/builds/Slimefun5/SlimeTinker/stable/badge.svg)](https://Slimefun5.github.io/builds/Slimefun5/SlimeTinker/stable)
![GitHub Downloads (all assets, all releases)](https://img.shields.io/github/downloads/Slimefun5/SlimeTinker/total)
[![GitHub Followers](https://img.shields.io/github/followers/Slimefun5?style=social)](https://github.com/Slimefun5)
[![GitHub Stars](https://img.shields.io/github/stars/Slimefun5/SlimeTinker?style=social)](https://github.com/Slimefun5/SlimeTinker)

A Tinkers' Construct-inspired addon for Slimefun that lets you craft, customize, and level modular tools and armor with unique material traits.

## Requirements
- Java 25
- Paper 1.16.* - 26.1.*
- [Slimefun 5](https://github.com/Slimefun5/Slimefun5)

## Info

For more information about SlimeTinker, please visit my <a href="https://sefiraat.dev/slimetinker/basics">documentation</a> page.

## Features include:

* The ability to melt all Vanilla and Core Slimefun metals and alloys.
* Casting out metals into nuggets, ingots and blocks for all metals.
* Casting into tools parts (Axe Heads, Sword Blades etc.) and casting into Armour Plates and Chain Mail Links.
* Creation of custom tools based on any combination of Head, Binding and Rod parts for a unique tool
* Creation of custom armour peices based on any combination of Plates, Gambeson and Mail Link parts for a unique armour
  peice
* Each Part/metal combination has a specific trait. Traits range from Auto smelting to Levitation, invisibilty to 3x3
  mining.
* Tools and Armour peices level as you use them, as they level they unlock new Modifier slots and can promote from
  Stone > Gold > Iron > Diamond and Netherite or Leather > Chain > Iron > Diamond > Netherite.
* Modifier slots can be used to add special modifiers to the tool from bonus drops (on top of looting/fortune) to more
  haste, damage and more.
* Tools and armour do not break, they just stop working until you repair them using the same metal type the tool is made
  of. Armour will fall off when in need of repair
* Parts can be swapped out at will leaving the tool/armour peices level/mods intact. Want to start with Copper to level
  faster THEN swap to Reinforced for 3x3? Go for it!
* All effects/mods (when applicable) work with Vanilla Enchants. Damage, exp and durability modifications are all
  stackable and work nicely with each other.
* Configurable traits. You can disable any trait you don't want in the configuration file.

For a more detailed look, check out the video from Boomer_1 who runs through the basics of Tinker!
[![Boomer shows off SlimeTinker](https://res.cloudinary.com/marcomontalbano/image/upload/v1626509062/video_to_markdown/images/youtube--gAUoxj-h26s-c05b58ac6eb4c4700831b2b3070cd403.jpg)](https://youtu.be/gAUoxj-h26s "Boomer shows off SlimeTinker")

## Planned Features include:

* Addition of molten metal types from even more addons!
* Additional tiers of smeltery that will allow for further automation.

## Configuration

SlimeTinker now supports disabling traits. All the traits are enabled by default. To disable a trait, open the file `/plugins/SlimeTinker/traits.yml`, disable the part under a specific material.

## Suggestions?

SlimeTinker has a LOT of properties and effects and I simply do not have enough ideas to flesh out all of it. So I
really would welcome any and all suggestions in the Slimefun Addon Community Discord (be sure to stick to the
<<<<<<< HEAD
slimetinker channel!). If you have ideas, please use this [invite](https://discord.gg/J4KurMDCKU)
=======
slimetinker channel!). If you have ideas, please use this [invite](https://discord.gg/CbBYZBEWdR)
>>>>>>> origin/experimental

## Credits

Big thanks to [Riley](https://github.com/Mooy1) for the great InfinityLib which sped things up greatly. Another big
thanks to [Martin](https://github.com/martinbrom) who helped us out with ideas and direction. Thanks
to [Boomer](https://www.youtube.com/channel/UC2ZmER18YBRYube-62-JVpA) for testing, feeding back and breaking things for
me!

## Thanks!

A big thanks to the owners of **mct.tantrum.org** who have tested nearly everything I have made and really given me direction and drive to make these things and was the first server willing to take the buggy mess that Tinker was, at first, onto their server!

A big shout-out to GentlemanCheesy who puts a lot of time and effort into making skull textures for me across many of my addons. Without these they would be far blander and certainly not be half as attractive as they are!

## Developer API

You can easily depend on this project using [github-gradle](https://github.com/intisy/github-gradle).

In your `build.gradle.kts`:

```kotlin
plugins {
    id("io.github.intisy.github-gradle") version "1.8.2.1"
}

dependencies {
    "githubCompileOnly"("Slimefun5:SlimeTinker:v1.1.3.2.5")
}
```

## Wiki

[Read more on the Slimefun Wiki...](https://github.com/Slimefun5/Wiki/wiki/SlimeTinker)

## Discord

You can find Slimefun's community on Discord! Click the badge below to join the server for suggestions/questions or other discussions about this plugin.

<p align="center">
  <a href="https://discord.gg/CbBYZBEWdR">
    <img src="https://discordapp.com/api/guilds/738626600539160576/widget.png?style=banner2" alt="Discord"/>
  </a>
</p>

## License

This project is open-source and licensed under the MIT License.
