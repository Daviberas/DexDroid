package es.iesnervion.dbenitez.dexdroid.Fragments;

import es.iesnervion.dbenitez.dexdroid.Models.Movimiento;

public class MovimientoClickedEvent
{
    final Movimiento movimiento;

    MovimientoClickedEvent(Movimiento movimiento)
    {
        this.movimiento = movimiento;
    }
}
