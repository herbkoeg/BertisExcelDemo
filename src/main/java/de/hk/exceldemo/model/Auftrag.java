/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hk.exceldemo.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;

/**
 *
 * @author palmherby
 */
@Entity
public class Auftrag {
    private String user;
    private List<GeVo> gevoList;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<GeVo> getGevoList() {
        if(gevoList==null)
            return new ArrayList<>();
        return gevoList;
    }

    public void setGevoList(List<GeVo> gevoList) {
        this.gevoList = gevoList;
    }
    
    
}
