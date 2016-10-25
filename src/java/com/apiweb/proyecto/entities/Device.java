/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apiweb.proyecto.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kronos16
 */
@Entity
@Table(name = "Device")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Device.findAll", query = "SELECT d FROM Device d"),
    @NamedQuery(name = "Device.findByIdDevice", query = "SELECT d FROM Device d WHERE d.idDevice = :idDevice"),
    @NamedQuery(name = "Device.findByType", query = "SELECT d FROM Device d WHERE d.type = :type"),
    @NamedQuery(name = "Device.findByIp", query = "SELECT d FROM Device d WHERE d.ip = :ip"),
    @NamedQuery(name = "Device.findByLocation", query = "SELECT d FROM Device d WHERE d.location = :location"),
    @NamedQuery(name = "Device.findByTx", query = "SELECT d FROM Device d WHERE d.tx = :tx"),
    @NamedQuery(name = "Device.findByRx", query = "SELECT d FROM Device d WHERE d.rx = :rx"),
    @NamedQuery(name = "Device.findByError", query = "SELECT d FROM Device d WHERE d.error = :error"),
    @NamedQuery(name = "Device.findByAllow", query = "SELECT d FROM Device d WHERE d.allow = :allow"),
    @NamedQuery(name = "Device.findByDeny", query = "SELECT d FROM Device d WHERE d.deny = :deny"),
    @NamedQuery(name = "Device.findByConn", query = "SELECT d FROM Device d WHERE d.conn = :conn")})
public class Device implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDevice")
    private Integer idDevice;
    @Column(name = "type")
    private Integer type;
    @Size(max = 45)
    @Column(name = "ip")
    private String ip;
    @Size(max = 45)
    @Column(name = "location")
    private String location;
    @Column(name = "tx")
    private Integer tx;
    @Column(name = "rx")
    private Integer rx;
    @Column(name = "error")
    private Integer error;
    @Column(name = "allow")
    private Integer allow;
    @Column(name = "deny")
    private Integer deny;
    //@Size(max = 45)
    @Column(name = "conn")
    private Integer conn;

    public Device() {
    }

    public Device(Integer idDevice) {
        this.idDevice = idDevice;
    }

    public Integer getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(Integer idDevice) {
        this.idDevice = idDevice;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getTx() {
        return tx;
    }

    public void setTx(Integer tx) {
        this.tx = tx;
    }

    public Integer getRx() {
        return rx;
    }

    public void setRx(Integer rx) {
        this.rx = rx;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public Integer getAllow() {
        return allow;
    }

    public void setAllow(Integer allow) {
        this.allow = allow;
    }

    public Integer getDeny() {
        return deny;
    }

    public void setDeny(Integer deny) {
        this.deny = deny;
    }

    public Integer getConn() {
        return conn;
    }

    public void setConn(Integer conn) {
        this.conn = conn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDevice != null ? idDevice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Device)) {
            return false;
        }
        Device other = (Device) object;
        if ((this.idDevice == null && other.idDevice != null) || (this.idDevice != null && !this.idDevice.equals(other.idDevice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.apiweb.proyecto.entities.Device[ idDevice=" + idDevice + " ]";
    }
    
}
