package com.brianmorales.passwordmanager

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brianmorales.passwordmanager.adapter.MenuAdapter
import com.brianmorales.passwordmanager.database.PassCardDatabase


class MenuActivity : AppCompatActivity(){

    companion object{
        lateinit var rvSaveCard: RecyclerView
    }
    private lateinit var adapter: MenuAdapter

    private var passCardList = PassCardDatabase.getCardList()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        rvSaveCard = findViewById(R.id.rvSaveCard)

        startMenuAdapter()
    }



    @SuppressLint("NotifyDataSetChanged")
    private fun startMenuAdapter() {

        adapter = MenuAdapter(this, passCardList)
        adapter.setOnCardClickListener(object : MenuAdapter.OnCardClickListener{
            override fun onCardClicked(position: Int) {
                val intent = Intent(this@MenuActivity, EditCardActivity::class.java)
                val passCard = passCardList[position]
                val bundle = Bundle()
                bundle.putString("UserName", passCard.userName.toString())
                bundle.putString("PassWord", passCard.password.toString())
                bundle.putString("Link", passCard.link.toString())
                bundle.putString("Description", passCard.desc.toString())
                bundle.putInt("Position", position)

                intent.putExtras(bundle)
                startActivity(intent)
            }

        })
        adapter.notifyDataSetChanged()

        rvSaveCard.setHasFixedSize(true)
        rvSaveCard.layoutManager = LinearLayoutManager(this)
        rvSaveCard.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.add_passcard -> startActivity(Intent(this, CardActivity::class.java))
        }
        return true
    }



}
