# CMBI

CMBI is a Minecraft plugin designed for Paper 1.21.11 servers. It introduces a unique command, `cmbi:random`, which can only be executed from impulse command blocks. The plugin automatically recognizes command blocks containing this command, protecting them from breaking, editing, and piston movement. It ensures recognition persists across server restarts. When activated by any redstone source, the command block executes once per rising edge, requiring redstone to return to an unpowered state before another activation. The plugin identifies the responsible player where possible, or assigns the outcome to the nearest online player in the same world if identification fails. It features a modular random engine with equal probability for each registered outcome, making it easy to add future outcomes without rewriting the engine. The initial outcome is the GIVE command, which selects from every vanilla item and block, with specific quantities for certain items.

## Install

1. Download the plugin JAR file from the [releases page](https://github.com/yourusername/CMBI/releases).
2. Place the JAR file in the `plugins` folder of your Paper 1.21.11 server.
3. Restart or reload your server to load the plugin.

## Use

1. Place an impulse command block in your Minecraft world.
2. Edit the command block to contain the command `cmbi:random`.
3. Connect the command block to a redstone source.
4. When the redstone source activates, the command block will execute the `cmbi:random` command, giving a random item or block to the nearest player in the same world.

---

More Minecraft plugin projects by EnderDevelopment: https://github.com/EnderDevelopment

Project page: https://dash.enderdevelopment.com/community/deb19b73-d232-46a6-bcdc-7b8abf494bbc?utm_source=github&utm_medium=readme&utm_campaign=auto_publish&utm_content=deb19b73-d232-46a6-bcdc-7b8abf494bbc
