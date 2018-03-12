package ederdev.padilla.driveoptionviews.option_two

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import ederdev.padilla.driveoptionviews.R
import ederdev.padilla.driveoptionviews.option_one.OptionOneActivity

import kotlinx.android.synthetic.main.activity_option_two.*
import kotlinx.android.synthetic.main.fragment_select_time.*
import ederdev.padilla.driveoptionviews.option_two.fragment.SelectTimeFragment


class OptionTwoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_option_two)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        this.window.statusBarColor = Color.parseColor("#303F9F")
        fab.setOnClickListener { view ->
            showFragment()
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
    fun showFragment(){
        val fm = supportFragmentManager
        val editNameDialog = SelectTimeFragment()
        editNameDialog.show(fm, "dialogomuestra")
    }
}
