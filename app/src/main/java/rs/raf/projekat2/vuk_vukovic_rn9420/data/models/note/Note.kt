package rs.raf.projekat2.vuk_vukovic_rn9420.data.models.note

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val archived: Boolean
)