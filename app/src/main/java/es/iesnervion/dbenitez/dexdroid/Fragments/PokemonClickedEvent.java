package es.iesnervion.dbenitez.dexdroid.Fragments;

import es.iesnervion.dbenitez.dexdroid.Models.Pokemon;

public class PokemonClickedEvent
{
    final Pokemon pokemon;

    PokemonClickedEvent(Pokemon pokemon)
    {
        this.pokemon = pokemon;
    }
}
