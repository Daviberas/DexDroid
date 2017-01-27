package es.iesnervion.dbenitez.dexdroid.RetrofitInterfaces;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Models.Evolucion;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EvolucionInterface
{
    @GET("Evolucion")
    Call<List<Evolucion>> getEvolucion();

    @GET("Evolucion/{id}")
    Call<List<Evolucion>> getEvolucion(@Path("id") int id);
}
