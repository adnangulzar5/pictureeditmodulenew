package com.example.pictureeditmodule

import android.app.Dialog
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import ja.burhanrashid52.photoeditor.OnPhotoEditorListener
import ja.burhanrashid52.photoeditor.PhotoEditor
import ja.burhanrashid52.photoeditor.PhotoEditorView
import ja.burhanrashid52.photoeditor.ViewType

interface TextEditorListener {
    fun onDone(inputText: String?, colorCode: Int)
}
class MainActivity : AppCompatActivity() {
    companion object
    {


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mPhotoEditorView = findViewById<PhotoEditorView>(R.id.photoEditorView)
        var undo1 = findViewById<Button>(R.id.undo1)
        var redo1 = findViewById<Button>(R.id.redo1)
        var addtext1 = findViewById<Button>(R.id.addtext1)
        var mTypeface: Typeface




        mPhotoEditorView.source.setImageResource(R.color.teal_700)
        val mTextRobotoTf = ResourcesCompat.getFont(this, R.font.algerian)

//loading font from asset

//loading font from asset
        var hj = PhotoEditor.Builder(this, mPhotoEditorView)
            .setPinchTextScalable(true)
            .setClipSourceImage(true)
            .setDefaultTextTypeface(mTextRobotoTf)
            .setPinchTextScalable(true)
            .build()

        undo1.setOnClickListener {

            hj.undo()

        }
        redo1.setOnClickListener {
            hj.redo()
        }
        addtext1.setOnClickListener {
            val fragment=TextEditorDialogFragment()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.details_fragment, fragment, "fragment_name")
                .commit()

//            hj.addText("hello",R.color.purple_500)
        }

//        hj.addText("inputText", R.color.purple_200);
            hj.addText("hello", R.color.purple_500)
            hj.addEmoji("2764")


            hj.setOnPhotoEditorListener(object : OnPhotoEditorListener {
                override fun onAddViewListener(viewType: ViewType?, numberOfAddedViews: Int) {

                }

                override fun onEditTextChangeListener(
                    rootView: View?,
                    text: String?,
                    colorCode: Int
                ) {


                }

                override fun onRemoveViewListener(viewType: ViewType?, numberOfAddedViews: Int) {

                }

                override fun onStartViewChangeListener(viewType: ViewType?) {

                }

                override fun onStopViewChangeListener(viewType: ViewType?) {

                }

                override fun onTouchSourceImage(event: MotionEvent?) {


                }
            })
        }

    }
