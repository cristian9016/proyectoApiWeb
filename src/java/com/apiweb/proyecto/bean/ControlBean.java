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


@Named(value = "controlBean")
@SessionScoped
public class ControlBean implements Serializable {
    
    private Integer delete;
    
    private Device device = new Device();
    private Device devi = new Device();

    public Device getDevi() {
        return devi;
    }

    public void setDevi(Device devi) {
        this.devi = devi;
    }
    
    public Integer getDelete() {
        return delete;
    }

    public void setDelete(Integer delete) {
        this.delete = delete;
    }
    
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
    DeviceJpaController djc1 = new DeviceJpaController(emf);
    try{
        djc1.create(device);
    }catch(Exception e){
        
    }
    }
    
    public void edit(){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoPU");
    DeviceJpaController djc2 = new DeviceJpaController(emf);
    try{
        djc2.edit(devi);
    }catch(Exception e){
        
    }   
    }
    
    public void delete(){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoPU");
    DeviceJpaController djc3 = new DeviceJpaController(emf);
    try{
        djc3.destroy(delete);
    }catch(Exception e){
        
    }
    }
    
}
