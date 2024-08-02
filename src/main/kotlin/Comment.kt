package org.example

data class Comment(val comment_id: Int, val note_id: Int, var message: String, var deleted : Boolean = false) {
}