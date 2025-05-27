import java.util.ArrayList;

public class ListaDoble {
    GuerreraBrightMoon primero, ultimo;

    public void insertarOrdenado(GuerreraBrightMoon nueva) {
        if (existeID(nueva.ID)) return;

        if (primero == null || nueva.ID < primero.ID) {
            nueva.sig = primero;
            if (primero != null) primero.ant = nueva;
            primero = nueva;
            if (ultimo == null) ultimo = nueva;
            return;
        }

        GuerreraBrightMoon actual = primero;
        while (actual.sig != null && actual.sig.ID < nueva.ID) {
            actual = actual.sig;
        }

        nueva.sig = actual.sig;
        if (actual.sig != null) actual.sig.ant = nueva;
        actual.sig = nueva;
        nueva.ant = actual;

        if (nueva.sig == null) ultimo = nueva;
    }

    public boolean existeID(int ID) {
        GuerreraBrightMoon actual = primero;
        while (actual != null) {
            if (actual.ID == ID) return true;
            actual = actual.sig;
        }
        return false;
    }

    public GuerreraBrightMoon[] toArray() {
        ArrayList<GuerreraBrightMoon> lista = new ArrayList<>();
        GuerreraBrightMoon actual = primero;
        while (actual != null) {
            lista.add(actual);
            actual = actual.sig;
        }
        return lista.toArray(new GuerreraBrightMoon[0]);
    }

    public GuerreraBrightMoon buscarBinaria(int ID) {
        GuerreraBrightMoon[] array = toArray();
        int inicio = 0, fin = array.length - 1;

        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            if (array[medio].ID == ID) return array[medio];
            if (array[medio].ID < ID) inicio = medio + 1;
            else fin = medio - 1;
        }
        return null;
    }

    public ArrayList<GuerreraBrightMoon> filtrarPorPoder(String poder) {
        ArrayList<GuerreraBrightMoon> resultado = new ArrayList<>();
        GuerreraBrightMoon actual = primero;
        while (actual != null) {
            if (actual.poderBatalla.equals(poder)) resultado.add(actual);
            actual = actual.sig;
        }
        return resultado;
    }

    public void ordenarPorNivelEstrategiaDesc(ArrayList<GuerreraBrightMoon> lista) {
        for (int i = 0; i < lista.size() - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < lista.size(); j++) {
                if (lista.get(j).nivelEstrategia > lista.get(maxIdx).nivelEstrategia) {
                    maxIdx = j;
                }
            }
            GuerreraBrightMoon temp = lista.get(i);
            lista.set(i, lista.get(maxIdx));
            lista.set(maxIdx, temp);
        }
    }

    public int contarPorUbicacion(String ubicacion) {
        return contarRecursivo(primero, ubicacion);
    }

    private int contarRecursivo(GuerreraBrightMoon nodo, String ubicacion) {
        if (nodo == null) return 0;
        return (nodo.ubicacion.equals(ubicacion) ? 1 : 0) + contarRecursivo(nodo.sig, ubicacion);
    }
}
