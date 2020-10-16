package com.samples.bootcamp.FlickrApp

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.samples.bootcamp.udemyflikrapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.browse.*
import kotlinx.android.synthetic.main.content_photo_details.*

class PhotoDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_details)
        //setSupportActionBar(findViewById(R.id.toolbar))
        activateToolbar(true)

        val photo = intent.getParcelableExtra<Photo>(
            PHOTO_TRANSER
        ) as Photo
            //intent.getSerializableExtra(PHOTO_TRANSER) as Photo
//        photo_title.text = "Title:" + photo.title
//        photo_tags.text = "Tags:" + photo.tags
        photo_title.text = resources.getString(R.string.photo_title_text, photo.title)
        photo_tags.text = resources.getString(R.string.photo_tags_text, photo.tags)

        //photo_author.text = photo.author
        photo_author.text = resources.getString(R.string.photo_author_text, photo.author)

        Picasso.with(this).load(photo.link)
            .error(R.drawable.image_holder_48)
            .placeholder(R.drawable.image_holder_48)
            .into(photo_image)


    }
}