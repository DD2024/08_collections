package org.example

data class Comment(val commentId: Int, val noteId: Int, var message: String, var deleted : Boolean = false) {
}