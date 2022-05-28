package rs.raf.projekat2.vuk_vukovic_rn9420.data.models.subject

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
data class SubjectEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val type: String,
    val prof: String,
    val groups: String,
    val day: String,
    val time: String,
    val classroom: String
)