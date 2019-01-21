/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas;

/**
 *
 * @author Jaime
 */
public enum Tramo 
{
    MANANA("Mañana"), // Añadidos Strings por defecto del test
    TARDE("Tarde");
    private String cadenaAMostrar;

    //Implementado constructor por defecto
    private Tramo(String cadenaAMostrar) {
        this.cadenaAMostrar = cadenaAMostrar;
    }

    @Override
    public String toString() {
        return cadenaAMostrar;
    }
}
