package com.gmail.lyohakasianik.leaguestatistics.customView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.gmail.lyohakasianik.leaguestatistics.R

class CircleDiagram : View {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textNumberPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val rectF = RectF()
    private var cx = 0f
    private var cy = 0f
    private var radius = 0f
    private var sumNumbersGetArray = 0f

    private var arrayProc = mutableListOf<Float>()

    var arrayNumbersText = mutableListOf<Int>()

    var arrayNumbers: List<Int> = emptyList()
        set(value) {
            field = value
            invalidate()
        }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )


    init {

        paint.style = Paint.Style.FILL

        textNumberPaint.color = ContextCompat.getColor(context, R.color.colorTextSearchSummoner)
        textNumberPaint.textSize = resources.getDimensionPixelSize(R.dimen.circle_diagram_text_size).toFloat()
        textNumberPaint.fontFeatureSettings = resources.getResourceName(R.font.comfortaa_regular)

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        cx = width / 2f
        cy = height / 2f
        radius = Math.min(width, height) / 2f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        calculatedProcent()

        val sliceAngleWin = arrayProc[0]
        val sliceAngleLoose = arrayProc[1]
        paint.color = ContextCompat.getColor(context, R.color.colorGreen)
        rectF.set(cx - radius, cy - radius, cx + radius, cy + radius)
        canvas.drawArc(rectF, 0f, sliceAngleWin, true, paint)
        paint.color = ContextCompat.getColor(context, R.color.colorPink)
        canvas.drawArc(rectF, sliceAngleWin, sliceAngleLoose, true, paint)
        canvas.drawText(returnWinrate(arrayNumbers)
            , cx - textNumberPaint.textSize,
            cy + textNumberPaint.textSize / 3, textNumberPaint
        )
    }

    private fun returnWinrate(list: List<Int>): String {
        return "${list[0]*100 / list.sum()}%"
    }

    private fun calculatedProcent() {
        arrayNumbersText = mutableListOf()
        arrayProc = mutableListOf()
        arrayNumbersText.addAll(arrayNumbers)
        sumNumbersGetArray = arrayNumbers.sum().toFloat()
        for (i in arrayNumbers) {
            arrayProc.add(i * 360 / sumNumbersGetArray)
        }
    }
}