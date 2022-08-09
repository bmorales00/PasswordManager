package com.brianmorales.passwordmanager.model

import java.io.Serializable

class Passcard (

    //Note username/email with Password
    var userName :String? = null,
    var password :String? = null,
    //Name of Website with provided link

    var link :String? = "",
    //Note Description
    var desc :String? = ""

        ): Serializable {


}