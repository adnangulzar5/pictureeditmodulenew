package com.example.pictureeditmodule
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.example.LibraryBR.EmojiBSFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ja.burhanrashid52.photoeditor.*


class MainActivity : AppCompatActivity(), EmojiBSFragment.EmojiListener {
    companion object
    {
        var hj: PhotoEditor? = null
        private var mEmojiBSFragment: EmojiBSFragment? = null

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mPhotoEditorView = findViewById<PhotoEditorView>(R.id.photoEditorView)
        var undo1 = findViewById<Button>(R.id.undo1)
        var redo1 = findViewById<Button>(R.id.redo1)
        var addstk=findViewById<Button>(R.id.addstk)
        var addtext1 = findViewById<Button>(R.id.addtext1)
        var mTxtCurrentTool: TextView? = null
        mEmojiBSFragment?.setEmojiListener(this@MainActivity)

        mPhotoEditorView.source.setImageResource(R.color.teal_700)
        val mTextRobotoTf = ResourcesCompat.getFont(this, R.font.algerian)

        hj = PhotoEditor.Builder(this, mPhotoEditorView)
            .setPinchTextScalable(true)
            .setClipSourceImage(true)
            .setDefaultTextTypeface(mTextRobotoTf)
            .setPinchTextScalable(true)
            .build()

        addstk.setOnClickListener {
            showBottomSheetDialogFragment(mEmojiBSFragment)

        }

        undo1.setOnClickListener {

            hj!!.undo()

        }
        redo1.setOnClickListener {
            hj!!.redo()
        }
        addtext1.setOnClickListener {

            val textEditorDialogFragment = TextEditorDialogFragment.show(this)
            textEditorDialogFragment.setOnTextEditorListener(object : TextEditorDialogFragment.TextEditorListener {
                override fun onDone(inputText: String?, colorCode: Int) {
                    val styleBuilder = TextStyleBuilder()
                    styleBuilder.withTextColor(colorCode)
                    hj?.addText(inputText, styleBuilder)
                    mTxtCurrentTool?.setText(R.string.label_text)
                }
            })

        }

            hj!!.addText("hello", R.color.green_color_picker)

        hj!!.setOnPhotoEditorListener(object : OnPhotoEditorListener {
            override fun onAddViewListener(viewType: ViewType?, numberOfAddedViews: Int) {

            }

            override fun onEditTextChangeListener(rootView: View?, text: String?, colorCode: Int) {
                val textEditorDialogFragment = TextEditorDialogFragment.show(this@MainActivity, text.toString(), colorCode)
                textEditorDialogFragment.setOnTextEditorListener (object : TextEditorDialogFragment.TextEditorListener {
                    override fun onDone(inputText: String?, colorCode: Int) {
                        val styleBuilder = TextStyleBuilder()
                        styleBuilder.withTextColor(colorCode)
                        if (rootView != null) {
                            hj?.editText(rootView, inputText, styleBuilder)
                        }
                        mTxtCurrentTool?.setText(R.string.label_text)
                    }
                })
            }

//            override fun onEditTextChangeListener(rootView: View?, text: String?, colorCode: Int) {
//
//                Toast.makeText(applicationContext,"edit ma aya ha ",Toast.LENGTH_SHORT).show()
//
//                    hj!!.editText(rootView!!, text, colorCode)
//
//
//            }



            override fun onRemoveViewListener(viewType: ViewType?, numberOfAddedViews: Int) {

            }

            override fun onStartViewChangeListener(viewType: ViewType?) {

            }

            override fun onStopViewChangeListener(viewType: ViewType?) {

            }

            override fun onTouchSourceImage(event: MotionEvent?) {

            }
        })

//            hj!!.setOnPhotoEditorListener(object : OnPhotoEditorListener {
//                override fun onAddViewListener(viewType: ViewType?, numberOfAddedViews: Int) {
//
//                }
//                override fun onEditTextChangeListener(rootView: View?, text: String?, colorCode: Int) {
////                    val textEditorDialogFragment = TextEditorDialogFragment.show(this@MainActivity, text.toString(), colorCode)
////                    textEditorDialogFragment.setOnTextEditorListener (object : TextEditorDialogFragment.TextEditorListener {
////                        override fun onDone(inputText: String?, colorCode: Int) {
////                            val styleBuilder = TextStyleBuilder()
////                            styleBuilder.withTextColor(colorCode)
////                            if (rootView != null) {
////                                hj?.editText(rootView, inputText, styleBuilder)
////                            }
////                            mTxtCurrentTool?.setText(R.string.label_text)
////                        }
////                    })
//                }
//
//
//                override fun onRemoveViewListener(viewType: ViewType?, numberOfAddedViews: Int) {
//
//                }
//
//                override fun onStartViewChangeListener(viewType: ViewType?) {
//
//                }
//
//                override fun onStopViewChangeListener(viewType: ViewType?) {
//
//                }
//
//                override fun onTouchSourceImage(event: MotionEvent?) {
//
//
//                }
//            })
        }

    private fun showBottomSheetDialogFragment(fragment: BottomSheetDialogFragment?) {
        if (fragment == null || fragment.isAdded) {
            return
        }
        fragment.show(supportFragmentManager, fragment.tag)
    }

    override fun onEmojiClick(emojiUnicode: String?) {
        hj?.addEmoji(emojiUnicode)
    }


}
