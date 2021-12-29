package com.example.android.evernote

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*

class NotesRVAdapter(private val context: Context, private val listener: MainActivity): RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {

    private val allNotes = ArrayList<Note>()

    inner class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textView = itemView.text
        val deleteButton = itemView.deleteButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val viewHolder = NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note, parent))
        viewHolder.deleteButton.setOnClickListener{
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.textView.text = currentNote.text
    }

    override fun getItemCount() = allNotes.size

    fun updateList(newList:List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }
}

interface INotesRVAdapter{
    fun onItemClicked(note:Note)
}