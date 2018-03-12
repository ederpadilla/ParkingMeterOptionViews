package ederdev.padilla.driveoptionviews.option_two.fragment

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ederdev.padilla.driveoptionviews.R
import ederdev.padilla.driveoptionviews.util.GetEquivalence
import ederdev.padilla.driveoptionviews.util.TimeEquivalence
import ederdev.padilla.driveoptionviews.view.SeekArc
import kotlinx.android.synthetic.main.fragment_select_time.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by ederpadilla on 12/03/18.
 */

class SelectTimeFragment : DialogFragment() , SeekArc.OnSeekArcChangeListener,View.OnClickListener{



    var myHour : String = ""
    var myDate : String = ""
    val simpleDateFormat = SimpleDateFormat("HH:mm")
    var dateHour :Date?= null

    private var time: Long? = null

    private lateinit var d2: Date

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_select_time, container)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mImgFragmentSubstract.setOnClickListener(this)
        mImgFragmentPlus.setOnClickListener(this)
        initViews()
    }

    private fun initViews() {
        mTvFragmentHour.text = ""
        mTvFragmentDate.text = ""
        mSeekAcrFragment.progress = 2
        mTvFragmentAmount.text = "${mSeekAcrFragment.progress}$"
        val formatHour = SimpleDateFormat("HH:mm", Locale.getDefault())
        val formatDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        myHour = formatHour.format(Date())
        myDate = formatDate.format(Date())
        mTvFragmentHour.text = myHour
        mTvFragmentDate.text = myDate
        dateHour = simpleDateFormat.parse(myHour)
        time = dateHour?.time
        time = time?.plus((2 * 60 * 60 * 1000).toLong())
        d2 = Date(time!!)
        mSeekAcrFragment.setOnSeekArcChangeListener(this)
    }

    override fun onProgressChanged(seekArc: SeekArc, progress: Int, fromUser: Boolean) {
        mTvFragmentAmount.text = "$progress$"
        if (progress<2){
            seekArc.progress = 2
            val formatHour = SimpleDateFormat("HH:mm", Locale.getDefault())
            myHour = formatHour.format(Date())
            mTvFragmentHour.text = myHour
        }else {
            val equivalence = GetEquivalence.equivalences.get(progress)
            checkForSetDate(equivalence)
        }
    }

    private fun checkForSetDate(equivalence: TimeEquivalence) {
        sumMinutes(equivalence.minutes)
    }

    override fun onStartTrackingTouch(seekArc: SeekArc) {
    }

    override fun onStopTrackingTouch(seekArc: SeekArc) {
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
        mTvFragmentHour.text = departureHour
    }
    fun plusFragmentAmount(){
        if (mSeekAcrFragment.progress==58){
            mSeekAcrFragment.progress = 2
        }else {
            mSeekAcrFragment.progress = mSeekAcrFragment.progress + 1
        }
    }
    fun substractFragmentAmount(){
        if (mSeekAcrFragment.progress>2){
            mSeekAcrFragment.progress = mSeekAcrFragment.progress-1
        }
    }
    override fun onClick(view: View?) {
        when(view!!.id){
            R.id.mImgFragmentPlus ->{
                plusFragmentAmount()
            }
            R.id.mImgFragmentSubstract ->{
                substractFragmentAmount()
            }
        }
    }
}
