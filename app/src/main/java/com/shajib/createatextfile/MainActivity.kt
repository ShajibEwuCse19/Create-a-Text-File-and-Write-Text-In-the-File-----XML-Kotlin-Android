package com.shajib.createatextfile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.shajib.createatextfile.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            saveFile()
        }
    }

    private fun saveFile() {
        val mainFolder = this.getExternalFilesDir(null) // Get the main folder
        val newFolder = File(mainFolder, "MyFiles") // Create a new folder inside the main folder

        try {
            val isDirMade = if (!newFolder.exists()) {
                newFolder.mkdir() // Create the new folder
            } else {
                true
            }

            if (isDirMade) {
                val fileName = binding.etText.text?.toString() // Get the file name from the EditText
                val file = File(newFolder, "$fileName.txt") // Create a new text file inside the new folder

                val isFileCreated = if (!file.exists()) {
                    file.createNewFile() // Create the text file
                } else {
                    true
                }
                if (isFileCreated) {
                    Snackbar.make(binding.root, "File Created", Snackbar.LENGTH_SHORT).show()

                    // Write the text to the file
                    val fileWriter = file.writer() // Get the file writer
                    fileWriter.write(binding.etText.text.toString()) // Write the text to the file
                    fileWriter.close() // Close the file writer
                } else {
                    Snackbar.make(binding.root, "Couldn't Create File", Snackbar.LENGTH_SHORT).show()
                }
            } else {
                Snackbar.make(binding.root, "Couldn't Create Folder", Snackbar.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

//File location: /storage/emulated/0/Android/data/com.shajib.createatextfile/files/MyFiles/MyFile