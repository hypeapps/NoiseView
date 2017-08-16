package pl.hypeapp.noiseview

import android.widget.SeekBar

interface OnSeekBarProgressChanged : SeekBar.OnSeekBarChangeListener {

    override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean)

    override fun onStartTrackingTouch(p0: SeekBar?) {}

    override fun onStopTrackingTouch(p0: SeekBar?) {}

}
