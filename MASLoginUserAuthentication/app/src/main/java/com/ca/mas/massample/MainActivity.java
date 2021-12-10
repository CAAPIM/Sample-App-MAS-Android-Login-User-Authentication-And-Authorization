package com.ca.mas.massample;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.test.espresso.IdlingResource;

import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ca.mas.core.error.TargetApiException;
import com.ca.mas.core.service.MssoIntents;
import com.ca.mas.core.storage.Storage;
import com.ca.mas.core.storage.StorageException;
import com.ca.mas.core.storage.implementation.MASStorageManager;
import com.ca.mas.core.store.ClientCredentialContainer;
import com.ca.mas.core.store.OAuthTokenContainer;
import com.ca.mas.core.store.StorageProvider;
import com.ca.mas.core.store.TokenManager;
import com.ca.mas.foundation.MAS;
import com.ca.mas.foundation.MASAuthenticationListener;
import com.ca.mas.foundation.MASCallback;
import com.ca.mas.foundation.MASConfiguration;
import com.ca.mas.foundation.MASDevice;
import com.ca.mas.foundation.MASOtpAuthenticationHandler;
import com.ca.mas.foundation.MASRequest;
import com.ca.mas.foundation.MASRequestBody;
import com.ca.mas.foundation.MASResponse;
import com.ca.mas.foundation.MASResponseBody;
import com.ca.mas.foundation.MASUser;
import com.ca.mas.foundation.auth.MASAuthenticationProviders;
import com.ca.mas.ui.MASLoginActivity;


import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.cert.X509Certificate;
import java.util.concurrent.ExecutionException;

