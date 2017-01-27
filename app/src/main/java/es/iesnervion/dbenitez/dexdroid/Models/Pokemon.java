package es.iesnervion.dbenitez.dexdroid.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Pokemon implements Parcelable
{
    private int numPokedex;
    private String nombre;
    private double porcentajeMacho;
    private double porcentajeHembra;
    private String imagen;

    public Pokemon(int numPokedex, String nombre, double porcentajeMacho, double porcentajeHembra, String imagen)
    {
        this.numPokedex = numPokedex;
        this.nombre = nombre;
        this.porcentajeMacho = porcentajeMacho;
        this.porcentajeHembra = porcentajeHembra;
        this.imagen = imagen;
    }

    protected Pokemon(Parcel in) {
        numPokedex = in.readInt();
        nombre = in.readString();
        porcentajeMacho = in.readDouble();
        porcentajeHembra = in.readDouble();
        imagen = in.readString();
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

    public int getNumPokedex()
    {
        return numPokedex;
    }

    public void setNumPokedex(int numPokedex)
    {
        this.numPokedex = numPokedex;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public double getPorcentajeMacho()
    {
        return porcentajeMacho;
    }

    public void setPorcentajeMacho(double porcentajeMacho)
    {
        this.porcentajeMacho = porcentajeMacho;
    }

    public double getPorcentajeHembra()
    {
        return porcentajeHembra;
    }

    public void setPorcentajeHembra(double porcentajeHembra)
    {
        this.porcentajeHembra = porcentajeHembra;
    }

    public String getImagen()
    {
        return imagen;
    }

    public void setImagen(String imagen)
    {
        this.imagen = imagen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(numPokedex);
        dest.writeString(nombre);
        dest.writeDouble(porcentajeMacho);
        dest.writeDouble(porcentajeHembra);
        dest.writeString(imagen);
    }
}

