package homeMusic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import homeMusic.Tracks.SoundcloudTrack;

@Controller
public class ClientControler {
    public ClientControler(){}

    @PostMapping("/client/addTrack")
    public String addTrack(){
        HomeMusicApplication.tracks.add(new SoundcloudTrack("https://soundcloud.com/artist/track"));
        return "lol";
    }

    @PostMapping("/client/removeTrack")
    public String removeTrack(){
        if (!HomeMusicApplication.tracks.isEmpty()) {
            HomeMusicApplication.tracks.remove(HomeMusicApplication.tracks.size() - 1);
        }
        return "lol";
    }

    @PostMapping("/client/rearrangeTracks")
    public String rearrangeTracks(){
        if (!HomeMusicApplication.tracks.isEmpty()) {
            HomeMusicApplication.tracks.add(0, HomeMusicApplication.tracks.remove(HomeMusicApplication.tracks.size() - 1));
        }
        return "lol";
    }

    @PostMapping("/client/shuffleTracks")
    public String shuffleTracks(){
        java.util.Collections.shuffle(HomeMusicApplication.tracks);
        return "lol";
    }
}
