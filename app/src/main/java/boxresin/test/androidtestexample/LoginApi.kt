package boxresin.test.androidtestexample

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/** 로그인 API */
object LoginApi {
    /** 로그인한다. */
    suspend fun login(
        id: String,
        password: String
    ) = withContext(Dispatchers.IO) {
        println("로그인 중...")
        delay(3000)
        println("로그인 완료")
    }
}
