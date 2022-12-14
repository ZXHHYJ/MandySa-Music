package studio.mandysa.music.logic.repository

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.map
import com.drake.serialize.serialize.serial
import com.drake.serialize.serialize.serialLiveData

object UserRepository {

    private var mUserId by serial<String?>(name = "userid", default = null)

    private val mCookieLiveData by serialLiveData<String?>(name = "cookie", default = null)

    /**
     * 通过有无cookie判断是否登录
     */
    private val isLoginLiveData = mCookieLiveData.map { it != null }

    val isLoginState @Composable @Stable get() = isLoginLiveData.observeAsState().value

    fun login(cookie: String, userId: String) {
        mUserId = userId
        mCookieLiveData.postValue(cookie)
    }

    fun cookie(): String {
        return mCookieLiveData.value!!
    }

    fun userId(): String {
        return mUserId!!
    }

    fun signOut() {
        mUserId = null
        mCookieLiveData.value = null
    }

}