package t3h.demo.appmusic2


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class SongListAdapter(var songList: List<Song>) : RecyclerView.Adapter<SongListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rowsong, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val music = songList[position]
        holder.title.text = music.title
        holder.artist.text = music.artist
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var artist: TextView
        init {
            title = itemView.findViewById(R.id.titleText)
            artist = itemView.findViewById(R.id.artisttext)
        }
    }
}