package cl.aiep.distribuidoraapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth // Asegúrate que este import esté

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var loginButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Inicializamos Firebase Auth con el mé
        auth = FirebaseAuth.getInstance()

        emailEditText = findViewById(R.id.editTextEmail)
        passwordEditText = findViewById(R.id.editTextPassword)
        registerButton = findViewById(R.id.buttonRegister)
        loginButton = findViewById(R.id.buttonLogin)

        registerButton.setOnClickListener {
            registrarUsuario()
        }

        loginButton.setOnClickListener {
            iniciarSesion()
        }
    }

    public override fun onStart() {
        super.onStart()
        // Revisamos si el usuario ya ha iniciado sesión al iniciar la pantalla.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // Si ya hay un usuario conectado, vamos directamente a la pantalla principal.
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Cerramos la pantalla de Login para que no se pueda volver a ella.
        }
    }

    private fun registrarUsuario() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese correo y contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "Registro exitoso.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(baseContext, "Falló el registro: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun iniciarSesion() {
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, ingrese correo y contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(baseContext, "Inicio de sesión exitoso.", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(baseContext, "Falló el inicio de sesión.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}