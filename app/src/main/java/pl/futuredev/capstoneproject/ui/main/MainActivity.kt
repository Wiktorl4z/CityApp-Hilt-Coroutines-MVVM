package pl.futuredev.capstoneproject.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import pl.futuredev.capstoneproject.R
import pl.futuredev.capstoneproject.ui.AuthActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun openFavouritesCity() {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favCity -> {
                openFavouritesCity()
            }

            R.id.signOut -> {
                FirebaseAuth.getInstance().signOut()
                Intent(this, AuthActivity::class.java).also {
                    startActivity(it)
                }
                finish()
            }

        }
        return super.onOptionsItemSelected(item)


    }
}