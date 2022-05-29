package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.view.recycler.note.callback

class NoteCallbackInfo(
    val position: Int,
    val action: NoteCallbackAction,
    var noteId: Int? = null,
    var noteTile: String? = null,
    var noteContent: String? = null,
    var noteArchive: Boolean? = null,
)