package com.example.apbd

import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_detail.*

class ActivityDetail : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor>
{
    var contactId = ContactsContract.RawContacts.CONTACT_ID
    var DisplayName = ContactsContract.Contacts.DISPLAY_NAME
    var Number = ContactsContract.CommonDataKinds.Phone.NUMBER

    var myListContact : MutableList<myContact> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        LoaderManager.getInstance(this).initLoader(1,null,this)
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        val myContactUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI

        var myProjection = arrayOf(contactId,DisplayName,Number)

        return CursorLoader(this,myContactUri,myProjection,null,null,"$DisplayName ASC")
    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        myListContact.clear()
        if(data != null) {
            data.moveToFirst()
            while (!data.isAfterLast){
                myListContact.add(
                    myContact(
                        data.getString(data.getColumnIndex(DisplayName)),
                        getUserContactEmail(data.getString(data.getColumnIndex(contactId)))!!,
                        data.getString(data.getColumnIndex(Number))
                    )
                )
                data.moveToNext()
            }
            var contactAdapter = myAdaptRecyView(myListContact)
            myRecycleView.apply {
                layoutManager = LinearLayoutManager(this@ActivityDetail)
                adapter = contactAdapter
            }
        }
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {
        myRecycleView.adapter?.notifyDataSetChanged()
    }

    private fun getUserContactEmail(contactId: String) : String? {
        val projection = arrayOf(ContactsContract.CommonDataKinds.Email.DATA, ContactsContract.CommonDataKinds.Email.TYPE)

        var email = ""
        val data = this.contentResolver.query(
            ContactsContract.CommonDataKinds.Email.CONTENT_URI,
            projection,
            ContactsContract.Data.CONTACT_ID + "=?",
            arrayOf(contactId),
            null
        )

        if (data != null) {
            data.moveToFirst()
            val EmailColumnIndex = data.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA)
            while (!data.isAfterLast) {
                email =  data.getString(EmailColumnIndex)
                data.moveToNext()
            }
        }
        data?.close()

        return email
    }
}