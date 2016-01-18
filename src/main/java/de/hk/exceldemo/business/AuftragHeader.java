/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hk.exceldemo.business;

/**
 *
 * @author palmherby
 */
public class AuftragHeader {
    private String gevoTyp;

    public AuftragHeader(String gevoTyp) {
        this.gevoTyp = gevoTyp;
    }

    
    public String getGevoTyp() {
        return gevoTyp;
    }

    public void setGevoTyp(String gevoTyp) {
        this.gevoTyp = gevoTyp;
    }

    
}
