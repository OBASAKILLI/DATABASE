package com.example.database

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.nio.Buffer

class MainActivity : AppCompatActivity() {


        internal var dbHelper=DatabaseManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

            handleInsert()



        update.setOnClickListener() {
            handleUpdate()
        }

        delete.setOnClickListener() {


            handleDelete()
        }
        view.setOnClickListener() {
            handleView()
        }

    }

    fun handleInsert(){
        insert.setOnClickListener() {
            try {
                dbHelper.insertData(fname.text.toString(), sname.text.toString(), age.text.toString())
                clear()
            } catch (erro: Exception) {

                showDialog("Erro!", erro.message.toString())

            }
        }
    }
    fun handleUpdate(){
            try {
                val isempty =dbHelper.updateData(fname.text.toString(),sname.text.toString(),age.text.toString(),id.text.toString())
                if (isempty==true){
                    showDialog("Sucess!","Database successfully updated")

                }
                else{

                    showDialog("Failure!","Failed to update the database")
                }
            }
            catch (error:Exception){

            }




        }
    fun handleDelete(){
        try {
            dbHelper.deleteData(id.text.toString())
            clear()
        }
        catch (error:Exception){

            showDialog("Error! ",error.message.toString())

        }

    }


    fun handleView(){


        view.setOnClickListener(
        View.OnClickListener {
            val res = dbHelper.allData
            if (res.count == 0){

                showDialog("No data","There was no data found!!!!")
                return@OnClickListener

            }
            else{
                val buffer=StringBuffer()
                while (res.moveToNext()) {
                    buffer.append("YOUR ID IS:" + res.getString(4) + "\n")
                    buffer.append("YOUR FNAME IS:" + res.getString(1) + "\n")
                    buffer.append("YOUR SNAME IS:" + res.getString(2) + "\n")
                    buffer.append("YOUR AGE IS:" + res.getString(3) + "\n")

                }

                showDialog("The list of records found",buffer.toString())

            }


        }
        )
        try {



        }
        catch (error:Exception){
           showDialog("Error!",error.message.toString())




        }


    }



    //Function toshowDialog
    fun showDialog(title:String,message:String){
        val kili =AlertDialog.Builder(this)
        kili.setTitle(title)
        kili.setMessage(message)
        kili.setCancelable(false)
        kili.setPositiveButton("OK"){dialog, which ->



          //Function toshow Toast
        }
        kili.setNegativeButton("Cancel"){dialog, which ->





        }
        kili.show()

        fun Toast(message: String){

            android.widget.Toast.makeText(this,message, android.widget.Toast.LENGTH_SHORT).show()

        }


    }

    //showDialog("Error","No data")
    fun clear(){
        id.setText("")
        fname.setText("")
        sname.setText("")
        age.setText("")
    }

}
