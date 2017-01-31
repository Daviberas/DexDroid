package es.iesnervion.dbenitez.dexdroid.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class HabilidadesPokemon implements Parcelable
{
    private int idHabilidad;
    private int idPokemon;
    private String categoria;

    public HabilidadesPokemon(int idHabilidad,int idPokemon,String categoria)
    {
        this.idHabilidad = idHabilidad;
        this.idPokemon = idPokemon;
        this.categoria = categoria;
    }

    protected HabilidadesPokemon(Parcel in) {
        idHabilidad = in.readInt();
        idPokemon = in.readInt();
        categoria = in.readString();
    }

    public static final Creator<HabilidadesPokemon> CREATOR = new Creator<HabilidadesPokemon>() {
        @Override
        public HabilidadesPokemon createFromParcel(Parcel in) {
            return new HabilidadesPokemon(in);
        }

        @Override
        public HabilidadesPokemon[] newArray(int size) {
            return new HabilidadesPokemon[size];
        }
    };

    public int getIdHabilidad()
    {
        return idHabilidad;
    }

    public void setIdHabilidad(int idHabilidad)
    {
        this.idHabilidad = idHabilidad;
    }

    public int getIdPokemon()
    {
        return idPokemon;
    }

    public void setIdPokemon(int idPokemon)
    {
        this.idPokemon = idPokemon;
    }

    public String getCategoria()
    {
        return categoria;
    }

    public void setCategoria(String categoria)
    {
        this.categoria = categoria;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idHabilidad);
        dest.writeInt(idPokemon);
        dest.writeString(categoria);
    }
}
