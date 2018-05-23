package me.looorielovbb.ssmfuck.controller;

import me.looorielovbb.ssmfuck.dao.DeveloperDao;
import me.looorielovbb.ssmfuck.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import me.looorielovbb.ssmfuck.model.CommonModel;
import me.looorielovbb.ssmfuck.model.DeveloperModel;

import java.util.List;

@Controller
@RequestMapping("/developer")
public class DeveloperController {

    private DeveloperService developerService;

    @Autowired
    DeveloperController(DeveloperService developerService) {
        this.developerService = developerService;
    }


    @RequestMapping(value = "/api/hello", method = RequestMethod.GET)
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/api/developers", method = RequestMethod.GET)
    @ResponseBody//通过@ResponseBody返回json数据,通过@ResquestBody解析json
    public CommonModel getAllDevelopers() {
        List<DeveloperModel> developerList = developerService.getAllDevelopers();
        CommonModel commonModel = new CommonModel();
        if (developerList.size() > 0) {
            commonModel.setSuccess();
            commonModel.setData(developerList);
        } else {
            commonModel.setFail();
        }
        return commonModel;
    }

    @RequestMapping(value = "/api/developer", method = RequestMethod.GET)
    @ResponseBody
    public CommonModel getDeveloper(String id) {
        System.out.println("developerId=" + id);
        DeveloperModel developerModel = developerService.getDeveloper(id);
        CommonModel commonModel = new CommonModel();
        if (developerModel != null) {
            commonModel.setSuccess();
            commonModel.setData(developerModel);
        } else {
            commonModel.setFail();
        }
        return commonModel;
    }

    @RequestMapping(value = "/api/developer/add", method = RequestMethod.POST)
    @ResponseBody
    public CommonModel addDeveloper(String name, String site, String avatar) {
        System.out.println("name=" + name);
        DeveloperModel developerModel = new DeveloperModel();
        developerModel.setName(name);
        developerModel.setSite(site);
        developerModel.setAvatar(avatar);
        CommonModel commonModel = new CommonModel();
        if (developerService.addDeveloper(developerModel)) {
            commonModel.setSuccess();
        } else {
            commonModel.setFail();
        }
        return commonModel;
    }

    @RequestMapping(value = "/api/developer/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonModel updateDeveloper(String id, String name) {
        System.out.println("name=" + name);
        CommonModel commonModel = new CommonModel();
        if (developerService.updateDeveloper(id, name)) {
            commonModel.setSuccess();
        } else {
            commonModel.setFail();
        }
        return commonModel;
    }

    //SpringMVC	不支持put,delete方法实现传参,这里用到了PathVariable
    @RequestMapping(value = "/api/developer/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public CommonModel deleteDeveloper(@PathVariable("id") String id) {
        System.out.println("developerId=" + id);
        CommonModel commonModel = new CommonModel();
        if (developerService.deleteDeveloper(id)) {
            commonModel.setSuccess();
        } else {
            commonModel.setFail();
        }
        return commonModel;
    }

}
