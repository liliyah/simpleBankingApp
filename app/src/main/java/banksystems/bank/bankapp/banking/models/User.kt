package banksystems.bank.bankapp.banking.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
 @PrimaryKey(autoGenerate = true)
 var user_id: Int,
 val user_name: String,
 val account_number: Int,
 val user_email: String,
 val user_phonenumber: Int,
 val current_balance: Int,
) :Parcelable{
 constructor(parcel: Parcel) : this(
  parcel.readInt(),
  parcel.readString().toString(),
  parcel.readInt(),
  parcel.readString().toString(),
  parcel.readInt(),
  parcel.readInt()) {
 }

 override fun writeToParcel(parcel: Parcel, flags: Int) {
  parcel.writeInt(user_id)
  parcel.writeString(user_name)
  parcel.writeInt(account_number)
  parcel.writeString(user_email)
  parcel.writeInt(user_phonenumber)
  parcel.writeInt(current_balance)
 }

 override fun describeContents(): Int {
  return 0
 }

 companion object CREATOR : Parcelable.Creator<User> {
  override fun createFromParcel(parcel: Parcel): User {
   return User(parcel)
  }

  override fun newArray(size: Int): Array<User?> {
   return arrayOfNulls(size)
  }
 }
}