import sun.util.calendar.LocalGregorianCalendar;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity {

    private static final int MENU_GROUP_LOGOUT = 66;
    private static final int MENU_ITEM_LOG_OUT = 3;
    private static final int MENU_ITEM_REMOVE_DEVICE_REGISTRATION = 4;
    private static final int MENU_ITEM_DESTROY_TOKEN_STORE = 2;
    int versionCode = 0;
    String versionName = null;
    private TextView mTvVersion = null;
    TextView apiResponse;
    private OAuthTokenContainer privateTokens;
    private final String content = "{\"test\":\"value\"}";
    private final String jwtPath = "/jwtVerification";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvVersion = (TextView) findViewById(R.id.app_version);
        apiResponse = (TextView) findViewById(R.id.apiResponse);

        try {
            PackageInfo packageInfo = this.getPackageManager()
                    .getPackageInfo(this.getPackageName(), 0);
            versionCode = packageInfo.versionCode;
            versionName = packageInfo.versionName;

            Log.d("ESCALATION", "Current App version code::: " + versionCode);

        } catch (PackageManager.NameNotFoundException e) {
            Log.d("ESCALATION", "Package name not found");
        }

        MAS.start(this);
        MAS.debug();
        //MAS.DEBUG("MAS", Log.d(""));

        TokenManager mgr = StorageProvider.getInstance().getTokenManager();
        mTvVersion.setText("App Version code " + versionCode + "\n" + "Version Name::: " + versionName);

//        if(versionCode == 18) {
//            Log.d("ESCALATION", "Version code matched");
//
////            Log.d("ESCALATION", "Before Upgrade::::Token store verification::: " + mgr.isTokenStoreReady());
////            Log.d("ESCALATION", "Before Upgrade::::IDToken::: " + mgr.getIdToken());
////            Log.d("ESCALATION", "Before Upgrade::::MAG Identifier::: " + mgr.getMagIdentifier());
////            Log.d("ESCALATION", "Before Upgrade::::User Profile::: " + mgr.getUserProfile());
////            Log.d("ESCALATION", "Before Upgrade::::Private key::: " + mgr.getClientPrivateKey());
////            Log.d("ESCALATION", "Before Upgrade::::Public key::: " + mgr.getClientPublicKey());
////            Log.d("ESCALATION", "After Upgrade::::Client certificate::: "+mgr.getClientCertificateChain().toString());
//
//            //printStorageProviderLogs();
//            doResetLocally();
//
//        }
        //else if(versionCode == 19)
        // {

//            doResetLocally();
        // printStorageProviderLogs();
//            Log.d("ESCALATION", "Version code matched");
//            Log.d("ESCALATION", "After Upgrade::::Token store verification::: "+mgr.isTokenStoreReady());
//            Log.d("ESCALATION", "After Upgrade::::IDToken::: "+mgr.getIdToken());
//            Log.d("ESCALATION", "After Upgrade::::MAG Identifier::: "+mgr.getMagIdentifier());
//            Log.d("ESCALATION", "After Upgrade::::User Profile::: "+mgr.getUserProfile());
//            Log.d("ESCALATION", "After Upgrade::::Private key::: "+mgr.getClientPrivateKey());
//            Log.d("ESCALATION", "After Upgrade::::Public key::: "+mgr.getClientPublicKey());
//            Log.d("ESCALATION", "After Upgrade::::Client certificate::: "+mgr.getClientCertificateChain().toString());
        //mgr.getClientCertificateChain();

//            try {
//                final Storage storage = new MASStorageManager().getStorage(MASStorageManager.MASStorageType.TYPE_KEYSTORE, new Object[]{getApplicationContext(), false});
//                storage.getAllKeys().getData();
//
//            } catch (StorageException e) {
//                if (e.getCode() == StorageException.STORE_NOT_UNLOCKED) {
//                    Log.d("ESCALATION", "KEYSTORE  is Locked!");
//                } else {
//                    Log.d("ESCALATION", "KEYSTORE Storage instantiation failed " + e);
//                }
//
//            }
        //}

//        showMessage("MAG Identifier:::"+mgr.getMagIdentifier()+" \n"+"IDToken::: "+mgr.getIdToken(), Toast.LENGTH_LONG);
//        MAS.setAuthenticationListener(new MASAuthenticationListener() {
//            @Override
//            public void onAuthenticateRequest(Context context, long requestId, MASAuthenticationProviders providers) {
//                Intent intent = new Intent(context, MASLoginActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra(MssoIntents.EXTRA_REQUEST_ID, requestId);
//                intent.putExtra(MssoIntents.EXTRA_AUTH_PROVIDERS, providers);
//                context.startActivity(intent);
//            }
//
//            @Override
//            public void onOtpAuthenticateRequest(Context context, MASOtpAuthenticationHandler handler) {
//                //Ignore for now
//            }
//        });

//        MASUnitCallbackFuture<MASResponse<JSONObject>> callback = new MASUnitCallbackFuture<>();
//        MASRequest request = null;
//        try {
//            request = new MASRequest.MASRequestBuilder(new URI(jwtPath))
//                    .post(MASRequestBody.jsonBody(new JSONObject(content)))
//                    .sign()
//                    .notifyOnCancel()
//                    .build();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        MAS.invoke(request, callback);
//        MASResponseBody<JSONObject> jsonResponseBody = null;
//        try {
//            jsonResponseBody = callback.get().getBody();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        JSONObject json = jsonResponseBody.getContent();
////        assertEquals(json.get("valid"), true);
//        try {
//            JSONObject payload = json.getJSONObject("payload");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        /*MASUser.login("admin", "7layer".toCharArray(), new MASCallback<MASUser>() {
            @Override
            public void onSuccess(MASUser user) {
                Log.d("ESCALATION", "Logged in as:: Identifier:: " + MASDevice.getCurrentDevice().getIdentifier());
                Log.d("ESCALATION", "Login in as: User Name:: " +MASUser.getCurrentUser().getUserName());
                getFlightList();
            }

            @Override
            public void onError(Throwable e) {
            }
        });*/
        MASUser.login(new MASCallback<MASUser>() {
            @Override
            public void onSuccess(MASUser result) {
                Log.d("ESCALATION", "Logged in as:: Identifier:: " + MASDevice.getCurrentDevice().getIdentifier());
                Log.d("ESCALATION", "Login in as: User Name:: " + MASUser.getCurrentUser().getUserName());
                getFlightList();
            }

            @Override
            public void onError(Throwable e) {
                Log.d("ESCALATION", "Login failure: " + e);
            }
        });

    }


