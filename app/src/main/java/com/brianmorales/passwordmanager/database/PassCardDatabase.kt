package com.brianmorales.passwordmanager.database

import com.brianmorales.passwordmanager.model.Passcard

class PassCardDatabase{
    companion object{
        private var passCardList: ArrayList<Passcard> = arrayListOf<Passcard>()

        fun getCardList(): ArrayList<Passcard> {
            return passCardList
        }
    }

    fun addPassCard(passCard: Passcard){
        passCardList.add(passCard)
    }
    fun getPassCardSize(): Int {
        return passCardList.size
    }
}