package pl.hypeapp.sample

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSeekBarNoiseIntensityChangedListener()
        setSeekBarGrainFpsChangedListener()
        setSeekBarNoiseScaleChangedListener()
        setOnCheckedClearDrawableListener()
        setOnCheckedPauseStateListener()
    }

    private fun setSeekBarNoiseIntensityChangedListener() {
        seek_bar_noise_intensity.progress = (seek_bar_noise_intensity.max * noise_view.noiseIntensity).toInt()
        text_view_noise_intensity_value.text = noise_view.noiseIntensity.toString()
        seek_bar_noise_intensity.setOnSeekBarChangeListener(object : OnSeekBarProgressChanged {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                val noiseIntensity: Float = (0.01 * progress).toFloat()
                noise_view.noiseIntensity = noiseIntensity
                text_view_noise_intensity_value.text = noiseIntensity.toString()
            }
        })
    }

    private fun setSeekBarGrainFpsChangedListener() {
        seek_bar_noise_grain_fps.progress = noise_view.grainFps
        seek_bar_noise_grain_fps.max = 250
        text_view_grain_fps_value.text = noise_view.grainFps.toString()
        seek_bar_noise_grain_fps.setOnSeekBarChangeListener(object : OnSeekBarProgressChanged {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                val grainFps: Int = progress
                noise_view.grainFps = grainFps
                text_view_grain_fps_value.text = progress.toString()
            }
        })
    }

    private fun setSeekBarNoiseScaleChangedListener() {
        seek_bar_noise_scale.progress = (seek_bar_noise_scale.max * noise_view.noiseScale).toInt()
        text_view_noise_scale_value.text = noise_view.noiseScale.toString()
        seek_bar_noise_scale.setOnSeekBarChangeListener(object : OnSeekBarProgressChanged {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                val noiseScale: Float = (0.01 * progress).toFloat()
                noise_view.noiseScale = noiseScale
                text_view_noise_scale_value.text = noiseScale.toString()
            }
        })
    }

    private fun setOnCheckedClearDrawableListener() = switch_clear_drawable.setOnCheckedChangeListener {
        _, checked ->
        if (checked)
            noise_view.setImageDrawable(null)
        else
            noise_view.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.wheel))
    }


    private fun setOnCheckedPauseStateListener() = switch_pause_state.setOnCheckedChangeListener {
        _, checked ->
        noise_view.pause = checked
    }

}
