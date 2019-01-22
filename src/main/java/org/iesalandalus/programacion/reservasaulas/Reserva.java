/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas;

import java.util.Objects;

/**
 *
 * @author Jaime
 */
public class Reserva 
{
    Aula aula;
    Permanencia permanencia;
    Profesor profesor;
    
    public Reserva(Profesor profesor, Aula aula, Permanencia permanencia) {
        setProfesor(profesor);
        setAula(aula);
        setPermanencia(permanencia);
    }
    
    public Reserva(Reserva reserva) {
        if(reserva==null)
            throw new IllegalArgumentException("No se puede copiar una reserva nula.");
        setProfesor(reserva.getProfesor());
        setAula(reserva.getAula());
        setPermanencia(reserva.getPermanencia());
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        if(aula==null)
            throw new IllegalArgumentException("La reserva debe ser para un aula concreta.");
        this.aula = new Aula(aula);
    }

    public Permanencia getPermanencia() {
        return permanencia;
    }

    public void setPermanencia(Permanencia permanencia) {
        if(permanencia==null)
            throw new IllegalArgumentException("La reserva se debe hacer para una permanencia concreta.");
        this.permanencia = new Permanencia(permanencia);
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        if(profesor==null)
            throw new IllegalArgumentException("La reserva debe estar a nombre de un profesor.");
        this.profesor = new Profesor(profesor);
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
        final Reserva other = (Reserva) obj;
        if (!Objects.equals(this.aula, other.aula)) {
            return false;
        }
        if (!Objects.equals(this.permanencia, other.permanencia)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.aula);
        hash = 29 * hash + Objects.hashCode(this.permanencia);
        return hash;
    }

    @Override
    public String toString() {
        /* String cadenaEsperada = "[profesor=[nombre=José Ramón, correo=joseramon.jimenez@iesalandalus.org]"
+ ", aula=[nombre=Salón de actos], permanencia=[dia=01/12/2018, tramo=Mañana]]"; */
        return "[profesor=" + getProfesor() + ", aula=" + getAula() + ", permanencia=" + getPermanencia() + "]";
    } 
}
