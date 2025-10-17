# HomeMusic


## Table of Contents
- [HomeMusic](#homemusic)
  - [Table of Contents](#table-of-contents)
  - [Prerequisites](#prerequisites)
  - [Description](#description)
  - [Tldr](#tldr)
  - [Compatible music services](#compatible-music-services)
  - [How to run this app](#how-to-run-this-app)
  - [How to configure your IP-Address](#how-to-configure-your-ip-address)
    - [If you don't know what your IP-Address is:](#if-you-dont-know-what-your-ip-address-is)
  - [How to use this Software](#how-to-use-this-software)


## Prerequisites
- You need to configure your IP-Address in this project
- Java
- Optionally Spring

## Description
Imagine a scenario where you and your friends all want listen to music together.
Each of you has a different taste in music so everyone wants to play something different.

The easiest solution would be to have one phone connected to a speaker and hand it around to whoever wants to add a song to a playlist,
but this would restrict the host of the phone to always be available and the phone to be handed around each time a someone wants to add a track.

Spotify offers a handy solution to that, it is called a 'Jam'.
As before, you have one host that opens the Jam but instead of needing one phone the host can invite people into the Jam and everyone can make changes as they please.
But what if someone wants to play a track that is not available on Spotify?
Maybe they use a different platform, like Soundcloud, to listen to music. 
They would have to search for each individual track to add it and if they cant figure out the name of the track, they would have to go back to their music app of choice, search it, remember the name, go back to spotify and add the track.

This software strives to solve these problems.
With HomeMusic, people in your network can access a website, enter a url from a supported music application and the track will automatically be added to the playlist.
The music will then be played from the system you launched this Software from.

## Tldr
Listening to tracks together has some problems because people may uses different music services to listen to the music they like
This software provides the following features to solve these problems:
- A website that people in your network can access
- A playlist-interface where people can add/remove/shuffle songs to listen to
- A collective place where all your music will be played
- A playlist solution like spotifys 'Jam' feature, that is application independent

## Compatible music services
- Youtube
- Soundcloud
- Spotify

## How to run this app
- clone the repository using `git clone https://github.com/Xomjak11223/HomeMusic.git`
- navigate to the project folder `cd HomeMusic`
- Configure your IP-Address (see below)
- Run the Software using `.\mvnw spring-boot:run`
- After a few seconds, a website will be available for all users in your network
- Website-URL: http://YOUR_IP_ADDRESS:8080
- Example-URL: http://192.168.40.60:8080

## How to configure your IP-Address
To configure your IP-Address to this software navigate to 
**`/src/main/java/resources/application.properties`**
Inside the file, set the following property:
```properties
server.address=YOUR_IP_ADDRESS
```
### If you don't know what your IP-Address is:
- Open a new Terminal
- Write the prompt `ipconfig` in it
- search for the IPv4-Address in the 'Ethernet-Adapter Ethernet' section, that is your IP-Address

## How to use this Software
TODO beschreibung schreiben