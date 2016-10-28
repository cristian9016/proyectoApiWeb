
package com.apiweb.proyecto.bean;

import com.apiweb.proyecto.controller.DeviceJpaController;
import com.apiweb.proyecto.entities.Device;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;

@ManagedBean(name = "welcomeBean")
public class welcomeBean implements Serializable{

    private MindmapNode model = new DefaultMindmapNode("Network","Network","FFCC00",false);
    
    private List<Device> device = new ArrayList();
    private MindmapNode selectedNode;
    private String location;
    private MindmapNode node;
    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
       
    @PostConstruct
    public void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoPU");
        DeviceJpaController dev = new DeviceJpaController(emf);
        device = dev.findDeviceEntities();
        String taip = "";
        
        for(Integer i=0;i<device.size();i++){
            Integer id = device.get(i).getIdDevice();
            Integer type = device.get(i).getType();
            Integer conn = device.get(i).getConn();
            String ip = device.get(i).getIp();
            
            if(null != type)switch (type) {
                case 1:
                    taip = "Router";
                    break;
                case 2:
                    taip = "Switch";
                    break;
                case 3:
                    taip = "Firewall";
                    break;
                case 4:
                    taip = "LoadBal";
                    break;
                default:
                    taip = "Server";
                    break;
            }
            String details = "Id = "+id+"  ,Type = "+taip+" ,Ip = "+ip;
            MindmapNode x = new DefaultMindmapNode(taip+" "+id,details,"0055FF",true);
            if(conn == 0){
                model.addNode(x);
            }
        }
       
    }     
    
    public void onNodeSelect(SelectEvent event) {
        selectedNode = (MindmapNode) event.getObject();
         
        //populate if not already loaded
        if(selectedNode.getChildren().isEmpty()) {
            List<Device> list = null;
            String idn = (String)selectedNode.getData();
            String idnn = idn.substring(5,7);
            Integer idnnn = Integer.parseInt(idnn.replace(" ", ""));
            System.out.print(idnnn); 
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoPU");
            EntityManager em = emf.createEntityManager();
            list = em.createNamedQuery("Device.findByConn").setParameter("conn", idnnn).getResultList();
            String taip = "";
            
                for(Integer g=0;g<list.size();g++){
                    Integer conn = list.get(g).getConn();
                    Integer id = list.get(g).getIdDevice();
                    Integer type = list.get(g).getType();
                    String ip = list.get(g).getIp();
                    
                    if(null != type)switch (type) {
                        case 1:
                            taip = "Router";
                            break;
                        case 2:
                            taip = "Switch";
                            break;
                        case 3:
                            taip = "Firewall";
                            break;
                        case 4:
                            taip = "LoalBal";
                            break;
                        default:
                            taip = "Server";
                            break;
                    }
                        String details = "Id = "+id+"  ,Type = "+taip+" ,Ip = "+ip;
                        MindmapNode x = new DefaultMindmapNode(taip+" "+id,details,"0055FF",true);
                        selectedNode.addNode(x);
                    
            }
            
            
        }   
    }
    
    public void mapa(){
        
    }
  
    public MindmapNode getModel() {
        return model;
    }
     
    public List<Device> getDevice() {
  
        return device;
    }

    public void setDevice(List<Device> device) {
        this.device = device;
    }

    public MindmapNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(MindmapNode selectedNode) {
        this.selectedNode = selectedNode;
    }
    
    public void onNodeDblselect(SelectEvent event) {
        this.selectedNode = (MindmapNode) event.getObject();
        String n = (String) selectedNode.getData();
        String n2 = n.substring(5,7);
        Integer n3 = Integer.parseInt(n2.replace(" ", ""));
        System.out.println("ubicacion: "+n3);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoPU");
        EntityManager em = emf.createEntityManager();
        //String b = locat.substring(5,7);
        //Integer c = Integer.parseInt(b.replace(" ", ""));
        Device de = em.createNamedQuery("Device.findByIdDevice", Device.class).setParameter("idDevice", n3).getSingleResult();
        String loc = de.getLocation();
        System.out.println("ubicacion: "+loc);
        location = "https://maps.googleapis.com/maps/api/staticmap?center="+loc+"&zoom=12&size=600x300&maptype=roadmap&markers=color:blue%7Clabel:S%7C"+loc+"&key=AIzaSyAoy9ahDapg64HeKRXyubPDw9IADOdmetQ";        
    }
}
  
    
        

