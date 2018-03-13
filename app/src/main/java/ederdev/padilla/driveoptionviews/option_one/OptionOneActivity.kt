package ederdev.padilla.driveoptionviews.option_one

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import ederdev.padilla.driveoptionviews.R
import ederdev.padilla.driveoptionviews.option_two.OptionTwoActivity
import ederdev.padilla.driveoptionviews.util.GetEquivalence
import ederdev.padilla.driveoptionviews.util.GetTimes
import ederdev.padilla.driveoptionviews.util.TimeEquivalence
import ederdev.padilla.driveoptionviews.util.TimeWithMinutesAnHour
import ederdev.padilla.driveoptionviews.view.SeekArc
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.circular_seekbar_view.*
import kotlinx.android.synthetic.main.content_main.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.Logger


class OptionOneActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,SeekArc.OnSeekArcChangeListener {


    val MAX_AMOUNT = 48

    val numbers: IntArray = intArrayOf(3,7,11,15,19,23,28,32,36,40,44,48)
    var myHour : String = ""
    var myDate : String = ""
    val simpleDateFormat = SimpleDateFormat("HH:mm")
    var dateHour :Date?= null

    private var time: Long? = null

    private lateinit var d2: Date


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
        val colorGray = Color.parseColor("#4d4d4d") //The color u want
        mImgArrow1.setColorFilter(colorGray)
        mImgArrow2.setColorFilter(colorGray)
        mSeekAcr.progress = 2
        mTvAmount.text = "$ ${mSeekAcr.progress}.00"
        mTvHourAprox.text = "00"
        mTvMinAprox.text = "12"
        val formatHour = SimpleDateFormat("HH:mm", Locale.getDefault())
        val formatDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        myHour = formatHour.format(Date())
        myDate = formatDate.format(Date())
        mTvHour.text = myHour
        mTvDate.text = myDate
        dateHour = simpleDateFormat.parse(myHour)
        time = dateHour?.time
        time = time?.plus((2 * 60 * 60 * 1000).toLong())
        d2 = Date(time!!)
        mSeekAcr.setOnSeekArcChangeListener(this)
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
           /* R.id.nav_slideshow -> {
                val optionSurprise = Intent(this,SurpriseActivity::class.java)
                startActivity(optionSurprise)
                this.finish()
            }*/
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
    override fun onProgressChanged(seekArc: SeekArc, progress: Int, fromUser: Boolean) {
        mTvAmount.text = "$ $progress.00"

        if (progress<2){
            seekArc.progress = 2
            val formatHour = SimpleDateFormat("HH:mm", Locale.getDefault())
            myHour = formatHour.format(Date())
            mTvHour.text = myHour
        }else {
            val equivalence = GetEquivalence.equivalences.get(progress)
            val equivalenceTime = GetTimes.getTimeAndHour.get(progress)
            checkForSetTime(equivalenceTime)
            checkForSetDate(equivalence)
        }
    }

    private fun checkForSetTime(equivalenceTime: TimeWithMinutesAnHour) {
        mTvHourAprox.text = equivalenceTime.hour
        mTvMinAprox.text = equivalenceTime.minute
    }

    private fun checkForSetDate(equivalence: TimeEquivalence) {
        sumMinutes(equivalence.minutes)
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

    fun sumMinutes(minutes : Int) {
        val completeDate = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val calendar = Calendar.getInstance()
        val now = Date()
        val dateAsString = completeDate.format(now)
        val dateFromString = completeDate.parse(dateAsString)
        calendar.time = dateFromString
        calendar.add(Calendar.MINUTE, minutes)
        val departureDate = calendar.time
        val departureHour = "${calendar.get(Calendar.HOUR)}:${calendar.get(Calendar.MINUTE)}"
        mTvHour.text = departureHour
    }
}
