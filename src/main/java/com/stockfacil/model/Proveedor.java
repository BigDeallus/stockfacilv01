/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.stockfacil.model;

/**
 *
 * @author Ronny
 */
public class Proveedor {

    private int idProveedor;
    private String nombre;
    private String cedulaJuridica;
    private String telefono;
    private String correo;
    private boolean activo;

    // Constructor vacío
    public Proveedor() {
    }

    // Constructor con todos los atributos
    public Proveedor(int idProveedor, String nombre,
            String cedulaJuridica, String telefono,
            String correo, boolean activo) {

        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.cedulaJuridica = cedulaJuridica;
        this.telefono = telefono;
        this.correo = correo;
        this.activo = activo;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedulaJuridica() {
        return cedulaJuridica;
    }

    public void setCedulaJuridica(String cedulaJuridica) {
        this.cedulaJuridica = cedulaJuridica;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}

