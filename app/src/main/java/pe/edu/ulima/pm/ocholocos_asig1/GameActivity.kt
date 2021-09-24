package pe.edu.ulima.pm.ocholocos_asig1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        findViewById<Button>(R.id.butCancelar).setOnClickListener { v: View ->
            setResult(RESULT_CANCELED)
            finish()
        }
    }
}