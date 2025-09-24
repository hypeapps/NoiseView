package pl.hypeapp.noiseview.sample

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import pl.hypeapp.noiseview.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        setSeekBarNoiseIntensityChangedListener()
        setSeekBarGrainFpsChangedListener()
        setSeekBarNoiseScaleChangedListener()
        setOnCheckedClearDrawableListener()
        setOnCheckedPauseStateListener()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_github) {
            val intent: Intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://github.com/hypeapps/NoiseView")
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setSeekBarNoiseIntensityChangedListener(): Unit = with(binding) {
        seekBarNoiseIntensity.progress = (seekBarNoiseIntensity.max * noiseView.noiseIntensity).toInt()
        textViewNoiseIntensityValue.text = noiseView.noiseIntensity.toString()
        seekBarNoiseIntensity.setOnSeekBarChangeListener(object : OnSeekBarProgressChanged {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                val noiseIntensity: Float = (0.01 * progress).toFloat()
                noiseView.noiseIntensity = noiseIntensity
                textViewNoiseIntensityValue.text = noiseIntensity.toString()
            }
        })
    }

    private fun setSeekBarGrainFpsChangedListener(): Unit = with(binding) {
        seekBarNoiseGrainFps.progress = noiseView.grainFps
        seekBarNoiseGrainFps.max = 250
        textViewGrainFpsValue.text = noiseView.grainFps.toString()
        seekBarNoiseGrainFps.setOnSeekBarChangeListener(object : OnSeekBarProgressChanged {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                val grainFps: Int = progress
                noiseView.grainFps = grainFps
                textViewGrainFpsValue.text = progress.toString()
            }
        })
    }

    private fun setSeekBarNoiseScaleChangedListener(): Unit = with(binding) {
        seekBarNoiseScale.progress = (seekBarNoiseScale.max * noiseView.noiseScale).toInt()
        textViewNoiseScaleValue.text = noiseView.noiseScale.toString()
        seekBarNoiseScale.setOnSeekBarChangeListener(object : OnSeekBarProgressChanged {
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                val noiseScale: Float = (0.01 * progress).toFloat()
                noiseView.noiseScale = noiseScale
                textViewNoiseScaleValue.text = noiseScale.toString()
            }
        })
    }

    private fun setOnCheckedClearDrawableListener(): Unit = with(binding) {
        switchClearDrawable
            .setOnCheckedChangeListener { _, checked ->
                if (checked) {
                    noiseView.setImageDrawable(null)
                } else {
                    noiseView.setImageDrawable(
                        ContextCompat.getDrawable(this@MainActivity, R.drawable.wheel)
                    )
                }
            }
    }

    private fun setOnCheckedPauseStateListener(): Unit = with(binding) {
        switchPauseState.setOnCheckedChangeListener { _, checked ->
            noiseView.pause = checked
        }
    }
}
