package com.example.artspace

import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nextButton = findViewById<Button>(R.id.nextButton)
        val prevButton = findViewById<Button>(R.id.prevButton)
        val imageView = findViewById<ImageView>(R.id.artGalleryImage)
        val caption = findViewById<TextView>(R.id.picCaption)

        val imageResources = arrayOf(
            R.drawable.art1,
            R.drawable.art2,
            R.drawable.art3,
            R.drawable.art4,
            R.drawable.art5
        )

        // set initial image index
        var imageIndex = 0

        // change caption based on image index
        data class ImageWithCaption (val image: Int, val caption: String)

        // image captions
        val images = listOf(
            ImageWithCaption(R.drawable.art1, "Joan Miro, Peinture (Etoile Bleue), 1927"),
            ImageWithCaption(R.drawable.art2, "Gerhard Richter, Abstract Painting 599, 1986"),
            ImageWithCaption(R.drawable.art3, "Christine Ay Tjoe, Small Flies and Other Wings, 2013"),
            ImageWithCaption(R.drawable.art4, "Tomoo Gokita, Scorn, 2011"),
            ImageWithCaption(R.drawable.art5, "Piet Mondrian, Composition II in Red, Blue, and Yellow, 1930")
        )

        // track the index of the image to display
        imageView.setImageResource(imageResources[imageIndex])
        // set initial caption
        caption.text = images[0].caption

        // button for next image
        nextButton.setOnClickListener {
            imageIndex++

            if (imageIndex >= images.size) {
                imageIndex = 0
            }
            imageView.setImageResource(imageResources[imageIndex])
            caption.text = images[imageIndex].caption // update caption
        }

        // button for previous image
        prevButton.setOnClickListener {
            imageIndex--

            if (imageIndex < 0) {
                imageIndex = images.size - 1
            }
            imageView.setImageResource(imageResources[imageIndex])
            caption.text = images[imageIndex].caption // update caption
        }

    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Load landscape layout
            setContentView(R.layout.activity_main)
        } else {
            // Load portrait layout
            setContentView(R.layout.activity_main)
        }
    }
}