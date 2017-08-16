package pl.hypeapp.noiseview

import android.graphics.*

internal class NoiseRenderable(var bitmap: Bitmap?, var grainFps: Int, var scale: Float) {

    private val paint = Paint()

    private var shader: BitmapShader = BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)

    private var matrix: Matrix = Matrix()

    private var lastGrainOffset: Long

    init {
        shader.setLocalMatrix(matrix)
        paint.shader = shader
        paint.alpha = 144
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SCREEN)
        lastGrainOffset = System.currentTimeMillis()
    }

    fun draw(canvas: Canvas) {
        canvas.drawPaint(paint)
    }

    fun update() {
        if (lastGrainOffset + grainFps < System.currentTimeMillis()) {
            matrix.reset()
            matrix.setScale(scale, scale)
            bitmap?.let {
                matrix.postTranslate(MathHelper.randomRange(-it.width * 10f, it.width * 10f),
                        MathHelper.randomRange(-it.height * 10f, it.height * 10f))
            }
            shader.setLocalMatrix(matrix)
            lastGrainOffset = System.currentTimeMillis()
        }
    }

    fun destroy() = bitmap?.let {
        if (!it.isRecycled) {
            it.recycle()
            bitmap = null
        }
    }

    fun setNoiseIntensity(noiseIntensity: Float) {
        paint.alpha = (255f * noiseIntensity).toInt()
    }

}
