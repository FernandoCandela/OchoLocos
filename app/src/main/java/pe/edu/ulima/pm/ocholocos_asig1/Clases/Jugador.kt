package pe.edu.ulima.pm.ocholocos_asig1.Clases

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

class Jugador(context: Context, atrs: AttributeSet) : LinearLayout(context, atrs) {

    lateinit var nombre: String;
    var gano: Boolean = false;
    var cartas = mutableListOf<Carta>();


    fun getCartaSelect(): Carta? {
        var cartaS: Carta? = null;
        for (carta in cartas) {
            if (carta.isSelect) {
                cartaS = carta;
            }
        }
        return cartaS;
    }

    fun removeCarta() {
        for (i in cartas.indices) {
            if (cartas[i].isSelect) {
                cartas.removeAt(i);
                break;
            }
        }
    }

    fun addCarta(carta: Carta) {
        this.cartas.add(carta);
    }

    fun addCartas(cartas: MutableList<Carta>) {
        this.cartas.let { list1 -> cartas.let(list1::addAll) }
    }


}