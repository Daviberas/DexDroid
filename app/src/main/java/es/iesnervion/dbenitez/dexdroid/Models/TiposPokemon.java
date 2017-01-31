package es.iesnervion.dbenitez.dexdroid.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class TiposPokemon implements Parcelable
{
    private int idTipo;
    private int idPokemon;

    public TiposPokemon(int idTipo, int idPokemon)
    {
        this.idTipo = idTipo;
        this.idPokemon = idPokemon;
    }

    protected TiposPokemon(Parcel in)
    {
        idTipo = in.readInt();
        idPokemon = in.readInt();
    }

    public static final Creator<TiposPokemon> CREATOR = new Creator<TiposPokemon>()
    {
        @Override
        public TiposPokemon createFromParcel(Parcel in)
        {
            return new TiposPokemon(in);
        }

        @Override
        public TiposPokemon[] newArray(int size)
        {
            return new TiposPokemon[size];
        }
    };

    public int getIdTipo()
    {
        return idTipo;
    }

    public void setIdTipo(int idTipo)
    {
        this.idTipo = idTipo;
    }

    public int getIdPokemon()
    {
        return idPokemon;
    }

    public void setIdPokemon(int idPokemon)
    {
        this.idPokemon = idPokemon;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(idTipo);
        dest.writeInt(idPokemon);
    }
}
