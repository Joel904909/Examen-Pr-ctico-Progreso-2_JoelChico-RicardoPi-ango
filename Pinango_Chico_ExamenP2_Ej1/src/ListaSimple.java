import javax.swing.*;

public class ListaSimple {
    DefensorEternia primero;

    public ListaSimple() {
        this.primero = null;
    }

    public void insertarAlFinal(int ID, int poder, String nombre, String region, String habilidad) {
        DefensorEternia nuevo = new DefensorEternia(ID, poder, nombre, region, habilidad, null);

        if (primero == null) {
            primero = nuevo;
        } else {
            DefensorEternia actual = primero;
            while (actual.sig != null) {
                actual = actual.sig;
            }
            actual.sig = nuevo;
        }
    }

    public boolean existeID(int ID) {
        DefensorEternia actual = primero;
        while (actual != null) {
            if (actual.ID == ID) return true;
            actual = actual.sig;
        }
        return false;
    }

    public DefensorEternia buscarPorID(int ID) {
        DefensorEternia actual = primero;
        while (actual != null) {
            if (actual.ID == ID) return actual;
            actual = actual.sig;
        }
        return null;
    }

    public DefensorEternia getPrimero() {
        return primero;
    }

    public ListaSimple filtrarYOrdenar(String regionFiltro) {
        ListaSimple nueva = new ListaSimple();
        DefensorEternia actual = primero;
        while (actual != null) {
            if (!actual.region.equals(regionFiltro)) {
                nueva.insertarAlFinal(actual.ID, actual.poder, actual.habilidad, actual.nombre, actual.region);
            }
            actual = actual.sig;
        }
        nueva.ordenarBurbujaPoder();
        return nueva;

    }
    public void ordenarBurbujaPoder() {
        if (primero == null) return;
        boolean swapped;
        do {
            swapped = false;
            DefensorEternia actual = primero;
            while (actual.sig != null) {
                if (actual.poder > actual.sig.poder) {
                    // Intercambiar datos
                    int tempID = actual.ID;
                    int tempPoder = actual.poder;
                    String tempNombre = actual.nombre;
                    String tempHabilidad = actual.habilidad;
                    String tempRegion = actual.region;

                    actual.ID = actual.sig.ID;
                    actual.poder = actual.sig.poder;
                    actual.nombre = actual.sig.nombre;
                    actual.habilidad = actual.sig.habilidad;
                    actual.region = actual.sig.region;

                    actual.sig.ID = tempID;
                    actual.sig.poder = tempPoder;
                    actual.sig.nombre = tempNombre;
                    actual.sig.habilidad = tempHabilidad;
                    actual.sig.region = tempRegion;

                    swapped = true;
                }
                actual = actual.sig;
            }
        } while (swapped);
    }

    public void contarPorPoderRecursivo(DefensorEternia nodo, JTextArea textArea) {
        if (nodo == null) return;

        if (!textArea.getText().contains(nodo.habilidad)) {
            int count = contarPorPoderRecursivoHelper(nodo.habilidad, primero);
            textArea.append(nodo.habilidad + ": " + count + " defensores\n");
        }
        contarPorPoderRecursivo(nodo.sig, textArea);
    }

    private int contarPorPoderRecursivoHelper(String habilidad, DefensorEternia nodo) {
        if (nodo == null) return 0;
        if (nodo.habilidad.equals(habilidad)) {
            return 1 + contarPorPoderRecursivoHelper(habilidad, nodo.sig);
        } else {
            return contarPorPoderRecursivoHelper(habilidad, nodo.sig);
        }
    }


}
