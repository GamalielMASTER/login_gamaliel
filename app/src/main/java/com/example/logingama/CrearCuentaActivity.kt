package com.example.logingama

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CrearCuentaActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cuenta)
        val txtNombre_Nuevo : TextView = findViewById(R.id.edtNombreNuevo)
        val txtEmail_nuevo : TextView = findViewById(R.id.edtEmailNuevo)
        val txtpassword1 :TextView = findViewById(R.id.edtPasswordNuevo1)
        val txtpassword2 :TextView = findViewById(R.id.edtPasswordNuevo2)
        val btnCrear : TextView = findViewById(R.id.btnCrearCuentaNueva)
        btnCrear.setOnClickListener()
        {
            val pass1 = txtpassword1.text.toString()
            val pass2 = txtpassword2.text.toString()
            if (pass1.equals(pass2))

            {
                createAccount(txtEmail_nuevo.text.toString(), txtpassword1.text.toString())
            }
            else
            {
                Toast.makeText(baseContext, "error: las contraseñas no son iguales",Toast.LENGTH_SHORT).show()
                txtpassword1.requestFocus()
            }

        }
        firebaseAuth= Firebase.auth
    }

    private fun createAccount(email: String, password: String)
    {
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) {task ->
                if (task.isSuccessful)
                {
                    Toast.makeText(baseContext,"cuenta creada exitosamente", Toast.LENGTH_SHORT).show()

                }
                else
                {
                    Toast.makeText(baseContext,"algo salio mal "+ task.exception, Toast.LENGTH_SHORT).show()

                }

            }
    }
}