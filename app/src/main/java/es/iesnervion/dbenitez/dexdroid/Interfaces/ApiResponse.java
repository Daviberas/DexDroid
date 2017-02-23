package es.iesnervion.dbenitez.dexdroid.Interfaces;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Models.Evolucion;
import es.iesnervion.dbenitez.dexdroid.Models.Habilidad;
import es.iesnervion.dbenitez.dexdroid.Models.HabilidadesPokemon;
import es.iesnervion.dbenitez.dexdroid.Models.Movimiento;
import es.iesnervion.dbenitez.dexdroid.Models.MovimientosPokemon;
import es.iesnervion.dbenitez.dexdroid.Models.Pokemon;
import es.iesnervion.dbenitez.dexdroid.Models.Tipo;
import es.iesnervion.dbenitez.dexdroid.Models.TiposPokemon;

public interface ApiResponse
{
    void pokemonResponse(List<Pokemon> poke, boolean evolucion);
    void tiposPokemonResponse(List<TiposPokemon> tiposPoke);
    void tipoResponse(List<Tipo> tipos);
    void habilidadesPokemonResponse(List<HabilidadesPokemon> habilidadesPoke);
    void habilidadResponse(List<Habilidad> habilidades, String categoria);
    void evolucionResponse(List<Evolucion> evoluciones);
    void movimientosPokemonResponse(List<MovimientosPokemon> movimientosPokemon);
    void movimientoResponse(List<Movimiento> movimientos);
}
