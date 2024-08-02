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

    fun add(note_id: Int, title: String, text: String): Int{

        val note = Note(note_id, title, text)

        notes += note

        return notes.last().note_id
    }

    fun createComment(comment_id: Int, note_id: Int, message: String): Int{

        getById(note_id)?: throw Exception("no note with note_id $note_id")
        comments = getComments()
        for(item in comments){
            if(item.comment_id == comment_id){
                throw Exception("comment with comment_id $comment_id already exists")
            }
        }

        val comment = Comment(comment_id, note_id, message)

        comments += comment

        return comments.last().comment_id
    }

    fun delete(note_id: Int): Int{

        val note = getById(note_id)?: throw Exception("no note with note_id $note_id")
        val notes = get()
        notes.remove(note)

        val comments = getComments()
        for(item in comments){
           if(item.note_id == note_id){
               comments.remove(item)
           }
        }
        return 1
    }

    fun deleteComment(comment_id: Int): Int{

        val comments = getComments()
        for(item in comments){
            if(item.comment_id == comment_id){
                if(item.deleted){
                    throw Exception("comment with comment_id $comment_id already deleted")
                } else {
                item.deleted = true
                return 1
                }
            }
        }

        throw Exception("no comment with comment_id $comment_id")
    }

    fun edit(note_id: Int, title: String, text: String): Int{

        val note = getById(note_id)?: throw Exception("no note with note_id $note_id")
        note.text = text
        note.title = title

        return 1

    }

    fun editComment(comment_id: Int, message: String): Int{

        val comments = getComments()
        for(item in comments){
            if(item.comment_id == comment_id){
                if(item.deleted){
                    throw Exception("comment with comment_id $comment_id already deleted")
                } else {
                    item.message = message
                    return 1
                }
            }
        }
        throw Exception("no comment with comment_id $comment_id")
    }


    fun get(): MutableList<Note>{
        return notes
    }

    fun getById(note_id: Int): Note?{

        for(item in notes){
            if(item.note_id == note_id){
                return item
            }
        }
       return null
    }

    fun getComments(): MutableList<Comment>{

        return comments
    }

    fun restoreComment(comment_id: Int): Int{

        val comments = getComments()
        for(item in comments){
            if(item.comment_id == comment_id){
                if(!item.deleted){
                    throw Exception("comment with comment_id $comment_id deleted")
                } else {
                    item.deleted = false
                    return 1
                }
            }
        }
        throw Exception("no comment with comment_id $comment_id")

    }
}