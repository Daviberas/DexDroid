package es.iesnervion.dbenitez.dexdroid.Fragments;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Models.Pokemon;
import es.iesnervion.dbenitez.dexdroid.Models.Tipo;
import es.iesnervion.dbenitez.dexdroid.Models.TiposPokemon;

public interface ApiResponse
{
    void pokemonResponse(List<Pokemon> poke);
    void tiposPokemonResponse(List<TiposPokemon> tiposPoke);
    void tipoResponse(List<Tipo> tipos);
}
