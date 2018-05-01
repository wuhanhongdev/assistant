package com.power.assistant.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageModel<T> implements Serializable {


    private Long total;
    private boolean sue;
    private List<T> res;

    public static <T> PageModel<T> ok(Long total, List<T> res){
        PageModel<T> model = new PageModel<T>();
        model.setSue(true);
        model.setTotal(total);
        model.setRes(res);

        return model;
    }

    public static <T> PageModel<T> error(){
        PageModel<T> model = new PageModel<T>();
        model.setTotal(0L);
        model.setSue(true);
        model.setRes(new ArrayList<>());

        return model;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public boolean isSue() {
        return sue;
    }

    public void setSue(boolean sue) {
        this.sue = sue;
    }

    public List<T> getRes() {
        return res;
    }

    public void setRes(List<T> res) {
        this.res = res;
    }
}
