package pe.edu.ulima.pm.ocholocos_asig1.Clases

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import pe.edu.ulima.pm.ocholocos_asig1.R

class Carta(
    var numero: Int,
    var palo: String,
    var efecto: Efecto?,
    context: Context,
    atrs: AttributeSet
) : androidx.appcompat.widget.AppCompatImageView(context, atrs) {
     var isSelect: Boolean = false;
    init {
        this.setImg(this.numero, this.palo);
        this.setSize(200,200);
    }

    fun setSize(w: Int, h: Int) {
        this.layoutParams = ViewGroup.LayoutParams(w, h);
    }

    fun setImg(numero: Int, palo: String) {
        when (palo + "_" + numero) {
            "corazones_1" -> setImageResource(R.drawable.corazones_1);
            "corazones_2" -> setImageResource(R.drawable.corazones_2);
            "corazones_3" -> setImageResource(R.drawable.corazones_3);
            "corazones_4" -> setImageResource(R.drawable.corazones_4);
            "corazones_5" -> setImageResource(R.drawable.corazones_5);
            "corazones_6" -> setImageResource(R.drawable.corazones_6);
            "corazones_7" -> setImageResource(R.drawable.corazones_7);
            "corazones_8" -> setImageResource(R.drawable.corazones_8);
            "corazones_9" -> setImageResource(R.drawable.corazones_9);
            "corazones_10" -> setImageResource(R.drawable.corazones_10);
            "corazones_11" -> setImageResource(R.drawable.corazones_11);
            "corazones_12" -> setImageResource(R.drawable.corazones_12);
            "corazones_13" -> setImageResource(R.drawable.corazones_13);
            "diamantes_1" -> setImageResource(R.drawable.diamantes_1);
            "diamantes_2" -> setImageResource(R.drawable.diamantes_2);
            "diamantes_3" -> setImageResource(R.drawable.diamantes_3);
            "diamantes_4" -> setImageResource(R.drawable.diamantes_4);
            "diamantes_5" -> setImageResource(R.drawable.diamantes_5);
            "diamantes_6" -> setImageResource(R.drawable.diamantes_6);
            "diamantes_7" -> setImageResource(R.drawable.diamantes_7);
            "diamantes_8" -> setImageResource(R.drawable.diamantes_8);
            "diamantes_9" -> setImageResource(R.drawable.diamantes_9);
            "diamantes_10" -> setImageResource(R.drawable.diamantes_10);
            "diamantes_11" -> setImageResource(R.drawable.diamantes_11);
            "diamantes_12" -> setImageResource(R.drawable.diamantes_12);
            "diamantes_13" -> setImageResource(R.drawable.diamantes_13);
            "trebol_1" -> setImageResource(R.drawable.trebol_1);
            "trebol_2" -> setImageResource(R.drawable.trebol_2);
            "trebol_3" -> setImageResource(R.drawable.trebol_3);
            "trebol_4" -> setImageResource(R.drawable.trebol_4);
            "trebol_5" -> setImageResource(R.drawable.trebol_5);
            "trebol_6" -> setImageResource(R.drawable.trebol_6);
            "trebol_7" -> setImageResource(R.drawable.trebol_7);
            "trebol_8" -> setImageResource(R.drawable.trebol_8);
            "trebol_9" -> setImageResource(R.drawable.trebol_9);
            "trebol_10" -> setImageResource(R.drawable.trebol_10);
            "trebol_11" -> setImageResource(R.drawable.trebol_11);
            "trebol_12" -> setImageResource(R.drawable.trebol_12);
            "trebol_13" -> setImageResource(R.drawable.trebol_13);
            "espadas_1" -> setImageResource(R.drawable.espadas_1);
            "espadas_2" -> setImageResource(R.drawable.espadas_2);
            "espadas_3" -> setImageResource(R.drawable.espadas_3);
            "espadas_4" -> setImageResource(R.drawable.espadas_4);
            "espadas_5" -> setImageResource(R.drawable.espadas_5);
            "espadas_6" -> setImageResource(R.drawable.espadas_6);
            "espadas_7" -> setImageResource(R.drawable.espadas_7);
            "espadas_8" -> setImageResource(R.drawable.espadas_8);
            "espadas_9" -> setImageResource(R.drawable.espadas_9);
            "espadas_10" -> setImageResource(R.drawable.espadas_10);
            "espadas_11" -> setImageResource(R.drawable.espadas_11);
            "espadas_12" -> setImageResource(R.drawable.espadas_12);
            "espadas_13" -> setImageResource(R.drawable.espadas_13);
        }

    }

    fun isEspecial(): Boolean {
        return this.efecto != null;
    }

    fun compatible(carta: Carta): Boolean {
        return this.palo == carta.palo
                || (this.numero == carta.numero && !this.isEspecial() && !carta.isEspecial())
                || (this.isEspecial() && carta.isEspecial() && this.efecto == carta.efecto);
    }
}