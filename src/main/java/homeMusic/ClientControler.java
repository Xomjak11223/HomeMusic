package homeMusic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import homeMusic.Tracks.SoundcloudTrack;

@Controller
public class ClientControler {
    public ClientControler(){}

    @PostMapping("/client/addTrack")
    public String addTrack(@RequestParam("url") String url) {
        /*TODO
         * Erkenne, von welcher Plattform der Track kommt
         * Erkenne, ob die url gültig ist
         */
        HomeMusicApplication.tracks.add(new SoundcloudTrack(url));
        return "redirect:/client";
    }

    //TODO zum entfernen eine ID einfügen, oder die Position des Tracks in der Liste nutzen?
    @PostMapping("/client/removeTrack/{index}")
    public String removeTrack(@PathVariable("index") int position) {
        if (!HomeMusicApplication.tracks.isEmpty()) {
            HomeMusicApplication.tracks.remove(HomeMusicApplication.tracks.size() - 1);
        }
        return "redirect:/client";
    }

    @PostMapping("/client/rearrangeTracks")
    public String rearrangeTracks(){
        if (!HomeMusicApplication.tracks.isEmpty()) {
            HomeMusicApplication.tracks.add(0, HomeMusicApplication.tracks.remove(HomeMusicApplication.tracks.size() - 1));
        }
        return "redirect:/client";
    }

    //TODO liedern einen Nutzer hinzu fügen, sodass bei einem Shuffle nicht 20 lieder des selben Nutzers hintereinander abgespielt werden
    @PostMapping("/client/shuffleTracks")
    public String shuffleTracks(){
        java.util.Collections.shuffle(HomeMusicApplication.tracks);
        return "redirect:/client";
    }
}
