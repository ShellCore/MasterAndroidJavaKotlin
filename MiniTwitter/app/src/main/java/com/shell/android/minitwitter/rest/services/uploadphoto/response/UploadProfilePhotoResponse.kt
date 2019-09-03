package com.shell.android.minitwitter.rest.services.uploadphoto.response

data class UploadProfilePhotoResponse(
    var fieldname : String,
    var originalname : String,
    var encoding : String,
    var mimetyoe : String,
    var destination : String,
    var filename : String,
    var path : String,
    var size : Long
)
