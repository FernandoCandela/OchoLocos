package pe.edu.ulima.pm.ocholocos_asig1.Clases

import android.content.Context
import android.util.AttributeSet

class Baraja(context: Context, atrs: AttributeSet) :
    androidx.appcompat.widget.AppCompatImageView(context, atrs) {
    private val atrs: AttributeSet = atrs;
    private var cartas = mutableListOf<Carta>();
    private val limitePalos: Int = 13;
    private val numCartas: Int = 52;
    private val palos = arrayOf("espadas", "corazones", "diamantes", "trebol");
    var cartaCentral: Carta;

    init {
        this.crearBaraja();
        this.barajar();
        this.cartaCentral = this.getFirstCarta();
    }

    fun crearBaraja() {
        for (i in palos.indices) {
            for (j in 0 until limitePalos) {
                when (j) {
                    12 -> this.cartas.add(
                        Carta(
                            j + 1,
                            palos[i],
                            Efecto.MASTRES,
                            this.context,
                            this.atrs
                        )
                    );
                    10 -> this.cartas.add(
                        Carta(
                            j + 1,
                            palos[i],
                            Efecto.SALTO,
                            this.context,
                            this.atrs
                        )
                    );
                    else -> {
                        this.cartas.add(Carta(j + 1, palos[i], null, this.context, this.atrs));
                    }
                }
            }
        }
    }

    fun barajar() {
        var posAleatorea: Int = 0;
        var c: Carta?;
        for (i in 0 until cartas.size) {
            posAleatorea = (0 until numCartas).random();
            c = cartas[i];
            cartas[i] = cartas[posAleatorea];
            cartas[posAleatorea] = c;
        }
    }

    fun addCarta(carta:Carta){
        this.cartas.add(carta);
    }

    fun getFirstCarta(): Carta {
        var c: Carta = cartas[0];
        cartas.removeAt(0)
        return c;
    }

    fun getListofCartas(numCartas: Int): MutableList<Carta>? {
        return if (numCartas > this.cartas.size) {
            // no se pueden repartir cartas
            null;
        } else {
            var mazo = mutableListOf<Carta>();
            for (i in 0 until numCartas) {
                mazo.add(this.getFirstCarta());
            }
            mazo;
        }
    }
}