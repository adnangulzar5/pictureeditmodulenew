package com.example.pictureeditmodule

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import ja.burhanrashid52.photoeditor.OnPhotoEditorListener
import ja.burhanrashid52.photoeditor.PhotoEditor
import ja.burhanrashid52.photoeditor.PhotoEditorView
import ja.burhanrashid52.photoeditor.ViewType


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mPhotoEditorView = findViewById<PhotoEditorView>(R.id.photoEditorView)

        mPhotoEditorView.source.setImageResource(R.drawable.ic_launcher_background)
        val mTextRobotoTf = ResourcesCompat.getFont(this, R.font.algerian)

//loading font from asset

//loading font from asset
        var hj = PhotoEditor.Builder(this, mPhotoEditorView)
            .setPinchTextScalable(true)
            .setClipSourceImage(true)
            .setDefaultTextTypeface(mTextRobotoTf)
            .build()



//        hj.addText("inputText", R.color.purple_200);
        hj.addText("hello",R.color.purple_500)
        hj.addEmoji("2764")


        hj.setOnPhotoEditorListener(object : OnPhotoEditorListener {
            override fun onAddViewListener(viewType: ViewType?, numberOfAddedViews: Int) {

            }

            override fun onEditTextChangeListener(rootView: View?, text: String?, colorCode: Int) {


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