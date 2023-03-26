package com.example.eldarwallet.infrastructure.repository

import android.app.Application
import com.example.eldarwallet.core.repository.InJsonCreditUserDataRepository
import com.example.eldarwallet.infrastructure.representation.UserVerificationData
import com.google.gson.Gson
import java.io.InputStream

class InJsonCreditUserDataRepositoryImp(private val application: Application) : InJsonCreditUserDataRepository {

    override fun find(): UserVerificationData {
        return Gson().fromJson(getJson(), UserVerificationData::class.java)
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