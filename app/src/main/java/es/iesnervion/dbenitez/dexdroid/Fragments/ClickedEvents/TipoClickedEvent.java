package es.iesnervion.dbenitez.dexdroid.Fragments.ClickedEvents;

import es.iesnervion.dbenitez.dexdroid.Models.Tipo;

public class TipoClickedEvent
{
    final Tipo tipo;

    public TipoClickedEvent(Tipo tipo)
    {
        this.tipo = tipo;
    }

    public Tipo getTipo() {
        return tipo;
    }
}
