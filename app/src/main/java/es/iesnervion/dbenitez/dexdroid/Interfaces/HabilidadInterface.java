package es.iesnervion.dbenitez.dexdroid.Interfaces;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Models.Habilidad;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HabilidadInterface
{
    @GET("Habilidad")
    Call<List<Habilidad>> getHabilidad();

    @GET("Habilidad/{id}")
    Call<List<Habilidad>> getHabilidad(@Path("id") int id);
}
