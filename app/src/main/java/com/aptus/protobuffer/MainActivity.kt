package com.aptus.protobuffer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addressBook = createAddressBookProtobuf()

        // Serialize AddressBook to ByteArray
        val bytes = addressBook.toByteArray()

        // Deserialize AddressBook ByteArray
        val myAddressBook = AddressBookProtos.AddressBook.parseFrom(bytes)
        println(myAddressBook)

        println("Protobuf byte size: ${bytes.size}")

        println("JSON byte size: ${SAMPLE_JSON.toByteArray().size}")
    }

    private fun createAddressBookProtobuf(): AddressBookProtos.AddressBook {
        // building PhoneNumber objects
        val phoneHome = AddressBookProtos.Person.PhoneNumber.newBuilder()
                .setNumber("+919842012345")
                .setType(AddressBookProtos.Person.PhoneType.HOME)
                .build()

        val phoneMobile = AddressBookProtos.Person.PhoneNumber.newBuilder()
                .setNumber("+919842012346")
                .setType(AddressBookProtos.Person.PhoneType.MOBILE)
                .build()

        // building a Person object using phone data
        val person = AddressBookProtos.Person.newBuilder()
                .setId(1)
                .setName("Shanmugsanthosh")
                .setEmail("info@mailinator")
                .addAllPhones(listOf(phoneHome, phoneMobile))
                .build()

        // building an AddressBook object using person data
        return AddressBookProtos.AddressBook.newBuilder()
                .addAllPeople(listOf(person))
                .build()
    }

    companion object {
        private const val SAMPLE_JSON =
                """{
  "addressbook": [
    {
      "person": {
        "id": 1,
        "name": "Shanmugasanthosh",
        "email": "info@mailinator.com"
      }
    },
    {
      "phones": [
        {
          "phone": {
            "number": "+919842012345",
            "type": "HOME"
          }
        },
        {
          "phone": {
            "number": "+919842012346",
            "type": "MOBILE"
          }
        }
      ]
    }
  ]
}"""
    }
}

