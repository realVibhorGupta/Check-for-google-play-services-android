package www.vibhorgupta.in.checkforgoogleplayservices;

import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_GOOGLE_PLAY_SERVICES = 1972;
    GoogleApiAvailability avail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int PLAY_SERVICE_STATUS = avail.isGooglePlayServicesAvailable(this);
        checkPlayService(PLAY_SERVICE_STATUS);
//        if (savedInstanceState == null) {
//            startRegistrationService();
//        }
    }

    private void checkPlayService(int PLAY_SERVICE_STATUS)
    {
        switch (PLAY_SERVICE_STATUS)
        {
            case ConnectionResult.API_UNAVAILABLE:
                //API is not available
                Toast.makeText(this, "API is not available", Toast.LENGTH_SHORT).show();
                break;
            case ConnectionResult.NETWORK_ERROR:
                //Network error while connection
                Toast.makeText(this, "Network error while connection", Toast.LENGTH_SHORT).show();
                break;
            case ConnectionResult.RESTRICTED_PROFILE:
                //Profile is restricted by google so can not be used for play services
                Toast.makeText(this, "Profile is restricted by google so can not be used for play services", Toast.LENGTH_SHORT).show();
                break;
            case ConnectionResult.SERVICE_MISSING:
                //service is missing
                Toast.makeText(this, "service is missing", Toast.LENGTH_SHORT).show();
                break;
            case ConnectionResult.SIGN_IN_REQUIRED:
                //service available but user not signed in
                Toast.makeText(this, "service available but user not signed in", Toast.LENGTH_SHORT).show();
                break;
            case ConnectionResult.SUCCESS:
                Toast.makeText(this, "Connection SuccesFull", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void startRegistrationService() {
        GoogleApiAvailability api = GoogleApiAvailability.getInstance();
        int code = api.isGooglePlayServicesAvailable(this);
        if (code == ConnectionResult.SUCCESS) {
            onActivityResult(REQUEST_GOOGLE_PLAY_SERVICES, Activity.RESULT_OK, null);
        } else if (api.isUserResolvableError(code) &&
                api.showErrorDialogFragment(this, code, REQUEST_GOOGLE_PLAY_SERVICES)) {
            // wait for onActivityResult call (see below)
        } else {
            Toast.makeText(this, api.getErrorString(code), Toast.LENGTH_LONG).show();
        }
    }

 //   @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        switch(requestCode) {
//            case REQUEST_GOOGLE_PLAY_SERVICES:
//                if (resultCode == Activity.RESULT_OK) {
//                    Intent i = new Intent(this, RegistrationService.class);
//                    startService(i); // OK, init GCM
//                }
//                break;
//
//            default:
//                super.onActivityResult(requestCode, resultCode, data);
//        }
}
