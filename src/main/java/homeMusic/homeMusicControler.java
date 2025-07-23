package homeMusic;

//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeMusicControler {

    //TODO häufig gespielte leider speichern
    //Path: 192.168.56.1:8080/home
    public HomeMusicControler(){}
    
    @GetMapping("/")
    public String selectMode(){
        return "selection.html";
    }

    @GetMapping("/host")
    public String host(Model model){
        System.out.println("A device has connected");
        model.addAttribute("tracks", HomeMusicApplication.tracks);
        return "host.html";
    }

    //TODO maybe nutzer accounts hinzu fügen? (Session token mäßig)
    @GetMapping("/client")
    public String client(Model model){
        System.out.println("A device has connected");
        model.addAttribute("tracks", HomeMusicApplication.tracks);
        return "client.html";
    }
}
