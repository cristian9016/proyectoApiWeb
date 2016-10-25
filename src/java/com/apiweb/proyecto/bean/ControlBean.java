/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apiweb.proyecto.bean;

import com.apiweb.proyecto.controller.DeviceJpaController;
import com.apiweb.proyecto.entities.Device;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.constraints.NotNull;


@Named(value = "controlBean")
@SessionScoped
public class ControlBean implements Serializable {
    @NotNull
    Device device = new Device();
    
    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }
    
    
    public ControlBean() {
    }
    

    
    public void create(){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoPU");
    DeviceJpaController djc = new DeviceJpaController(emf);
    try{
        djc.create(device);
    }catch(Exception e){
        
    }
    }
    public void edit(){
     EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoPU");
    DeviceJpaController djc = new DeviceJpaController(emf);
    try{
        djc.edit(device);
    }catch(Exception e){
        
    }   
    }
    
}
