package com.power.assistant.core.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.power.assistant.base.Constants;
import com.power.assistant.base.PageModel;
import com.power.assistant.base.PageParam;
import com.power.assistant.mapper.MenuMapper;
import com.power.assistant.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("menuService")
public class MenuService {


    @Autowired
    private MenuMapper menuMapper;

    public int save(Menu menu) {
        return menuMapper.insertSelective(menu);
    }


    /*public PageModel<Menu> list() {

        List<Menu> menus = menuMapper.selectAll();

        return new PageModel<Menu>(1, menus.size(), menus.size(), menus);
    }*/


    /**
     * @param userId
     * @return
     */
    public String userMenus(Long userId) {
        List<Menu> menus = menuMapper.selectUserMenus(userId);
        StringBuilder builder = new StringBuilder();
        builder.append("<li class='nav-devider'></li><li class='nav-label'>系统菜单</li>");
        if (menus != null && menus.size() > 0) {
            for (Menu menu : menus) {
                if (menu.getPid() == -1) {
                    //first level
                    builder.append("<li>");
                    builder.append("<a class='has-arrow' href='#' aria-expanded='false'>");
                    builder.append("<i class='fa fa-th-large'></i>");
                    builder.append("<span class='hide-menu'>" + menu.getName() + "</span></a>");
                    builder.append("<ul aria-expanded='false' class='collapse'>");
                    for (Menu secondLevel : menus) {
                        if (secondLevel.getPid().equals(menu.getId())) {
                            //second level
                            builder.append("<li><a href=\"javascript:void(0)\" onclick=\"Core.loadPage('" + secondLevel.getUrl() +
                                    "','" + secondLevel.getName() + "')\">" + secondLevel.getName() + "<i class='" + secondLevel.getIcon() + "'></i></a></li>");
                        }
                    }
                    builder.append("</ul>");
                    builder.append("</li>");
                }
            }
        }
        return builder.toString();
    }

    public PageModel<Menu> list(PageParam model) {
        PageHelper.offsetPage(model.getOffset(),model.getLimit(),true);
        List<Menu> menus = menuMapper.selectAll();
        PageInfo pageInfo = new PageInfo(menus);

        return PageModel.ok(pageInfo.getTotal(), pageInfo.getList());
    }
}
