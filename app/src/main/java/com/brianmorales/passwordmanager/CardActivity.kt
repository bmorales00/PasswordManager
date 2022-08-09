package com.brianmorales.passwordmanager


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.brianmorales.passwordmanager.adapter.MenuAdapter
import com.brianmorales.passwordmanager.database.PassCardDatabase
import com.brianmorales.passwordmanager.model.Passcard
import com.brianmorales.passwordmanager.utility.ThemeManager

class CardActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etUrl: EditText
    private lateinit var etDescription: EditText

    private lateinit var toolbar:Toolbar

    private var passCardList = PassCardDatabase.getCardList()


    override fun onCreate(savedInstanceState: Bundle?) {
        //This method is used to empty the toolbar to be able to insert our custom toolbar for this activity
        ThemeManager.setTheme(this@CardActivity)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        //This is used to set up out custom toolbar
        toolbar = findViewById(R.id.custom_toolbar)
        setSupportActionBar(toolbar)


        etUsername = findViewById(R.id.etUserName)
        etPassword = findViewById(R.id.etPassWord)
        etUrl = findViewById(R.id.etUrl)
        etDescription = findViewById(R.id.etDesc)

        onClicked(toolbar)
    }

    private fun onClicked(toolbar: Toolbar) {

        toolbar.setNavigationOnClickListener{
            this.finish()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.card_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.itemEdit -> {
                etUsername.isEnabled = true
                etPassword.isEnabled = true
                etUrl.isEnabled = true
                etDescription.isEnabled = true
            }
            R.id.itemSave ->{
                if(etUsername.text.isBlank() || etPassword.text.isBlank() || etUrl.text.isBlank()){
                    Toast.makeText(this,
                        "missing fields need to be filled out in order to save", Toast.LENGTH_LONG).show()
                }
                else{
                    saveItem()
                    this.finish()
                    MenuActivity.rvSaveCard.adapter?.notifyDataSetChanged()
                }

            }
        }
        return true
    }


    private fun saveItem() {

        passCardList.add(
            Passcard(
                userName = etUsername.text.toString(),
                password = etPassword.text.toString(),
                link = etUrl.text.toString(),
                desc = etDescription.text.toString()
            ))

    }

//    private fun updateMenuAdapter() {
//        adapter = MenuAdapter(this, passCardList)
//        adapter.notifyDataSetChanged()
//
//        rvSaveCard.setHasFixedSize(true)
//        rvSaveCard.adapter = adapter
//        rvSaveCard.layoutManager = LinearLayoutManager(this)
//    }
}

