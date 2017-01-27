package es.iesnervion.dbenitez.dexdroid.RetrofitInterfaces;

import java.util.List;

import es.iesnervion.dbenitez.dexdroid.Models.Tipo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TipoInterface
{
    @GET("Tipo")
    Call<List<Tipo>> getTipo();

    @GET("Tipo/{id}")
    Call<List<Tipo>> getTipo(@Path("id") int id);
}
