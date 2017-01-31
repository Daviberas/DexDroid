package es.iesnervion.dbenitez.dexdroid.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Movimiento implements Parcelable
{
    private int id;
    private String nombre;
    private String efecto;
    private int potencia;
    private double porcentajeAcierto;
    private String via;
    private int pp;
    private String objetivo;
    private String contacto;
    private int id_tipo;

    public Movimiento(int id,String nombre,String efecto, int potencia,int porcentajeAcierto,String via,int pp,String objetivo,String contacto,int id_tipo)
    {
        this.id = id;
        this.nombre = nombre;
        this.efecto = efecto;
        this.potencia = potencia;
        this.porcentajeAcierto = porcentajeAcierto;
        this.via = via;
        this.pp = pp;
        this.objetivo = objetivo;
        this.contacto = contacto;
        this.id_tipo = id_tipo;
    }

    protected Movimiento(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        efecto = in.readString();
        potencia = in.readInt();
        porcentajeAcierto = in.readDouble();
        via = in.readString();
        pp = in.readInt();
        objetivo = in.readString();
        contacto = in.readString();
        id_tipo = in.readInt();
    }

    public static final Creator<Movimiento> CREATOR = new Creator<Movimiento>() {
        @Override
        public Movimiento createFromParcel(Parcel in) {
            return new Movimiento(in);
        }

        @Override
        public Movimiento[] newArray(int size) {
            return new Movimiento[size];
        }
    };

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getEfecto()
    {
        return efecto;
    }

    public void setEfecto(String efecto)
    {
        this.efecto = efecto;
    }

    public int getPotencia()
    {
        return this.potencia;
    }

    public void setPotencia(int potencia)
    {
        this.potencia = potencia;
    }

    public double getPorcentajeAcierto()
    {
        return this.porcentajeAcierto;
    }

    public void setPorcentajeAcierto(double porcentajeAcierto)
    {
        this.porcentajeAcierto = porcentajeAcierto;
    }

    public String getVia()
    {
        return this.via;
    }

    public void setVia(String via)
    {
        this.via = via;
    }

    public int getPp()
    {
        return this.pp;
    }

    public void setPp(int pp)
    {
        this.pp = pp;
    }

    public String getObjetivo()
    {
        return this.objetivo;
    }

    public void setObjetivo(String objetivo)
    {
        this.objetivo = objetivo;
    }

    public String getContacto()
    {
        return this.contacto;
    }

    public void setContacto(String contacto)
    {
        this.contacto = contacto;
    }

    public int getIdTipo()
    {
        return id_tipo;
    }

    public void setIdTipo(int id_tipo)
    {
        this.id_tipo = id_tipo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nombre);
        dest.writeString(efecto);
        dest.writeInt(potencia);
        dest.writeDouble(porcentajeAcierto);
        dest.writeString(via);
        dest.writeInt(pp);
        dest.writeString(objetivo);
        dest.writeString(contacto);
        dest.writeInt(id_tipo);
    }
}
