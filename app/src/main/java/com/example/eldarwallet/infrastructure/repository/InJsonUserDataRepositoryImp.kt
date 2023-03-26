package com.example.eldarwallet.infrastructure.repository

import android.app.Application
import com.example.eldarwallet.core.repository.UserDataRepository
import com.example.eldarwallet.infrastructure.representation.UserVerificationData
import com.google.gson.Gson
import java.io.InputStream

class InJsonUserDataRepositoryImp(private val application: Application) : UserDataRepository {

    override fun find(): UserVerificationData {
        val json = Gson().fromJson(getJson(), UserVerificationData::class.java)
        return json
    }

    private fun getJson(): String? {
        var inputStream : InputStream? = null
        val jsonString: String
        try {
            inputStream = application.applicationContext.assets.open("userdata.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            jsonString = String(buffer)
            return jsonString
        } catch (ex: Exception) {
            ex.printStackTrace()
        } finally {
            inputStream?.close()
        }
        return null
    }
}