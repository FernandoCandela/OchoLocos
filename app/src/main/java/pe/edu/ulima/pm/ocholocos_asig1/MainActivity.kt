package pe.edu.ulima.pm.ocholocos_asig1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val butJugar : Button = findViewById(R.id.butJugar)
        butJugar.setOnClickListener {
            val intent : Intent = Intent(this, GameActivity::class.java)
            startActivityForResult(intent, 10)
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 10) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "El Ganador es :  ", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Juego cancelado :(", Toast.LENGTH_LONG).show()

            }
        }
    }
}