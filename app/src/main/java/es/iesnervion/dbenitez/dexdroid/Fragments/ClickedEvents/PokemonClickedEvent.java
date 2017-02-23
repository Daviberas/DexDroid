package es.iesnervion.dbenitez.dexdroid.Fragments.ClickedEvents;

import es.iesnervion.dbenitez.dexdroid.Models.Pokemon;

public class PokemonClickedEvent
{
    final Pokemon pokemon;

    public PokemonClickedEvent(Pokemon pokemon)
    {
        this.pokemon = pokemon;
    }

    public Pokemon getPokemon()
    {
        return pokemon;
    }
}
