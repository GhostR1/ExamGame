package nataliia.semenova.examgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import nataliia.semenova.examgame.webview.WebViewActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_start);

        boolean isUser = checkTraffic();
        moveToForward(isUser);
    }

    private boolean checkTraffic() { return true; }

    private void moveToForward(boolean isUser) {
        if(isUser) {
            Intent intent = new Intent(this, WebViewActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}