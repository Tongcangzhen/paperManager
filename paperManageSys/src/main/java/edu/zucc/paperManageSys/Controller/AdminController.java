package edu.zucc.paperManageSys.Controller;

import edu.zucc.paperManageSys.Entity.PaperTypeEntity;
import edu.zucc.paperManageSys.Service.PaperService;
import edu.zucc.paperManageSys.util.HostHolder;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    PaperService paperService;

    @Autowired
    HostHolder hostHolder;

    @RequestMapping(path = "/papertype", method = RequestMethod.GET)
    public String papertype(Model model) throws Exception {
        if (hostHolder.getUser() == null) {
            throw new Exception("用户未登陆!");
        }
        return "/papertype";
    }

    @RequestMapping(path = "/type_list", method = RequestMethod.POST)
    @ResponseBody
    public String typeList(Model model) throws Exception {
        if (hostHolder.getUser() == null) {
            throw new Exception("用户未登陆!");
        }
        List<PaperTypeEntity> typeList = paperService.typeQueryAll();
        if(typeList == null)   return null;

        JSONArray result = new JSONArray();
        for (PaperTypeEntity typeEntity:typeList) {
            result.appendElement(typeEntity);
        }
        logger.info("Paper type names:"+result.toJSONString());
        return result.toJSONString();
    }

    @RequestMapping(path = "/type_add", method = RequestMethod.POST)
    @ResponseBody
    public String typeAdd(Model model, @RequestBody String data) throws Exception {
        if (hostHolder.getUser() == null) {
            throw new Exception("用户未登陆!");
        }

        JSONObject dataObj = (JSONObject)JSONValue.parseStrict(data);

        JSONObject result = new JSONObject();
        if(paperService.typeAdd(dataObj.getAsString("typeName"))){
            result.put("result","success");
        }else{
            result.put("result","failed");
        }

        return result.toJSONString();
    }

    @RequestMapping(path = "/type_remove", method = RequestMethod.POST)
    @ResponseBody
    public String typeRemove(Model model, @RequestBody String data) throws Exception {
        if (hostHolder.getUser() == null) {
            throw new Exception("用户未登陆!");
        }

        JSONObject dataObj = (JSONObject)JSONValue.parseStrict(data);

        JSONObject result = new JSONObject();
        if(paperService.typeDel(dataObj.getAsString("typeId"))){
            result.put("result","success");
        }else{
            result.put("result","failed");
        }

        return result.toJSONString();
    }
}
