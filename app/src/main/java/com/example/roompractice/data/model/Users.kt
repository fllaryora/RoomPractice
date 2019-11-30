package com.example.roompractice.data.model

import com.google.gson.annotations.SerializedName

class Users {
    @SerializedName("id")
    var id: Int? = null
    @SerializedName("username")
    var userName: String = ""
    @SerializedName("email")
    var emailAddress: String = ""
    @SerializedName("website")
    var website: String = ""

    constructor() {}

    constructor(id: Int, userName: String, emailAddress: String, website: String) : this() {
        this.id = id
        this.userName = userName
        this.emailAddress = emailAddress
        this.website = website
    }

}