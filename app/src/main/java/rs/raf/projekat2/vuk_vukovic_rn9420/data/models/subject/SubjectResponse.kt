package rs.raf.projekat2.vuk_vukovic_rn9420.data.models.subject

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubjectResponse(
    @Json(name = "predmet") val name: String,
    @Json(name = "tip") val type:String,
    @Json(name = "nastavnik") val prof: String,
    @Json(name = "grupe") val groups: String,
    @Json(name = "dan") val day: String,
    @Json(name = "termin") val time: String,
    @Json(name = "ucionica") val classroom: String
)