//    public void printStorageProviderLogs(){
//        TokenManager mgr = StorageProvider.getInstance().getTokenManager();
////        Log.d("ESCALATION", "After Upgrade::::Token store verification::: "+mgr.isTokenStoreReady());
////        Log.d("ESCALATION", "After Upgrade::::Token store verification::: "+mgr.getClientPrivateKey());
////        Log.d("ESCALATION", "After Upgrade::::IDToken Type::: "+mgr.getIdToken().getType());
////        Log.d("ESCALATION", "After Upgrade::::IDToken value::: "+mgr.getIdToken().getValue());
////        Log.d("ESCALATION", "After Upgrade::::MAG Identifier::: "+mgr.getMagIdentifier());
////        Log.d("ESCALATION", "After Upgrade::::User Profile::: "+mgr.getUserProfile());
////        Log.d("ESCALATION", "After Upgrade::::Private key::: "+mgr.getClientPublicKey());
//        ClientCredentialContainer clientCredentialContainer = StorageProvider.getInstance().getClientCredentialContainer();
////        Log.d("ESCALATION", "After Upgrade::::ClientId::: "+clientCredentialContainer.getClientId());
////        Log.d("ESCALATION", "After Upgrade::::ClientSecret::: "+clientCredentialContainer.getClientSecret());
//        OAuthTokenContainer oAuthTokenContainer = StorageProvider.getInstance().getOAuthTokenContainer();
////        Log.d("ESCALATION", "After Upgrade::::AccessToken::: "+oAuthTokenContainer.getAccessToken());
////        Log.d("ESCALATION", "After Upgrade::::RefreshToke::: "+oAuthTokenContainer.getRefreshToken());
////        X509Certificate[] chain = mgr.getClientCertificateChain();
////        for (int i = 0; i < chain.length; i++) {
////            Log.d("ESCALATION", "After Upgrade::::MAG client Certificate Chain=" + i + " ->" + chain[i]);
////            chain[i].getSignature();
////        }
//    }

    public void getFlightList() {
        try {
            URI flightList = new URI("/retrieveFlights");
            final MASRequest request = new MASRequest.MASRequestBuilder(flightList).build();

            MAS.invoke(request, new MASCallback<MASResponse<JSONObject>>() {

                @Override
                public Handler getHandler() {
                    return new Handler(Looper.getMainLooper());
                }

                @Override
                public void onSuccess(MASResponse<JSONObject> result) {
                    Log.d("ESCALATION", "Getting Flight List, got result: " + result.getBody().getContent());
                    apiResponse.setText(result.getBody().getContent().toString());
                    showMessage("Airline info: " + result.getBody().getContent(), Toast.LENGTH_LONG);
                }

                @Override
                public void onError(Throwable e) {
                    Log.d("ESCALATION", "Getting Flight List, got error: " + e);
                    if (e.getCause() instanceof TargetApiException) {
                        apiResponse.setText(new String(((TargetApiException) e.getCause()).getResponse()
                                .getBody().getRawContent()));
                        showMessage(new String(((TargetApiException) e.getCause()).getResponse()
                                .getBody().getRawContent()), Toast.LENGTH_SHORT);
                    } else {
                        apiResponse.setText("Error: " + e.getMessage());
                        showMessage("Error: " + e.getMessage(), Toast.LENGTH_LONG);
                    }
                }
            });

        } catch (Exception x) {
            Log.d("ESCALATION", "ERROR Getting Flight List");
        }

    }


    public void showMessage(final String message, final int toastLength) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, message, toastLength).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Settings) {
            return true;
        } else if (id == R.id.Logout) {
            doServerLogout();
            return true;
        } else if (id == R.id.Reset) {
            doResetLocally();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void doResetLocally() {
//        MASDevice.getCurrentDevice().resetLocally();
//        Log.d("ESCALATION", "Reset locally is completed");
//        showMessage("Device Registration Destroyed (client only)", Toast.LENGTH_LONG);

        TokenManager mgr = StorageProvider.getInstance().getTokenManager();

        privateTokens = StorageProvider.getInstance().getOAuthTokenContainer();

        Log.d("ESCALATION", "After reset locally performed::::: Token store verification::: " + mgr.isTokenStoreReady());
        Log.d("ESCALATION", "After reset locally performed:::::IDToken::: " + mgr.getIdToken());
        Log.d("ESCALATION", "After reset locally performed:::::AccessToken::: " + privateTokens.getAccessToken());
        Log.d("ESCALATION", "After reset locally performed:::::Refesh Token::: " + privateTokens.getRefreshToken());
        Log.d("ESCALATION", "After reset locally performed:::::Expiry::: " + privateTokens.getExpiry());
        Log.d("ESCALATION", "After reset locally performed:::::MAG Identifier::: " + mgr.getMagIdentifier());
//        Log.d("ESCALATION", "After reset locally performed:::::User Profile::: "+mgr.getUserProfile());
//        Log.d("ESCALATION", "After reset locally performed:::::Private key::: "+mgr.getClientPublicKey());

    }

    // Log the user out of all client apps and notify the server to revoke tokens.
    private void doServerLogout() {
        //clearItem();
        if (MASUser.getCurrentUser() != null) {
            MASUser.getCurrentUser().logout(new MASCallback<Void>() {
                @Override
                public void onSuccess(Void result) {
                    showMessage("Successful Logout", Toast.LENGTH_LONG);
                }

                @Override
                public void onError(Throwable e) {
                    showMessage("Fail Logout", Toast.LENGTH_SHORT);
                }
            });
        }
    }


}
