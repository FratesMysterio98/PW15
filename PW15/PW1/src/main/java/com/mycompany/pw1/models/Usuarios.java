package com.mycompany.pw1.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuarios implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")  // Ajuste de nombre de la columna
    private int id;

    @Basic
    @Column(name = "nombre")  // Ajuste de nombre de la columna
    private String nombre;

    @Column(name = "apellidos")  // Ajuste de nombre de la columna
    private String apellidos;

    @Column(name = "fecha_nacimiento")  // Ajuste de nombre de la columna
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "correo_electronico")  // Ajuste de nombre de la columna
    private String correoElectronico;

    @Column(name = "imagen_perfil")  // Ajuste de nombre de la columna
    private String imagenPerfil;

    @Column(name = "nombre_usuario")  // Ajuste de nombre de la columna
    private String nombreUsuario;

    @Column(name = "contrasena")  // Ajuste de nombre de la columna
    private String contrasena;

    @Column(name = "fecha_creacion")  // Ajuste de nombre de la columna
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
