package com.brianmorales.passwordmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.brianmorales.passwordmanager.database.PassCardDatabase
import com.brianmorales.passwordmanager.model.Passcard
import com.brianmorales.passwordmanager.utility.ThemeManager

class EditCardActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var etUrl: EditText
    private lateinit var etDescription: EditText

    private lateinit var bundle: Bundle
    private var passCardList = PassCardDatabase.getCardList()

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeManager.setTheme(this@EditCardActivity)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        toolbar = findViewById(R.id.custom_toolbar)
        setSupportActionBar(toolbar)
        etUsername = findViewById(R.id.etUserName)
        etPassword = findViewById(R.id.etPassWord)
        etUrl = findViewById(R.id.etUrl)
        etDescription = findViewById(R.id.etDesc)
        bundle = intent.extras!!

        onClicked(toolbar)
        editedCard()
    }

    private fun editedCard() {
        etUsername.setText(bundle.getString("UserName"))
        etPassword.setText(bundle.getString("PassWord"))
        etUrl.setText(bundle.getString("Link"))
        etDescription.setText(bundle.getString("Description"))
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
                    overWriteItem()
                    MenuActivity.rvSaveCard.adapter?.notifyDataSetChanged()
                    this.finish()
                }

            }
        }
        return true
    }

    private fun overWriteItem() {
        val passCard = passCardList[bundle.getInt("Position")]
        passCard.userName = etUsername.toString()
        passCard.password = etPassword.toString()
        passCard.link = etUrl.toString()
        passCard.desc = etDescription.toString()
    }

}