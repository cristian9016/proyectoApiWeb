package com.apiweb.proyecto.bean;

import com.apiweb.proyecto.controller.UsuariosJpaController;
import com.apiweb.proyecto.entities.Usuarios;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


@Named(value = "newUserBean")
@SessionScoped
public class NewUserBean implements Serializable {

    private String validate;
    Usuarios u = new Usuarios();

    public String create(){
        if(validate.equals("123")){
            if(lookForDuplicated()){
                FacesContext context = FacesContext.getCurrentInstance(); 
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "El Usuario Ya Existe",null));
                return "";
            }else{
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoPU");
                UsuariosJpaController ujc = new UsuariosJpaController(emf);
                try{
                    ujc.create(u);
                    return "";
                }catch(Exception e){
                    return "";
                }
            }
            
            
        }else{
                FacesContext context = FacesContext.getCurrentInstance(); 
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Codigo",null));
                return "";
        }
    }
    
    public boolean lookForDuplicated(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoPU");
        EntityManager em = emf.createEntityManager();
        Usuarios us = em.createNamedQuery("Usuarios.findByUsername", Usuarios.class).setParameter("username", u.getUsername()).getSingleResult();
        if(us!=null){
            return true;
        }else{
            return false;
        }
        
    }
    
    public String getValidate() {
        return validate;
    }

    public void setValidate(String validate) {
        this.validate = validate;
    }

    public Usuarios getU() {
        return u;
    }

    public void setU(Usuarios u) {
        this.u = u;
    }
    
    
    
    public NewUserBean() {
    }
    
}
