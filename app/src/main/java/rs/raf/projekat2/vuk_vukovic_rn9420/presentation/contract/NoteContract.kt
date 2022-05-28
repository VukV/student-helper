package rs.raf.projekat2.vuk_vukovic_rn9420.presentation.contract

interface NoteContract {

    interface ViewModel{
        //todo add states

        fun getAllNotes()
        fun getOnlyUnarchivedNotes()
        fun getByTitleOrContent(searchTag: String)
    }
}