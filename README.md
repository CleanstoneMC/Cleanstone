![Cleanstone](https://i.imgur.com/A5pc55f.png)

[![Scrutinizer Code Quality](https://scrutinizer-ci.com/g/CleanstoneMC/Cleanstone/badges/quality-score.png?b=master)](https://scrutinizer-ci.com/g/CleanstoneMC/Cleanstone/?branch=master)
[![Code Coverage](https://scrutinizer-ci.com/g/CleanstoneMC/Cleanstone/badges/coverage.png?b=master)](https://scrutinizer-ci.com/g/CleanstoneMC/Cleanstone/?branch=master)
[![Build Status](https://ci.codemc.org/buildStatus/icon?job=CleanstoneMC/Cleanstone)](https://ci.codemc.org/job/CleanstoneMC/job/Cleanstone)
[![Join our Discord](https://img.shields.io/discord/429029538778054657.svg?logo=discord)](https://discord.gg/Jx5kk3u)
## What is Cleanstone?
The plan is to properly build a scalable Minecraft Server from scratch without the outdated code and single-threaded nature that Minecraft was created with.

We dont just copy the vanilla server structure and refactor it. Instead we write it completely from scratch in our own way in which we focus on doing it right instead of keeping it close and similar to Vanilla Minecraft.

Scalable: It should be expandable e.g. you can use multiple servers for a single Minecraft world and split the load over them.

Maintainable: A proper event-driven code style with dependency injection and lots of useful abstraction e.g. the protocol (built-in multi-client-version support similar to ViaVersion/ViaBackwards)

## Multi-Threading
Cleanstone is multi-threaded by design since we are using multiple thread pools that balance the load of chunk loading, IO, etc.
In the future we want to add the possibility to balance it across multiple servers as well.
This is possible because we don't have a global tick loop that synchronizes all server actions but instead we outsource the work as modular services that can work asynchronously and are therefore scalable.

## Features
### Implemented
- Multiple Network Protocols
  - Minecraft Java Edition
    - 1.12.2
    - 1.13
    - 1.13.1
    - 1.13.2
  - Minecraft Pocket Edtition
    - Basic serverlist ping support
- Async world loading and saving in LevelDB
- Online mode login
- Async chunk loading
- Breaking and placing blocks (without item drops)
- Seeing other players move around (entity tracking)
- Command system with nice sub-command and parameter abstraction
- Online admin panel to execute console commands
### TODO
- Seeing non-player entities (Missing entity metadata protocol)
- World conversion (Missing NBT Support)
- Item drops
- Special block behavior (e.g. beds)
- Spreading load over multiple servers
- Network packet encapsulation (Events for Packets)
- Chunk/Region behavior (Physics and AI)

## No global Tick Loop?
We will probably not be able to avoid a tick loop completely when we get to redstone, gravity, etc. since too many other systems directly depend on it and it would cause too many race conditions and delays to synchronize it all.
Our trick here is to divide the world into independent regions that manage their entities and work by themselves using their own worker threads and their own independent tick loops or worker servers independent of other regions.
However, tasks like pathfinding and entity movement can be completely asynchronous nonetheless since nothing else depends on them.

## API?
Currently we aren't supporting Bukkit or Sponge since we simply need more features and fundamentals first but in the future we will add support for Bukkit and probably Sponge as well, however we wont integrate it into our code but keep it separate and map it to our own API.

## Discord
We have a Discord! Join us here: https://discord.gg/Jx5kk3u

## Livedemo
Connect to "demo.cleanstone.rocks" with Minecraft 1.13.1 or 1.12.2. The Server restarts from time to time 
with the latest changes.

## Sponsors
[![YourKit](https://www.yourkit.com/images/yklogo.png)](https://www.yourkit.com/java/profiler/)

We are proudly using the YourKit Java Profiler to improve the performance and find high memory usage. Thank you for this amazing tool and we can fully recommend it to all Java developers! 

YourKit supports open source projects with its full-featured Java Profiler.
YourKit, LLC is the creator of <a href="https://www.yourkit.com/java/profiler/">YourKit Java Profiler</a>
and <a href="https://www.yourkit.com/.net/profiler/">YourKit .NET Profiler</a>,
innovative and intelligent tools for profiling Java and .NET applications.

Banner created using JetBrains' amazing jb-rpd-splash code art generator (CC BY 4.0, https://creativecommons.org/licenses/by/4.0/)
