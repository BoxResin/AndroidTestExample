package boxresin.test.androidtestexample

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

/** 메인 액티비티 */
class MainActivity : AppCompatActivity() {
    private val uiScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable) {
                btn_login.isEnabled = edit_id.text.isNotEmpty() && edit_password.text.isNotEmpty()
            }
        }
        this.edit_id.addTextChangedListener(textWatcher)
        this.edit_password.addTextChangedListener(textWatcher)

        this.btn_login.setOnClickListener {
            this.uiScope.launch {
                LoginApi.login("${edit_id.text}", "${edit_password.text}")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        this.uiScope.cancel()
    }
}
