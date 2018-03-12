package ederdev.padilla.driveoptionviews.option_two.fragment

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ederdev.padilla.driveoptionviews.R
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
            checkForDate(progress)
        }
    }

    override fun onStartTrackingTouch(seekArc: SeekArc) {
    }

    override fun onStopTrackingTouch(seekArc: SeekArc) {
    }
    private fun checkForDate(progress: Int) {
        when(progress){
            2->{sumMinutes(12)}
            3->{sumMinutes(19)}
            4->{sumMinutes(25)}
            5->{sumMinutes(31)}
            6->{sumMinutes(37)}
            7->{sumMinutes(44)}
            8->{sumMinutes(50)}
            9->{sumMinutes(56)}
            10->{sumMinutes(62)}
            11->{sumMinutes(69)}
            12->{sumMinutes(75)}
            13->{sumMinutes(81)}
            14->{sumMinutes(87)}
            15->{sumMinutes(94)}
            16->{sumMinutes(100)}
            17->{sumMinutes(106)}
            18->{sumMinutes(112)}
            19->{sumMinutes(119)}
            20->{sumMinutes(125)}
            21->{sumMinutes(131)}
            22->{sumMinutes(137)}
            23->{sumMinutes(144)}
            24->{sumMinutes(150)}
            25->{sumMinutes(156)}
            26->{sumMinutes(162)}
            27->{sumMinutes(168)}
            28->{sumMinutes(175)}
            29->{sumMinutes(181)}
            30->{sumMinutes(187)}
            31->{sumMinutes(193)}
            32->{sumMinutes(200)}
            33->{sumMinutes(206)}
            34->{sumMinutes(212)}
            35->{sumMinutes(218)}
            36->{sumMinutes(225)}
            37->{sumMinutes(231)}
            38->{sumMinutes(237)}
            39->{sumMinutes(243)}
            40->{sumMinutes(250)}
            41->{sumMinutes(256)}
            42->{sumMinutes(262)}
            43->{sumMinutes(268)}
            44->{sumMinutes(275)}
            45->{sumMinutes(281)}
            46->{sumMinutes(287)}
            47->{sumMinutes(293)}
            48->{sumMinutes(300)}
        }
    }
    fun sumMinutes(minutes : Int) {
        val completeDate = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val calendar = Calendar.getInstance()
        val now = Date()

        val dateAsString = completeDate.format(now) //"08.01.2013"
        val dateFromString = completeDate.parse(dateAsString)
        calendar.time = dateFromString //tuFechaBase es un Date;
        calendar.add(Calendar.MINUTE, minutes) //minutosASumar es int.
        //calendar.add(Calendar.HOUR, horasASumar) //horasASumar es int.
//lo que más quieras sumar
        val departureDate = calendar.time //Y ya tienes la fecha sumada.
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
