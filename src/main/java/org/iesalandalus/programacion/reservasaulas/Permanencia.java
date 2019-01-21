/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author Jaime
 */
public class Permanencia 
{
    private LocalDate dia;
    private Tramo tramo;
    private static final DateTimeFormatter FORMATO_DIA = DateTimeFormatter.ofPattern("dd/MM/uuuu");; 

    public Permanencia(LocalDate dia, Tramo tramo) {
        setDia(dia);
        setTramo(tramo);
    }
    
    public Permanencia(Permanencia permanencia) {
		if(permanencia==null) {
			throw new IllegalArgumentException("No se puede copiar una permanencia nula.");
                }
		setDia(permanencia.getDia());
		setTramo(permanencia.getTramo());
	}

    public LocalDate getDia() {
        return LocalDate.of(dia.getYear(), dia.getMonth(), dia.getDayOfMonth());
    }

    public void setDia(LocalDate dia) {
        if (dia==null) {
            throw new IllegalArgumentException("El día de una permanencia no puede ser nulo.");
        }
	this.dia = LocalDate.of(dia.getYear(), dia.getMonth(), dia.getDayOfMonth());
    }

    public Tramo getTramo() {
        return tramo;
    }

    public void setTramo(Tramo tramo) {
        if(tramo == null) {
            throw new IllegalArgumentException("El tramo de una permanencia no puede ser nulo.");
        }
        this.tramo = tramo;
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
        final Permanencia other = (Permanencia) obj;
        if (!Objects.equals(this.dia, other.dia)) {
            return false;
        }
        if (!Objects.equals(this.tramo, other.tramo)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.dia);
        hash = 53 * hash + Objects.hashCode(this.tramo);
        return hash;
    }

    @Override
    public String toString() {
        // "[dia=01/12/2018, tramo=Mañana]"
        return "[dia="+getDia().format(FORMATO_DIA)+", tramo="+getTramo()+"]";
    }
}
