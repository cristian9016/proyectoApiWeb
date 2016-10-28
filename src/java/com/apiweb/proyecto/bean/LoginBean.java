package com.apiweb.proyecto.bean;

import com.apiweb.proyecto.entities.Usuarios;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;


@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String usuario;
    private String pass;
    
    public String login(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoPU");
        EntityManager em = emf.createEntityManager();
        try{
            Usuarios u = em.createNamedQuery("Usuarios.login", Usuarios.class).setParameter("username", usuario).setParameter("pass", pass).getSingleResult();
        if(u!=null){
            String type = u.getType();
            HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("LOGGED", true);
            if(type.equals("admin")){
                return "welcome?faces-redirect=true";
            }else{
                return "welcome-user?faces-redirect=true";
            }
            
            
        }else{
            FacesContext context = FacesContext.getCurrentInstance(); 
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario o Contraseña Incorrectos",null));
            return "";
        }
        }catch(Exception ex){
            FacesContext context = FacesContext.getCurrentInstance(); 
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Nombre de Usuario o Contraseña Incorrectos"));
            return "";
        }
        
    }

    public String logout(){
            HttpSession session = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.invalidate();
            return "index?faces-redirect=true";
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    
    
    public LoginBean() {
    }
    
}
