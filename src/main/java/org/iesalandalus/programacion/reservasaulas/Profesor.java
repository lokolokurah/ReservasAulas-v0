/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Jaime
 */
public class Profesor 
{
    private static final String ER_TELEFONO = "[69][0-9]{8}";
    private static final String ER_CORREO = "([a-zA-z0-9.-_]{1,})(\\@[a-zA-z]{1,})(\\.[a-z]{1,3})"; 
    private String nombre;
    private String correo;
    private String telefono;
    
    public Profesor(String nombre, String correo) {
        setNombre(nombre);
        setCorreo(correo);
    }
    
    public Profesor(String nombre, String correo, String telefono) {
        setNombre(nombre);
        setCorreo(correo);
        setTelefono(telefono);
    }
    
    public Profesor(Profesor profesor) {
        if(profesor==null)
            throw new IllegalArgumentException("No se puede copiar un profesor nulo.");
        setNombre(profesor.getNombre());
        setCorreo(profesor.getCorreo());
        setTelefono(profesor.getTelefono());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre==null)
            throw new IllegalArgumentException("El nombre del profesor no puede ser nulo.");
        if(nombre.equals(""))
            throw new IllegalArgumentException("El nombre del profesor no puede estar vacío.");
        this.nombre=nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if(correo==null)
            throw new IllegalArgumentException("El correo del profesor no puede ser nulo.");
        Pattern pat = Pattern.compile(ER_CORREO);
        Matcher mat = pat.matcher(correo);
        if(mat.matches())
            this.correo = correo;
        else
            throw new IllegalArgumentException("El correo del profesor no es válido.");
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if(telefono==null) {
            this.telefono = null;
        } else {
        Pattern pat = Pattern.compile(ER_TELEFONO);
        Matcher mat = pat.matcher(telefono);
        if(mat.matches())
            this.telefono = telefono;
        else
            throw new IllegalArgumentException("El teléfono del profesor no es válido.");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Profesor other = (Profesor) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.nombre);
        hash = 97 * hash + Objects.hashCode(this.correo);
        hash = 97 * hash + Objects.hashCode(this.telefono);
        return hash;
    }

    @Override
    public String toString() {
        if(getTelefono()==null)
            return "[nombre=" + getNombre() + ", correo=" + getCorreo() + "]";
        else
            return "[nombre=" + getNombre() + ", correo=" + getCorreo() + ", telefono=" + getTelefono() + "]";
    }
}
