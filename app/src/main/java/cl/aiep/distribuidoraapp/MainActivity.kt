package cl.aiep.distribuidoraapp

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                startLocationUpdates()
            } else {
                Toast.makeText(this, "El permiso de ubicación es necesario.", Toast.LENGTH_LONG).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()
        database = Firebase.database.reference
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val logoutButton: Button = findViewById(R.id.buttonLogout)
        logoutButton.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Definimos qué hacer cuando recibimos una actualización de la ubicación
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations){
                    if (location != null) {
                        // Una vez que tenemos la ubicación, la guardamos y detenemos las actualizaciones
                        guardarUbicacionEnFirebase(location)
                        fusedLocationClient.removeLocationUpdates(locationCallback)
                        break // Salimos del bucle
                    }
                }
            }
        }

        // Solicitamos la ubicación
        solicitarActualizacionesDeUbicacion()
    }

    override fun onPause() {
        super.onPause()
        // Es una buena práctica detener las actualizaciones si la app no está visible
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    private fun solicitarActualizacionesDeUbicacion() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                startLocationUpdates()
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }

    private fun startLocationUpdates() {
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY, 10000)
            .setWaitForAccurateLocation(false)
            .setMinUpdateIntervalMillis(5000)
            .setMaxUpdateDelayMillis(15000)
            .build()

        try {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
        } catch (e: SecurityException) {
            Log.e("GPS", "Error de seguridad.", e)
        }
    }

    private fun guardarUbicacionEnFirebase(location: android.location.Location) {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val userLocation = mapOf("latitud" to location.latitude, "longitud" to location.longitude)
            database.child("ubicaciones").child(userId).setValue(userLocation)
                .addOnSuccessListener {
                    Toast.makeText(this, "Ubicación guardada con éxito.", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error al guardar ubicación.", Toast.LENGTH_SHORT).show()
                }
        }
    }
}