package es.iesnervion.dbenitez.dexdroid.Fragments.ClickedEvents;

import es.iesnervion.dbenitez.dexdroid.Models.Movimiento;

public class MovimientoClickedEvent
{
    final Movimiento movimiento;

    public MovimientoClickedEvent(Movimiento movimiento)
    {
        this.movimiento = movimiento;
    }

    public Movimiento getMovimiento()
    {
        return movimiento;
    }
}
