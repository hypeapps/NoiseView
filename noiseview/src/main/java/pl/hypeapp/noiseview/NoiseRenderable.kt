package pl.hypeapp.noiseview

import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Shader

internal class NoiseRenderable(
    var bitmap: Bitmap? ,
    var grainFps: Int,
    var scale: Float,
    intensity: Float
) {

    private val paint = Paint()

    private var shader: BitmapShader =
        BitmapShader(requireNotNull(bitmap), Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)

    private var matrix: Matrix = Matrix()

    private var lastGrainOffset: Long

    var noiseIntensity: Float = 0f
        set(value) {
            paint.alpha = (255f * value).toInt()
        }

    init {
        shader.setLocalMatrix(matrix)
        paint.shader = shader
        paint.alpha = 144
        paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SCREEN)
        lastGrainOffset = System.currentTimeMillis()
        noiseIntensity = intensity
    }

    fun draw(canvas: Canvas) {
        canvas.drawPaint(paint)
    }

    fun update() {
        if (lastGrainOffset + grainFps < System.currentTimeMillis()) {
            matrix.reset()
            matrix.setScale(scale, scale)
            bitmap?.let {
                matrix.postTranslate(
                    MathHelper.randomRange(-it.width * 10f, it.width * 10f),
                    MathHelper.randomRange(-it.height * 10f, it.height * 10f)
                )
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
}
