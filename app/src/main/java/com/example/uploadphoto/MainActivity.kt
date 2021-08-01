package com.example.uploadphoto

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    var mStorage :StorageReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mStorage = FirebaseStorage.getInstance().reference
        btnSelect.setOnClickListener {
            val intentImage  = Intent(Intent.ACTION_PICK)
            intentImage.type = ("image/*")
            startActivityForResult(intentImage,1)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK){
         var uri=  data!!.data

            var filepath =  mStorage!!.child(Calendar.getInstance().time.toString())
            if (uri != null) {
                filepath.putFile(uri).addOnSuccessListener {
                    Toast.makeText(applicationContext,"Upload Image",Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}