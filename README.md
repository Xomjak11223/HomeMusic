# HomeMusic

## Prerequesits
- You need to configure your IP-Address in this project
- Java
- Optionally Spring

## Description
Imagine a scenario where you and your friends all want listen to music together.
Each of you has a different taste in music so everyone wants to play something different.

The easyest solution would be to have one phone connected to a speaker and hand it around to whoever wants to add a song to a playlist,
but this would restrict the host of the phone to allways be available and the phone to be handed around each time a someone wants to add a track.

Spotify offers a handy solution to that, it is called a 'Jam'.
As before, you have one host that opens the Jam but instead of needing one phone the host can invite people into the Jam and everyone can make changes as they please.
But what if someone wants to play a track that is not available on spotify?
Maybe the use a different platform like soundcloud to listen to music. 
They would have to search for each individual track to add it and if they cant figure out the name of the track, they would have to go back to their music app of choice, search it, remember the name, go back to spotify and add the track.

This software strives to solve these problems.
With HomeMusic people in your network can access a website, enter a url from a supportet music application and it will automatically be added to a playlist.
The music will then be played from the system you launched this Software from.

## Tldr
Listening to tracks together has some problems because peaple may uses different music services to listen to the music they like
This software provides the following features to solve these problems:
- A website that people in your network can access
- A playlist-interface where people can add/remove/shufle songs to listen to
- A collective place where all your music will be played
- A playlist solution like spotifys 'Jam' feature, that is application independent

## Compatible music services
- Youtube
- Soundcloud
- Spotify

## How to run this app
- Configure your IP-Address (see below)
- Run the Software using `.\mvnw spring-boot:run`
- After a few seconds, a website will be available for all users in your network
- Website-URL: http://YOUR_IP_ADDRESS:8080
- Example-URL: http://192.168.40.60:8080

## How to configure your IP-Address
To configure your IP-Address to this software navigate to the applications.properties file (/source/main/java/homeMusic/resources/application.properties).
In it you will find the server.address property.
Write your IP-Address in this section.

### If you don't know what your IP-Address is:
- Open a new Terminal
- Write the prompt `ipconfig` in it
- search for the IPv4-Address in the 'Ethernet-Adapter Ethernet' section, that is your IP-Address

## How to use this Software
TODO beschreibung schreiben