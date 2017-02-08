package es.iesnervion.dbenitez.dexdroid.Models;

public class FilaMovimiento
{
    String nombre;
    String aprendizaje;
    String tipo;

    public FilaMovimiento(String nombre, String aprendizaje, String tipo)
    {
        this.nombre = nombre;
        this.aprendizaje = aprendizaje;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAprendizaje() {
        return aprendizaje;
    }

    public void setAprendizaje(String aprendizaje) {
        this.aprendizaje = aprendizaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
