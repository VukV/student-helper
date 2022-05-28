package rs.raf.projekat2.vuk_vukovic_rn9420.data.models.subject

data class Subject(
    val id: Int,
    val name: String,
    val type: String,
    val prof: String,
    val groups: String,
    val day: String,
    val time: String,
    val classroom: String
)