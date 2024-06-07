/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pw1.models;

import java.io.Serializable;
import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author sadam
 */
@Entity
public class Ejemplo implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    int idEjemplo;
    
    @Basic
    private String nombreEjemplo;

    public Ejemplo() {
    }

    public Ejemplo(int idEjemplo, String nombreEjemplo) {
        this.idEjemplo = idEjemplo;
        this.nombreEjemplo = nombreEjemplo;
    }

    public int getIdEjemplo() {
        return idEjemplo;
    }

    public void setIdEjemplo(int idEjemplo) {
        this.idEjemplo = idEjemplo;
    }

    public String getNombreEjemplo() {
        return nombreEjemplo;
    }

    public void setNombreEjemplo(String nombreEjemplo) {
        this.nombreEjemplo = nombreEjemplo;
    }
    
    
    
}
