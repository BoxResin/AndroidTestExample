package boxresin.test.androidtestexample

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.not
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    /** 초기 상태 테스트 */
    @Test
    fun testInitial() {
        // When: 메인 액티비티 실행될 때
        ActivityScenario.launch(MainActivity::class.java)

        // Then: 아이디, 비밀번호 입력창은 비어 있어야 한다.
        onView(withId(R.id.edit_id)).check(matches(withText("")))
        onView(withId(R.id.edit_password)).check(matches(withText("")))

        // Then: 로그인 버튼은 비활성화 상태여야 한다.
        onView(withId(R.id.btn_login)).check(matches(not(isEnabled())))
    }

    /** 입력 테스트 */
    @Test
    fun testTyping() {
        // Given: 메인 액티비티 실행
        ActivityScenario.launch(MainActivity::class.java)

        // When: 아이디를 입력할 때
        onView(withId(R.id.edit_id)).perform(typeText("hello"))

        // Then: 아직 로그인 버튼은 활성화되면 안 된다.
        onView(withId(R.id.btn_login)).check(matches(not(isEnabled())))


        // When: 비밀번호를 입력할 때
        onView(withId(R.id.edit_password)).perform(typeText("hello"))

        // Then: 로그인 버튼이 활성화되어야 한다.
        onView(withId(R.id.btn_login)).check(matches(isEnabled()))


        // When: 아이디 입력창을 비울 때
        onView(withId(R.id.edit_id)).perform(clearText())

        // Then: 로그인 버튼이 활성화되어야 한다.
        onView(withId(R.id.btn_login)).check(matches(not(isEnabled())))
    }
}
