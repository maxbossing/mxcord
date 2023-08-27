# MXCord
> My personal discord bot, which I use on my servers

---

## Features
* Message reactions
* Easy access to pastes.dev from a command
* Auto-upload code blocks to pastes.dev to de-clutter the chat
* Letmegooglethat command
* Ping command
* Non-Authoritative nameserver lookup on domain names

## TODO
* Remove link embeds

## Running the bot
1. Place the newest release in a folder
2. Create a folder named `config`
3. create the files `credentials.json` and `settings.json` in the folder `config`
   1. `credentials.json`
      ```json
      {
        "dcToken": "YOUR TOKEN HERE"
      }
      ```
   2. `settings.json`
      ```json
      {
        "logChannel": ID OF YOUR LOGGING CHANNEL
      }
      ```
4. `java -jar MXCord.jar`

## License
MXCord is licensed under [lgpl v2.1](LICENSE)