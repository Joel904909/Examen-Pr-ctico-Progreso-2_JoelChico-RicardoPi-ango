public class DefensorEternia {
    public int ID;
    public String nombre;
    public String habilidad;
    public String region;
    public int poder;
    public DefensorEternia sig;

    public DefensorEternia(int ID, int poder, String nombre, String habilidad, String region, Object o) {
        this.ID = ID;
        this.region=region;
        this.nombre=nombre;
        this.poder=poder;
        this.habilidad = habilidad;
        this.sig=null;
    }
}
