package ederdev.padilla.driveoptionviews.option_one

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import ederdev.padilla.driveoptionviews.R
import ederdev.padilla.driveoptionviews.option_surprise.SurpriseActivity
import ederdev.padilla.driveoptionviews.option_two.OptionTwoActivity
import ederdev.padilla.driveoptionviews.view.SeekArc
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.circular_seekbar_view.*
import kotlinx.android.synthetic.main.content_main.*

class OptionOneActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,SeekArc.OnSeekArcChangeListener {


    val MAX_AMOUNT = 58

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setUpBasicView()
    }

    private fun setUpBasicView() {

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
        nav_view.menu.getItem(0).setChecked(true)
        nav_view.menu.getItem(1).setChecked(true)
        nav_view.menu.getItem(2).setChecked(true)
        mSeekAcr.setOnSeekArcChangeListener(this)
        mSeekAcr.progress = 2
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
            }
            R.id.nav_gallery -> {
                val optionTwoIntent = Intent(this,OptionTwoActivity::class.java)
                startActivity(optionTwoIntent)
                this.finish()
            }
            R.id.nav_slideshow -> {
                val optionSurprise = Intent(this,SurpriseActivity::class.java)
                startActivity(optionSurprise)
                this.finish()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    override fun onProgressChanged(seekArc: SeekArc, progress: Int, fromUser: Boolean) {
        mTvAmount.text = "$progress$"
        if (progress==1){
            seekArc.progress = 2
        }
    }

    override fun onStartTrackingTouch(seekArc: SeekArc) {
    }

    override fun onStopTrackingTouch(seekArc: SeekArc) {
    }

    fun plusAmount(view : View){
        if (mSeekAcr.progress==58){
            mSeekAcr.progress = 2
        }else {
            mSeekAcr.progress = mSeekAcr.progress + 1
        }
    }
    fun substractAmount(view :View){
        if (mSeekAcr.progress>2){
            mSeekAcr.progress = mSeekAcr.progress-1
        }
    }
}
