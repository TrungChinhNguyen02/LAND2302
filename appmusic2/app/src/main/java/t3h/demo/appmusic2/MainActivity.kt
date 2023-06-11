package t3h.demo.appmusic2


import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import phucdv.android.musichelper.MediaHelper
import android.Manifest


class MainActivity : AppCompatActivity() {
     private lateinit var mRecyclerView : RecyclerView
     private lateinit var mEmptyView: TextView
     lateinit var msongList: List<Song>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecyclerView = findViewById(R.id.rscview)
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        msongList = getMusicList()

        if (msongList.isEmpty()) {
            mEmptyView.visibility = View.VISIBLE
        } else {
            mEmptyView.visibility = View.GONE
        }
        val adapter = SongListAdapter(msongList)
        mRecyclerView.adapter = adapter
    }

    private fun getMusicList(): List<Song> {
        val songList = mutableListOf<Song>()
        val songResolver = contentResolver
        val songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.Media.TITLE,
            MediaStore.Audio.Media.ARTIST
        )
        val selection = null
        val selectionArgs = null
        val sortOrder = null
        val songCursor = songResolver.query(songUri, projection, selection, selectionArgs, sortOrder)
        if (songCursor != null && songCursor.moveToFirst()) {
            do {
                val title = songCursor.getString(0)
                val artist = songCursor.getString(1)
                val song = Song(title, artist)
                songList.add(song)
            } while (songCursor.moveToNext())
            songCursor.close()
        }
        return songList
    }
    }