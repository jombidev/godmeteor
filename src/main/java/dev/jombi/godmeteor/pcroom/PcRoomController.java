package dev.jombi.godmeteor.pcroom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Objects;

@Controller
public class PcRoomController {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String home(){
        return "home";
    }
    @ResponseBody
    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public void init(HashMap<String, Objects>map){
        System.out.println(map);
    }

}
