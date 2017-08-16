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

    private var isRenderableAlreadyInit: Boolean = false

    var pause = false

    var noiseIntensity = 0f
        set(value) {
            field = value
            renderables.forEach {
                it.noiseIntensity = noiseIntensity
            }
        }

    var noiseScale = 0f
        set(value) {
            field = value
            renderables.forEach {
                it.scale = noiseScale
            }
        }

    var grainFps: Int = 0
        set(value) {
            field = value
            renderables.forEach {
                it.grainFps = grainFps
            }
        }

    init {
        attrs?.let {
            val typedArray: TypedArray = context.obtainStyledAttributes(it, R.styleable.NoiseView)
            initAttrs(typedArray)
            typedArray.recycle()
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (!isRenderableAlreadyInit) {
            initRenderables()
            isRenderableAlreadyInit = true
        }
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

    private fun initAttrs(typedArray: TypedArray) {
        this.pause = typedArray.getBoolean(R.styleable.NoiseView_paused, DEFAULT_PAUSED)
        this.grainFps = typedArray.getInteger(R.styleable.NoiseView_grainFps, DEFAULT_GRAIN_FPS)
        this.noiseIntensity = typedArray.getFloat(R.styleable.NoiseView_noiseIntensity, DEFAULT_NOISE_INTENSITY)
        this.noiseScale = typedArray.getFloat(R.styleable.NoiseView_noiseScale, DEFAULT_NOISE_SCALE)
    }

    private fun initRenderables() {
        setLayerType(View.LAYER_TYPE_HARDWARE, null)
        val noiseScratch = BitmapFactory.decodeResource(resources, R.drawable.noise_scratch)
        val noiseReg = BitmapFactory.decodeResource(resources, R.drawable.noise)
        noiseScratchEffect = NoiseRenderable(noiseScratch, grainFps, noiseScale, noiseIntensity)
        renderables.add(noiseScratchEffect)
        noise = NoiseRenderable(noiseReg, grainFps, noiseScale, noiseIntensity)
        renderables.add(noise)
    }

    private fun destroyResources() {
        renderables.forEach {
            it.destroy()
        }
    }

    private companion object {
        val DEFAULT_GRAIN_FPS = 90
        val DEFAULT_NOISE_INTENSITY = 0.1f
        val DEFAULT_NOISE_SCALE = 0.6f
        val DEFAULT_PAUSED = false
    }

}
