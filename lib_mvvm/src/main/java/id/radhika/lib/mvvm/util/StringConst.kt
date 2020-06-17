package id.radhika.lib.mvvm.util

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 08/Jun/2020
 **/

object StringConst {
    const val VALID_EMAIL_ADDRESS_REGEX = "[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"
    const val VALID_USERNAME_REGEX = "^(?!.*\\.\\.)(?!.*\\.\$)[^\\W][\\w.]{0,29}\$"
    const val VALID_PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[a-zA-Z]).{8,}\$"
}