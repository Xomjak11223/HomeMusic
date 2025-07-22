package homeMusic;

//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeMusicControler {

    //Path: 192.168.56.1:8080/home
    public HomeMusicControler(){}
    
    @GetMapping("/")
    public String selectMode(){
        return "selection.html";
    }

    @GetMapping("/host")
    public String host(){
        System.out.println("A device has connected");
        return "host.html";
    }

    @GetMapping("/client")
    public String client(){
        System.out.println("A device has connected");
        return "client.html";
    }
}
