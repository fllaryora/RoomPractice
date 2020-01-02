package com.example.roompractice.data.model

import com.google.gson.annotations.SerializedName

class Post {
    @SerializedName("userId")
    var userId :Int = -1

    @SerializedName("id")
    var  id: Int = -1

    @SerializedName("title")
    var title: String = ""

    @SerializedName("body")
    var  body: String = ""

    constructor()

    constructor(id: Int, userId: Int, title: String, body: String) : this() {
        this.id = id
        this.userId = userId
        this.title = title
        this.body = body
    }
}