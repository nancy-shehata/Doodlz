package com.example.doodlz

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DoodleView(context:Context,attrs: AttributeSet) : View(context,attrs) {
    private var paint: Paint = Paint().apply {
        isAntiAlias = true
        color = Color.RED // default color
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 20f //default stroke width
    }

    private var path: Path = Path()
    private val paths = mutableListOf<Pair<Path, Paint>>()
    private var currentColor = Color.RED
    private var bitmap: Bitmap? = null
    private var canvas: Canvas? = null
    private val backgroundColor = Color.WHITE
    private val colors = arrayOf(Color.RED, Color.BLUE, Color.YELLOW, Color.GREEN)
    private var currentColorIndex = 0

    init {
        //Additional intialization if needed

    }

    fun changeColor(){
        currentColorIndex = (currentColorIndex +1) % colors.size
        currentColor = colors [currentColorIndex]
        paint = Paint(paint) //copy current paint settings
        paint.color = currentColor // Apply new color
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w,h,oldw,oldh)
        if(w>0 &&h>0){
            bitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888)
            canvas= Canvas(bitmap!!)
            canvas?.drawColor(backgroundColor)
        }
    }

    override fun onDraw(canvas:Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap!!,0f,0f,null)
        paths.forEach{(path,paint)->
        }

    }

    override fun onTouchEvent(event:MotionEvent):Boolean{
        val x=event.x
        val y= event.y
    when (event.action){
        MotionEvent.ACTION_DOWN->{
            path=Path()
            paint=Paint(paint) //Copy current paint settings
            paths.add(path to paint)
            path.moveTo(x,y)
        }
        MotionEvent.ACTION_MOVE-> {
            path.lineTo(x,y)
        }
        MotionEvent.ACTION_UP-> {
            path.lineTo(x,y)
            canvas?.drawPath(path,paint) //Draw final path on the canvas
        }
    }
        invalidate() //Invalidate to request a redraw
        return true // return true to indicate the event was handled

        }
    }
