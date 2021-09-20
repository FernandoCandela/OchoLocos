package pe.edu.ulima.pm.ocholocos_asig1.model

class Carta (var numero: Int, var palo : String , var efecto: Efecto? ){

    fun isEspecial(): Boolean {
        return this.efecto != null;
    }

    fun compatible(carta: Carta):Boolean{
        return this.palo == carta.palo
                || (this.numero == carta.numero && !this.isEspecial() && !carta.isEspecial())
                || (this.isEspecial() && carta.isEspecial() && this.efecto == carta.efecto);
    }
}