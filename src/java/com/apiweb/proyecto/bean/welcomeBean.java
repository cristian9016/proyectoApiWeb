
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

    private MindmapNode model = new DefaultMindmapNode("Network","Network","FFCC00",true);;
    private List<Device> device = new ArrayList();
    
    
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
            MindmapNode x = new DefaultMindmapNode(taip+" "+id,id,"0055FF",true);
            if(conn == 0){
                model.addNode(x);
            }
        }
    }     
    
    public void onNodeSelect(SelectEvent event) {
        MindmapNode node = (MindmapNode) event.getObject();
         
        //populate if not already loaded
        if(node.getChildren().isEmpty()) {
            
            Integer idn = (Integer) node.getData();
            System.out.print(idn);
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoPU");
            EntityManager em = emf.createEntityManager();
            List<Device> list = em.createNamedQuery("Device.findByConn").setParameter("conn", idn).getResultList();
            String taip = "";
            
                for(Integer g=0;g<list.size();g++){
                    Integer conn = list.get(g).getConn();
                    Integer id = list.get(g).getIdDevice();
                    Integer type = list.get(g).getType();

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
                    
                        MindmapNode x = new DefaultMindmapNode(taip+" "+id,id,"0055FF",true);
                        node.addNode(x);
                    
            }
            
            
        }   
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

}
  
    
        

