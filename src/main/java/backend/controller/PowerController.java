package backend.controller;

import backend.GetModel;
import backend.GetPVData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Controller
public class PowerController {

    @GetMapping("/data")
    public Map<String, String> getData() {
        GetPVData getPVData = new GetPVData("127.0.0.1", 6760);
        HashMap<String, String> map = new HashMap<>();

        try {
            String resp = getPVData.run();
            map.put("data", resp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    @GetMapping("/developer/iec")
    public ModelAndView getIECModel() {
       GetModel getModel = new GetModel("127.0.0.1", 6760);
        String[] modelList = null;
        try {
            String model = getModel.run();
            modelList = model.split("\n");
        } catch (IOException e){
            e.printStackTrace();
        }
       ModelAndView modelAndView = new ModelAndView("IECdisplay");
       modelAndView.addObject("LD1", "solarPanel");
        modelAndView.addObject("LN1", "MMXU");
        modelAndView.addObject("A1", "TotW: 6\nq: True\nt: 00:59:01");
        modelAndView.addObject("LD2", "gridInterconnection");
        modelAndView.addObject("LN2a", "DGEN");
        modelAndView.addObject("A2a", "mag: 6\nq: True\nt: 00:59:01");
        modelAndView.addObject("LN2b", "CSWI");
        modelAndView.addObject("A2b", "cltVal: False\nq: True\nt: 00:59:01");
       return modelAndView;
    }
}
