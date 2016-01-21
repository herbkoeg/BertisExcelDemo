/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hk.exceldemo.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author palmherby
 */
@Entity
public class Beitragsaenderung extends GeVo{
   
    private Long id;
    private String vnr;
    private Double beitragAktuell;
    private Double beitragNeu;
    private Date stichtag;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVnr() {
        return vnr;
    }

    public void setVnr(String vnr) {
        this.vnr = vnr;
    }

    public Double getBeitragAktuell() {
        return beitragAktuell;
    }

    public void setBeitragAktuell(Double beitragAktuell) {
        this.beitragAktuell = beitragAktuell;
    }

    public Double getBeitragNeu() {
        return beitragNeu;
    }

    public void setBeitragNeu(Double beitragNeu) {
        this.beitragNeu = beitragNeu;
    }

    public Date getStichtag() {
        return stichtag;
    }

    public void setStichtag(Date stichtag) {
        this.stichtag = stichtag;
    }
    
    
    
}
