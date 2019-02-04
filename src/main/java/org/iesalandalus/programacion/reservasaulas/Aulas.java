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
public class Aulas 
{
    private static final int MAX_AULAS = 5;
    private int numAulas;
    private Aula[] coleccionAulas = new Aula[MAX_AULAS];
    
    public Aulas() {
        this.numAulas = 0;
    }

    public Aulas(Aulas aulas) {
        setAulas(aulas);
    }
    
    private void setAulas(Aulas aulas) {
        if(aulas==null)
            throw new IllegalArgumentException("No se pueden copiar aulas nulas.");
        this.coleccionAulas = copiaProfundaAulas(aulas.getAulas());
    }
    
    private Aula[] copiaProfundaAulas(Aula[] coleccionAulas) {
        Aula[] replica=new Aula[MAX_AULAS];
        for(int i = 0; i<replica.length && coleccionAulas[i]!=null; i++)
            replica[i] = new Aula(coleccionAulas[i]);
        return replica;
    }

    public Aula[] getAulas() {
        return copiaProfundaAulas(this.coleccionAulas);
    }

    public int getNumAulas() {
        return numAulas;
    }
    
    public void insertar(Aula aula) throws OperationNotSupportedException {
        if(aula==null)
            throw new IllegalArgumentException("No se puede insertar un aula nula.");
        int indice = buscarIndiceAula(aula);
        if(indiceNoSuperaTamano(indice))
            throw new OperationNotSupportedException("El aula ya existe.");
        if(indiceNoSuperaCapacidad(indice)) {
            coleccionAulas[indice] = aula;
            numAulas++;
        } else {
            throw new OperationNotSupportedException("Se ha alcanzado el máximo de aulas que se pueden guardar.");
        }         
    }
    
    private int buscarIndiceAula(Aula aula) {
        for(int i = 0; i < coleccionAulas.length; i++) {
            if(coleccionAulas[i]!=null) {
                if(coleccionAulas[i].equals(aula))
                    return i;
            } else {
                return i;
            }
        }
        return MAX_AULAS;
    }

    private boolean indiceNoSuperaTamano(int indice) {
        if(indice<numAulas)
            return true;
        else
        return false;
    }    
    
    private boolean indiceNoSuperaCapacidad(int indice) {
        if(indice<MAX_AULAS)
            return true;
        return false;
    }
    
    public Aula buscar(Aula aula) {
        if(aula==null)
            return null;
        int indice = buscarIndiceAula(aula);
        if(indiceNoSuperaTamano(indice))
            return coleccionAulas[indice];
        return null;
    }
    
    public void borrar(Aula aula) throws OperationNotSupportedException {
        if(aula==null)
            throw new IllegalArgumentException("No se puede borrar un aula nula.");
        int indice = buscarIndiceAula(aula);
        if(indiceNoSuperaTamano(indice)) {
            coleccionAulas[indice] = null;
            desplazarUnaPosicionHaciaIzquierda(indice);
            numAulas = numAulas - 1;
        } else {
            throw new OperationNotSupportedException("El aula a borrar no existe.");
        }
    }
    
    private void desplazarUnaPosicionHaciaIzquierda(int indice) {
        for(int i = indice; i<coleccionAulas.length && coleccionAulas[i+1]!=null; i++)
            coleccionAulas[i] = coleccionAulas[i+1];
        coleccionAulas[numAulas-1] = null;
    }
    
    
    public String[] representar() {
        String[] representar = new String[numAulas];
        for(int i = 0; i < representar.length; i++)
            representar[i] = coleccionAulas[i].toString();
        return representar;	
    }   
}
