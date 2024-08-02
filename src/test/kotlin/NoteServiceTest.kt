import org.example.NoteService
import org.junit.Test

import org.junit.Assert.*

class NoteServiceTest {



    @Test (expected = Exception::class)
    fun createCommentFail1() {

        val service = NoteService
        service.add(1, "note1", "text of the note 1")
        service.createComment(1, 2, "message of the comment 1")

    }

    @Test (expected = Exception::class)
    fun createCommentFail2() {

        val service = NoteService
        service.add(1, "note1", "text of the note 1")
        service.createComment(1, 1, "message of the comment 1")
        service.createComment(1, 1, "message of the comment 1")

    }

    @Test
    fun createCommentSuccess() {

        val service = NoteService
        service.add(1, "note1", "text of the note 1")
        val resultCreateComment = service.createComment(1, 1, "message of the comment 1")

        assertEquals(1, resultCreateComment)

    }

    @Test (expected = Exception::class)
    fun deleteFail() {

        val service = NoteService
        service.add(1, "note1", "text of the note 1")
        service.delete(2)
    }

    @Test
    fun deleteSuccess() {

        val service = NoteService
        service.add(1, "note1", "text of the note 1")
        val resultDelete = service.delete(1)
        assertEquals(1, resultDelete)
    }

    @Test (expected = Exception::class)
    fun deleteCommentFail1() {

        val service = NoteService
        service.add(1, "note1", "text of the note 1")
        service.createComment(1, 1, "message of the comment 1")
        service.deleteComment(1)

    }

    @Test (expected = Exception::class)
    fun deleteCommentFail2() {

        val service = NoteService
        service.add(1, "note1", "text of the note 1")
        service.createComment(1, 1, "message of the comment 1")
        service.deleteComment(2)

    }

    @Test
    fun deleteCommentSuccess() {

        val service = NoteService
        service.add(1, "note1", "text of the note 1")
        service.createComment(1, 1, "message of the comment 1")
        val resultDeleteComment = service.deleteComment(1)

        assertEquals(1, resultDeleteComment)

    }

    @Test (expected = Exception::class)
    fun editFail() {

        val service = NoteService
        service.add(1, "note1", "text of the note 1")
        service.edit(2, "not_2", "text of the note_2")

    }

    @Test
    fun editSuccess() {

        val service = NoteService
        service.add(1, "note1", "text of the note 1")
        val resultEdit = service.edit(1, "not_1", "text of the note_1")

        assertEquals(1, resultEdit)

    }

    @Test (expected = Exception::class)
    fun editCommentFail1() {

        val service = NoteService
        service.add(1, "note1", "text of the note 1")
        service.createComment(1, 1, "message of the comment 1")
        service.deleteComment(1)
        service.editComment(1, "new message of the comment 1")

    }

    @Test (expected = Exception::class)
    fun editCommentFail2() {

        val service = NoteService
        service.add(1, "note1", "text of the note 1")
        service.createComment(1, 1, "message of the comment 1")
        service.editComment(2, "new message of the comment 1")

    }

    @Test
    fun editCommentSuccess() {

        val service = NoteService
        service.add(1, "note1", "text of the note 1")
        service.createComment(1, 1, "message of the comment 1")
        val resultEditComment = service.editComment(1, "new message of the comment 1")

        assertEquals(1, resultEditComment)

    }

    @Test (expected = Exception::class)
    fun restoreCommentFail1() {

        val service = NoteService
        service.add(1, "note1", "text of the note 1")
        service.createComment(1, 1, "message of the comment 1")
        service.restoreComment(1)

    }

    @Test (expected = Exception::class)
    fun restoreCommentFail2() {

        val service = NoteService
        service.add(1, "note1", "text of the note 1")
        service.createComment(1, 1, "message of the comment 1")
        service.deleteComment(1)
        service.restoreComment(2)

    }
    @Test
    fun restoreCommentSuccess() {

        val service = NoteService
        service.add(1, "note1", "text of the note 1")
        service.createComment(1, 1, "message of the comment 1")
        service.deleteComment(1)
        val resultrestoreComment = service.restoreComment(1)
        assertEquals(1, resultrestoreComment)

    }

}