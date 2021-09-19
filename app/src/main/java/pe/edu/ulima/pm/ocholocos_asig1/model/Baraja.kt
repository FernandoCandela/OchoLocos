package pe.edu.ulima.pm.ocholocos_asig1.model

public class Baraja(val numCartas: Int) {
    private var cartas = MutableList<Carta?>(numCartas) { null };
    private val limitePalos: Int = 13;
    private val palos = arrayOf("Espadas", "Corazones", "Rombos", "Trebol");

    fun crearBaraja() {
        for (i in 0..palos.size) {
            for (j in 0..limitePalos) {
                this.cartas.add(Carta(j + 1, palos[i]));
            }
        }
    }

    fun barajar() {
        var posAleatorea: Int = 0;
        var c: Carta?;
        for (i in 0..cartas.size) {
            posAleatorea = (0 until numCartas).random();
            c = cartas[i];
            cartas[i] = cartas[posAleatorea];
            cartas[posAleatorea] = c;
        }
    }

    fun getFirstCarta(): Carta? {
        var c: Carta? = cartas[0];
        cartas.removeAt(0)
        return c;
    }

    fun repartirCartas(numCartas: Int): MutableList<Carta?>? {
        if (numCartas > this.cartas.size) {
            // no se pueden repartir cartas
            return null;
        } else {
            var mazo = MutableList<Carta?>(numCartas) { null };
            for (i in 0 until numCartas) {
                mazo.add(this.getFirstCarta());
            }
            return mazo;
        }
    }
}