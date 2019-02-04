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
public class Reservas 
{
    private Reserva[] coleccionReservas = new Reserva[MAX_RESERVAS];
    private static final int MAX_RESERVAS = 100;
    private int numReservas;
    
    public Reservas() {
         this.numReservas = 0;
    }
    
    public Reservas(Reservas reservas) {
        setReservas(reservas);
    }
    
    private void setReservas(Reservas reservas) {
        if(reservas==null)
            throw new IllegalArgumentException("No se pueden copiar reservas nulas.");
        this.coleccionReservas =  copiaProfundaReservas(reservas.coleccionReservas);
        this.numReservas = reservas.numReservas;
    }
    
    private Reserva[] copiaProfundaReservas(Reserva[] reservas) {
        Reserva[] replica = new Reserva[reservas.length];
        for(int i=0; i<replica.length && reservas[i]!=null; i++)
            replica[i] = new Reserva(reservas[i]);
        return replica;
    }
    
    public Reserva[] getReservas() {
        return copiaProfundaReservas(this.coleccionReservas);
    }
    
    public int getNumReservas() {
        return numReservas;
    }
    
    public void insertar(Reserva reserva) throws OperationNotSupportedException {
        if(reserva==null) {
            throw new IllegalArgumentException("No se puede realizar una reserva nula.");
        }
        if(indiceNoSuperaTamano(buscarIndiceReserva(reserva))==false) {
            coleccionReservas[numReservas] = reserva;
            numReservas = numReservas+1;
        } else {
            if (indiceNoSuperaCapacidad(buscarIndiceReserva(reserva))){
                throw new OperationNotSupportedException("La reserva ya existe.");
            } else {
                throw new OperationNotSupportedException("No se permiten más reservas.");
            }
        } 
    }
    
    public int buscarIndiceReserva(Reserva reserva){
            for(int i=0; i<coleccionReservas.length; i++) { 
                if(coleccionReservas[i]!=null) {
                    if(coleccionReservas[i].equals(reserva))
                        return i;
                } else {
                    return i;
                }
            }
            return MAX_RESERVAS;
    }
    
    private boolean indiceNoSuperaTamano(int indice) {
        if(indice>coleccionReservas.length) {
            return true;
        } else {
            return false;
        }
    }
    
    private boolean indiceNoSuperaCapacidad(int indice) {
        if (indice>MAX_RESERVAS) {
            return true;
        } else {
            return false;
        }
    }
    
    public Reserva buscar(Reserva reserva) {
        if(reserva==null)
            return null;
        int indice = buscarIndiceReserva(reserva);
        if(indiceNoSuperaTamano(indice))
            return coleccionReservas[indice];
        return null;
    }
    
    public void borrar(Reserva reserva) throws OperationNotSupportedException {
        if(reserva==null) {
            throw new IllegalArgumentException("No se puede anular una reserva nula.");
        }
        if(indiceNoSuperaTamano(buscarIndiceReserva(reserva))) {
            desplazarUnaPosicionHaciaIzquierda(buscarIndiceReserva(reserva));
        } else {
            throw new OperationNotSupportedException("La reserva a anular no existe.");
        }
    }
    
    private void desplazarUnaPosicionHaciaIzquierda(int posicion) {
        for(int i=posicion; i<numReservas-1; i++) {
            coleccionReservas[i] = coleccionReservas[i+1];
        }
        numReservas = numReservas-1;
    }
    
    public String[] representar() {
        String[] representar = new String[numReservas];
        for(int i=0; i<representar.length; i++)
            representar[i] = coleccionReservas[i].toString();
        return representar;
    }
    
    public Reserva[] getReservasProfesor(Profesor profesor) {    
        if(profesor==null)
            throw new IllegalArgumentException("No se pueden comprobar las reservas de un profesor nulo.");
        Reserva[] retornar = new Reserva[MAX_RESERVAS];
        int indice = 0;
        for(int i=0; i<numReservas; i++) {
            if(coleccionReservas[i].getProfesor().equals(profesor)) {
                retornar[indice] = new Reserva(coleccionReservas[i]);
                indice = indice+1;
            }
        }
        return retornar;
    }
    
    public Reserva[] getReservasAula(Aula aula) {
        if(aula==null)
            throw new IllegalArgumentException("No se pueden comprobar las reservas realizadas sobre un aula nula.");
        Reserva[] retornar = new Reserva[MAX_RESERVAS];
        int indice = 0;
        for(int i=0; i<numReservas; i++) {
            if(coleccionReservas[i].getAula().equals(aula)) {
                retornar[indice] = new Reserva(coleccionReservas[i]);
                indice = indice+1;
            }
        }
        return retornar;
    }
    
    /*public Reserva[] getReservasAula(Aula aula) {    
    }*/
    
    public Reserva[] getReservasPermanencia(Permanencia permanencia) {
        int indice = 0;
        Reserva[] reservaPermanencia = new Reserva[MAX_RESERVAS];
        if(permanencia==null) {
            throw new IllegalArgumentException("No se pueden consultar las reservas de una permanencia nula.");
        }
        for(int i=0; i<numReservas; i++) {
            if(coleccionReservas[i].getPermanencia().equals(permanencia)) {
                reservaPermanencia[indice] = new Reserva(coleccionReservas[i]);
                indice = indice+1;
            }
        }
        return reservaPermanencia;
    }
    
    public boolean consultarDisponibilidad(Aula aula, Permanencia permanencia) {
        if(aula==null)
            throw new IllegalArgumentException("No se puede consultar la disponibilidad de un aula nula.");
        if(permanencia==null)
            throw new IllegalArgumentException("No se puede consultar la disponibilidad de una permanencia nula.");
        for(int i=0; i<coleccionReservas.length &&coleccionReservas[i]!=null; i++) {
            if(coleccionReservas[i].getAula().equals(aula) && coleccionReservas[i].getPermanencia().equals(permanencia))
                return false;
        }
        return true;
    }
}
