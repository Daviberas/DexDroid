package es.iesnervion.dbenitez.dexdroid.Fragments;

import es.iesnervion.dbenitez.dexdroid.Models.Tipo;

public class TipoClickedEvent
{
    final Tipo tipo;

    TipoClickedEvent(Tipo tipo)
    {
        this.tipo = tipo;
    }

    public Tipo getTipo() {
        return tipo;
    }
}
