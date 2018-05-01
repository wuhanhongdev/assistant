package com.power.assistant.facade;

import com.power.assistant.base.DataModel;
import com.power.assistant.base.PageModel;
import com.power.assistant.core.annotation.Authentication;
import com.power.assistant.core.service.MenuService;
import com.power.assistant.model.Menu;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;


    /*@Authentication
    @GetMapping("/auth")
    public DataModel auth(String userId){
        try {
            return DataModel.ok(menuService.userMenus(userId));
        } catch (Exception e) {
            e.printStackTrace();
            return DataModel.error(e.getMessage());
        }
    }*/
}
