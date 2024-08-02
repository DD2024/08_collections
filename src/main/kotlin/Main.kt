package org.example

fun main() {

    val noteService = NoteService
    noteService.add(1, "note1", "text of the note 1")
    noteService.add(2, "note2", "text of the note 2")

    noteService.createComment(1, 1, "message of the comment 1")
    noteService.createComment(2, 2, "message of the comment 2")

    noteService.edit(2, "not_2", "text of the note_2")
    noteService.delete(2)

    noteService.deleteComment(2)
    noteService.restoreComment(2, )

    noteService.editComment(1, "new message of the comment 1")
}

object NoteService{

    private var notes = mutableListOf<Note>()
    private var comments = mutableListOf<Comment>()

    fun clear() {
        notes = mutableListOf()
        comments = mutableListOf()
    }

    fun add(noteId: Int, title: String, text: String): Int{

        val note = Note(noteId, title, text)

        notes += note

        return notes.last().noteId
    }

    fun createComment(commentId: Int, noteId: Int, message: String): Int{

        getById(noteId)?: throw Exception("no note with noteId $noteId")
        comments = getComments()
        for(item in comments){
            if(item.commentId == commentId){
                throw Exception("comment with commentId $commentId already exists")
            }
        }

        val comment = Comment(commentId, noteId, message)

        comments += comment

        return comments.last().commentId
    }

    fun delete(noteId: Int): Int{

        val note = getById(noteId)?: throw Exception("no note with noteId $noteId")
        val notes = get()
        notes.remove(note)

        val comments = getComments()
        for(item in comments){
           if(item.noteId == noteId){
               comments.remove(item)
           }
        }
        return 1
    }

    fun deleteComment(commentId: Int): Int{

        val comments = getComments()
        for(item in comments){
            if(item.commentId == commentId){
                if(item.deleted){
                    throw Exception("comment with commentId $commentId already deleted")
                } else {
                item.deleted = true
                return 1
                }
            }
        }

        throw Exception("no comment with commentId $commentId")
    }

    fun edit(noteId: Int, title: String, text: String): Int{

        val note = getById(noteId)?: throw Exception("no note with noteId $noteId")
        note.text = text
        note.title = title

        return 1

    }

    fun editComment(commentId: Int, message: String): Int{

        val comments = getComments()
        for(item in comments){
            if(item.commentId == commentId){
                if(item.deleted){
                    throw Exception("comment with commentId $commentId already deleted")
                } else {
                    item.message = message
                    return 1
                }
            }
        }
        throw Exception("no comment with commentId $commentId")
    }


    fun get(): MutableList<Note>{
        return notes
    }

    fun getById(noteId: Int): Note?{

        for(item in notes){
            if(item.noteId == noteId){
                return item
            }
        }
       return null
    }

    fun getComments(): MutableList<Comment>{

        return comments
    }

    fun restoreComment(commentId: Int): Int{

        val comments = getComments()
        for(item in comments){
            if(item.commentId == commentId){
                if(!item.deleted){
                    throw Exception("comment with commentId $commentId deleted")
                } else {
                    item.deleted = false
                    return 1
                }
            }
        }
        throw Exception("no comment with commentId $commentId")

    }
}