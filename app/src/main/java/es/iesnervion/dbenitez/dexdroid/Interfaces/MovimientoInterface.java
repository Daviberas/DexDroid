package es.iesnervion.dbenitez.dexdroid.Interfaces;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Models.Movimiento;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovimientoInterface
{
    @GET("Movimiento")
    Call<List<Movimiento>> getMovimiento();

    @GET("Movimiento/{id}")
    Call<List<Movimiento>> getMovimiento(@Path("id") int id);
}
