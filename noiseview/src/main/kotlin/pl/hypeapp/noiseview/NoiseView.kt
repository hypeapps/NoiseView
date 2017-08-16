package pl.hypeapp.noiseview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView

class NoiseView(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : ImageView(context, attrs, defStyleAttr) {

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    private val renderables: ArrayList<NoiseRenderable> = arrayListOf()

    private lateinit var noiseScratchEffect: NoiseRenderable

    private lateinit var noise: NoiseRenderable

    private var pause = false

    private var grainFps = 0

    private var noiseIntensity = 0f

    private var noiseScale = 0f

    init {
        attrs?.let {
            val typedArray: TypedArray = context.obtainStyledAttributes(it, R.styleable.NoiseView)
            initAttrs(typedArray)
            typedArray.recycle()
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        initRenderables()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (renderables.isEmpty() && width != 0) initRenderables()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        destroyResources()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (!pause) {
            renderables.forEach {
                it.draw(canvas)
                it.update()
            }
        }
        invalidate()
    }

    fun setGrainFps(grainFps: Int) {
        this.grainFps = grainFps
        renderables.forEach {
            it.grainFps = grainFps
        }
    }

    fun setScale(noiseScale: Float) {
        this.noiseScale = noiseScale
        renderables.forEach {
            it.scale = noiseScale
        }
    }

    fun setNoiseIntensity(noiseIntensity: Float) {
        this.noiseIntensity = noiseIntensity
        renderables.forEach {
            it.setNoiseIntensity(noiseIntensity)
        }
    }

    fun pause() {
        this.pause = true
    }

    fun resume() {
        this.pause = false
    }

    private fun initAttrs(typedArray: TypedArray) {
        this.pause = typedArray.getBoolean(R.styleable.NoiseView_paused, false)
        this.grainFps = typedArray.getInteger(R.styleable.NoiseView_grainFps, 90)
        this.noiseIntensity = typedArray.getFloat(R.styleable.NoiseView_noiseIntensity, 0.1f)
        this.noiseScale = typedArray.getFloat(R.styleable.NoiseView_noiseScale, 0.6f)
    }

    private fun initRenderables() {
        setLayerType(View.LAYER_TYPE_HARDWARE, null)
        val noiseScratch = BitmapFactory.decodeResource(resources, R.drawable.noise_scratch)
        val noiseReg = BitmapFactory.decodeResource(resources, R.drawable.noise)
        noiseScratchEffect = NoiseRenderable(noiseScratch, grainFps, noiseScale)
        renderables.add(noiseScratchEffect)
        noise = NoiseRenderable(noiseReg, grainFps, noiseScale)
        renderables.add(noise)
        setNoiseIntensity(noiseIntensity)
    }

    private fun destroyResources() {
        renderables.forEach {
            it.destroy()
        }
    }

}
