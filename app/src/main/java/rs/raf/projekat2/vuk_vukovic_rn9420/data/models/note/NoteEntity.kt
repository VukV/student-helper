package rs.raf.projekat2.vuk_vukovic_rn9420.data.models.note

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    var title: String,
    var content: String,
    var archived: Boolean
)