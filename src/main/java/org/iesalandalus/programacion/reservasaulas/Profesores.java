/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas;

import javax.naming.OperationNotSupportedException;

/**
 *
 * @author Jaime
 */
public class Profesores 
{
    //Try 2 ;;
    private Profesor[] coleccionProfesores = new Profesor[MAX_PROFESORES];
    static final int MAX_PROFESORES = 50;
    int numProfesores;
    
    public Profesores() {
        this.numProfesores = 0;
    }
    
    public Profesores(Profesores profesores) {
        setProfesores(profesores);
    }
    
    public void setProfesores(Profesores profesores) {
        if(profesores==null)
            throw new IllegalArgumentException("No se pueden copiar profesores nulos.");
    this.coleccionProfesores = copiaProfundaProfesores(profesores.getProfesores());
    }
    
    private Profesor[] copiaProfundaProfesores(Profesor[] profesores) {
        Profesor[] replica = new Profesor[MAX_PROFESORES];
        for(int i=0; i<replica.length && profesores[i]!=null; i++)
            replica[i] = new Profesor(profesores[i]);
        return replica;
    }
    
    public Profesor[] getProfesores() {
        return copiaProfundaProfesores(this.coleccionProfesores);
    }
    
    public int getNumProfesores() {
        return numProfesores;
    }
    
    public void insertar(Profesor profesor) throws OperationNotSupportedException {
        if(profesor==null)
            throw new IllegalArgumentException("No se puede insertar un profesor nulo.");
        int indice = buscarIndiceProfesor(profesor);
        if(indiceNoSuperaTamano(indice))
            throw new OperationNotSupportedException("El profesor ya existe.");
        if(indiceNoSuperaCantidad(indice)) {
            coleccionProfesores[indice] = profesor;
            numProfesores = numProfesores +1;
        } else {
            throw new OperationNotSupportedException("Se ha alcanzado el máximo de profesores que se pueden guardar.");
        }
    }
    
    private int buscarIndiceProfesor(Profesor profesor) {
        for(int i=0; i<coleccionProfesores.length; i++) {
            if(coleccionProfesores[i]!=null) {
                if(coleccionProfesores[i].equals(profesor))
                    return i;
            } else {
                return i;
            }
        }
        return MAX_PROFESORES;
    }
    
    private boolean indiceNoSuperaTamano(int indice) {
        if(indice<numProfesores)
            return true;
        return false;
    }
    
    private boolean indiceNoSuperaCantidad(int indice) {
        if(indice<MAX_PROFESORES)
            return true;
        return false;
    }
    
    public Profesor buscar(Profesor profesor) {
        if(profesor==null)
            return null; 
        int indice = buscarIndiceProfesor(profesor);    
        if(indiceNoSuperaTamano(indice))
            return coleccionProfesores[indice];
        return null;
    }
    
    public void borrar(Profesor profesor) throws OperationNotSupportedException {
        if(profesor==null) 
            throw new IllegalArgumentException("No se puede borrar un profesor nulo.");
        int indice = buscarIndiceProfesor(profesor);
        if(indiceNoSuperaTamano(indice)) {
            coleccionProfesores[indice] = null;
            desplazarUnaPosicionHaciaIzquierda(indice);
            numProfesores = numProfesores-1;
        } else {
            throw new OperationNotSupportedException("El profesor a borrar no existe.");
        }
    }
    
    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for(int i=indice; i<coleccionProfesores.length && coleccionProfesores[i+1]!=null; i++) {
            coleccionProfesores[i] = coleccionProfesores[i+1];
        } 
        coleccionProfesores[numProfesores-1] = null;
    }
    
    public String[] representar() { 
        String[] representar = new String[numProfesores];
        for(int i=0; i<representar.length; i++) {
            representar[i] = coleccionProfesores[i].toString();
        }
        return representar;
    }
}
