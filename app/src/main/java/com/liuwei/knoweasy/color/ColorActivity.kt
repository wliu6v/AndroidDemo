package com.liuwei.knoweasy.color

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.jaredrummler.android.colorpicker.ColorPickerDialog
import com.jaredrummler.android.colorpicker.ColorPickerDialogListener
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.util.*

/**
 * Created by liuwei on 2017/8/3.
 */
class ColorActivity : AppCompatActivity(), ColorPickerDialogListener {
    lateinit var view: UI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = UI()
        view.setContentView(this@ColorActivity)
        initView()
    }

    fun initView() {
        view.colorPicker.onClick {
            inputMethodManager.hideSoftInputFromWindow(view.colorInputTv.windowToken, 0)
            ColorPickerDialog.newBuilder().setShowAlphaSlider(false).show(this@ColorActivity)
        }

        view.paintBtn.onClick {
            inputMethodManager.hideSoftInputFromWindow(view.colorInputTv.windowToken, 0)
            try {
                var colorString = view.colorInputTv.text.toString()
                if (!colorString.startsWith("#")) {
                    colorString = "#$colorString"
                }
                val color = Color.parseColor(colorString)
                paintColor(color)
            } catch(e: Exception) {
                //
            }
        }
    }

    override fun onDialogDismissed(dialogId: Int) {
        //
    }

    @SuppressLint("SetTextI18n")
    override fun onColorSelected(dialogId: Int, color: Int) {
        paintColor(color)
        var colorStr = Integer.toHexString(color)
        if (colorStr.length == 8) {
            colorStr = colorStr.substring(2)
        }
        view.colorInputTv.setText("#$colorStr")
    }

    fun paintColor(color: Int) {
        addMsg("-------- ${Integer.toHexString(color)} -------")

        val hsv = kotlin.FloatArray(3)
        Color.colorToHSV(color, hsv)
        val grayLevel = (hsv[0] * 0.299 + hsv[1] * 0.587 + hsv[2] * 0.114)
        addMsg("The HSV GrayLevel is : $grayLevel")

        val darkerHsv = kotlin.FloatArray(3)
        darkerHsv[0] = hsv[0]
        darkerHsv[1] = hsv[1] + 0.2f
        darkerHsv[2] = hsv[2] - 0.2f

        val lighterHsv = kotlin.FloatArray(3)
        lighterHsv[0] = hsv[0]
        lighterHsv[1] = hsv[1] - 0.2f
        lighterHsv[2] = hsv[2] + 0.2f

        val darkerColor = Color.HSVToColor(darkerHsv)
        val lighterColor = Color.HSVToColor(lighterHsv)

        val tvList = Arrays.asList(view.darkerView, view.colorView, view.lighterView)
        val colorList = Arrays.asList(darkerColor, color, lighterColor)
        for (i in tvList.indices) {
            val t = tvList[i]
            val c = colorList[i]
            t.backgroundColor = c
            t.text = Integer.toHexString(c)
            t.textColor = ContextCompat.getColor(this@ColorActivity, if (isLighterColor(c)) android.R.color.black else android.R.color.white)
        }
    }

    fun isLighterColor(color: Int): Boolean {
//        val hsv = kotlin.FloatArray(3)
//        Color.colorToHSV(color, hsv)
//        val gl1 = hsv[0] * 0.299 + hsv[1] * 0.587 + hsv[2] * 0.114
//        addMsg("GL1 = $gl1 : ${hsv[0]}, ${hsv[1]}, ${hsv[2]}")
//        return g1  < 65

        val r = color shr 16 and 0xFF
        val g = color shr 8 and 0xFF
        val b = color and 0xFF
        val y = r * 0.299 + g * 0.587 + b * 0.114

        addMsg("RGB = ${"%3d".format(r)}, ${"%3d".format(g)}, ${"%3d".format(b)}, Y = ${"%.3f".format(y)}")

        return y > 157
    }

    @SuppressLint("SetTextI18n")
    fun addMsg(msg: String) {
        view.statusTv.text = "${view.statusTv.text}\n$msg"
    }


    inner class UI : AnkoComponent<ColorActivity> {
        lateinit var colorPicker: Button
        lateinit var colorInputTv: EditText
        lateinit var paintBtn: Button
        lateinit var colorView: TextView
        lateinit var darkerView: TextView
        lateinit var lighterView: TextView
        lateinit var statusTv: TextView

        override fun createView(ui: AnkoContext<ColorActivity>): View {
            return with(ui) {
                verticalLayout {
                    colorPicker = button("Pick a color")
                    colorInputTv = editText()
                    paintBtn = button("paint")
                    linearLayout {
                        orientation = LinearLayout.HORIZONTAL
                        darkerView = textView("Darker").lparams {
                            width = dip(120)
                            height = dip(40)
                        }
                        colorView = textView("Origin").lparams {
                            width = dip(120)
                            height = dip(40)
                        }
                        lighterView = textView("Ligher").lparams {
                            width = dip(120)
                            height = dip(40)
                        }
                    }
                    scrollView { statusTv = textView() }
                }
            }
        }
    }
}
