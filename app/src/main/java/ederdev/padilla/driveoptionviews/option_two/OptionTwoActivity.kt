package ederdev.padilla.driveoptionviews.option_two

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import ederdev.padilla.driveoptionviews.R
import ederdev.padilla.driveoptionviews.option_one.OptionOneActivity

import kotlinx.android.synthetic.main.activity_option_two.*
import android.widget.Toast



class OptionTwoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option_two)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        this.window.statusBarColor = Color.parseColor("#303F9F")
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {goBack()}
        }
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        goBack()
    }

    fun goBack(){
        startActivity(Intent(this, OptionOneActivity::class.java))
        finish()
    }
}
