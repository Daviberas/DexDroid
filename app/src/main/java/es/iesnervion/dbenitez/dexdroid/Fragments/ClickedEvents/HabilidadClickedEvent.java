package es.iesnervion.dbenitez.dexdroid.Fragments.ClickedEvents;

import es.iesnervion.dbenitez.dexdroid.Models.Habilidad;

public class HabilidadClickedEvent
{
    final Habilidad habilidad;

    public HabilidadClickedEvent(Habilidad habilidad)
    {
        this.habilidad = habilidad;
    }

    public Habilidad getHabilidad()
    {
        return habilidad;
    }
}
