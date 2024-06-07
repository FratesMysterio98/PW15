package com.mycompany.pw1.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Publicaciones implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="idPublicacion")
    private int idPublicacion;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuarios usuario;
    
    @Basic
    private String titulo;
    
    @Basic
    private String descripcion;
    
    @Basic
    private boolean estatus;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMovimiento;

    public Publicaciones() {
    }

    public int getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(int idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public Publicaciones(int idPublicacion, Usuarios usuario, String titulo, String descripcion, boolean estatus, Date fechaCreacion, Date fechaMovimiento) {
        this.idPublicacion = idPublicacion;
        this.usuario = usuario;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estatus = estatus;
        this.fechaCreacion = fechaCreacion;
        this.fechaMovimiento = fechaMovimiento;
    }

    public Publicaciones(Usuarios usuario, String titulo, String descripcion, boolean estatus, Date fechaCreacion, Date fechaMovimiento) {
        this.usuario = usuario;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.estatus = estatus;
        this.fechaCreacion = fechaCreacion;
        this.fechaMovimiento = fechaMovimiento;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstatus() {
        return estatus;
    }

    public void setEstatus(boolean estatus) {
        this.estatus = estatus;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPublicacion;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Publicaciones)) {
            return false;
        }
        Publicaciones other = (Publicaciones) object;
        return this.idPublicacion == other.idPublicacion;
    }

    @Override
    public String toString() {
        return "com.mycompany.pw1.models.Publicaciones[ id=" + idPublicacion + " ]";
    }

    public void setAutor(String autor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
