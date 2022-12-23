package nataliia.semenova.examgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;

import nataliia.semenova.examgame.game.PlayActivity;
import nataliia.semenova.examgame.webview.WebViewActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_start);

        boolean isUser = checkTraffic();
        moveNextPage(isUser);
    }

    private void moveNextPage(boolean isUser) {
        Intent intent;
        if(isUser) {
            intent = new Intent(this, WebViewActivity.class);
        } else {
            intent = new Intent(this, PlayActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private boolean checkTraffic() {
        return checkInternet() && !isVPN();
    }

    private boolean checkInternet() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private boolean isVPN() {
        String iFace = "";
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (networkInterface.isUp())
                    iFace = networkInterface.getName();
                if ( iFace.contains("tun") || iFace.contains("ppp") || iFace.contains("pptp")) {
                    return true;
                }
            }
        } catch (SocketException e1) {
            e1.printStackTrace();
        }
        return false;
    }
}