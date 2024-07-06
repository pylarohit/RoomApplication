package com.rohit.roomapplication

import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.rohit.roomapplication.databinding.ActivityMainBinding


class RecyclerActivity : AppCompatActivity(), RecyclerInterferce {
    var binding: ActivityMainBinding? = null
    var listView = arrayListOf<Title>()
    var recycleradapter = RecyclerAdapter(this,listView,this)
    lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding?.fab?.setOnClickListener {
            Dialog(this).apply {
                setContentView(R.layout.dialog_add)
                window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                var title1 = findViewById<TextView>(R.id.title_add)
                var description1 = findViewById<TextView>(R.id.description_add)
                var add = findViewById<Button>(R.id.add_button)

                add?.setOnClickListener {
                    if (title1.text.trim().isNullOrEmpty()) {
                        title1.error = "enter title"
                    } else if (description1.text.trim().isNullOrEmpty()) {
                        description1.error = "enter description"
                    } else {
                        listView.add(Title(title1.text.toString(), description1.text.toString()))
                        recycleradapter.notifyDataSetChanged()
                        dismiss()
                    }
                }
            }.show()
        }
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding?.RecyclerView?.layoutManager = linearLayoutManager
        binding?.RecyclerView?.adapter = recycleradapter

    }
    override fun delete(position: Int) {
        Dialog(this).apply {
            setContentView(R.layout.dialog_delete)
            window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)

            var yes = findViewById<Button>(R.id.yes_btn)
            var no = findViewById<Button>(R.id.no_btn)
            yes?.setOnClickListener {
                    listView.removeAt(position)
                    recycleradapter.notifyDataSetChanged()
                    dismiss()
                }
            no?.setOnClickListener {
                recycleradapter.notifyDataSetChanged()
                dismiss()
            }
            }.show()
        }
    override fun update(position: Int) {
        Dialog(this).apply {
            setContentView(R.layout.dialog_update)
            window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            var title_1 = findViewById<EditText>(R.id.title_edit)
            var description_2 = findViewById<EditText>(R.id.description_edit)
            var update = findViewById<Button>(R.id.update_button)

            title_1.setText(listView[position].title)
            description_2.setText(listView[position].description)

            update?.setOnClickListener {
                if(title_1.text.trim().isNullOrEmpty()){
                    title_1.error = "enter title"
                }else if (description_2.text.trim().isNullOrEmpty()){
                    description_2.error = "enter description"
                }else
                {
                    listView.set(position,
                        Title(title_1.text.toString(),description_2.text.toString())
                    )
                    recycleradapter.notifyDataSetChanged()
                    dismiss()
                }
            }
        }.show()
    }

}