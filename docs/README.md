# Console Broadcast

[![PaperMC](https://img.shields.io/badge/PaperMC-Plugin-lightgrey?style=for-the-badge)](https://papermc.io)
[![GitHub license](https://img.shields.io/github/license/jimmyl0l3c/paper-ConsoleBroadcast?style=for-the-badge)](https://github.com/jimmyl0l3c/paper-ConsoleBroadcast/blob/main/LICENSE)

[![GitHub release](https://img.shields.io/github/v/release/jimmyl0l3c/paper-ConsoleBroadcast?style=for-the-badge)](https://github.com/jimmyl0l3c/paper-ConsoleBroadcast/releases/)
[![GitHub release](https://img.shields.io/github/release-date/jimmyl0l3c/paper-ConsoleBroadcast?style=for-the-badge)](https://github.com/jimmyl0l3c/paper-ConsoleBroadcast/releases/)

ConsoleBroadcast plugin for PaperMC.

# Features

* Send messages from console with configurable prefix.
* Send custom info and warning messages to chat. (with configurable prefix)

---

# Commands

## Say

Usage:

> **s** *MESSAGE*

Send message using [say prefix](#say-prefix).

## Info

Usage:

> **info** *MESSAGE*

Send message using [info prefix](#info-prefix).

## Warn

Usage:

> **warn** *MESSAGE*

Send message using [warn prefix](#warn-prefix).

## Reload

Usage:

> **cb reload**

Reloads plugin config.

---

# Configuration

Prefixes are configured in **config.yml** file located in **/plugins/ConsoleBroadcast** folder.

Default config:
```yaml
locale: en-US

prefixes:
    say: '§f[§6Server§f]§f'
    info: '§f[§eInfo§f]§e'
    warn: '§f[§cWarning§f]§l§c'
```

Ingame preview:

![Preview with default config!](./assets/preview.png "Preview with default config")

## Options

Useful resource: [Minecraft Formatting Codes](https://minecraft.fandom.com/wiki/Formatting_codes)

### locale

*NOTE: You must restart server in order to change locale in **help**.
**cb reload** doesn't have effect.*

#### Available languages:

- **English**: en-US
- **Czech**: cs-CZ

Set plugin language. **Not implemented yet.**

### say-prefix

Configure prefix for [say command](#say) using [MC formatting codes](https://minecraft.fandom.com/wiki/Formatting_codes).

### info-prefix

Configure prefix for [info command](#info) using [MC formatting codes](https://minecraft.fandom.com/wiki/Formatting_codes).

### warn-prefix

Configure prefix for [warn command](#warn) using [MC formatting codes](https://minecraft.fandom.com/wiki/Formatting_codes).

---

# Permissions

- **cb.reload** - Allow reload command
- **cb.say** - Allow say command
- **cb.warn** - Allow warn command
- **cb.info** - Allow info command

## Deprecated

Following permissions are deprecated and were removed in this version

- **cb.god** - Allow player to send messages.