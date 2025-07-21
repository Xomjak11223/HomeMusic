package homeMusic;

//import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;

@Controller
public class homeMusicControler {

    //Path: 192.168.56.1:8080/home
    public homeMusicControler(){}
    
    @GetMapping("/")
    public String home(){
        System.out.println("A device has connected");
        return "index.html";
    }
}
