package com.khetans.evening.easyswitch2gto3g;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class Direct_Switch extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_direct__switch);

        //To check the connectivity
        Context context2 = getApplicationContext();
        boolean text2 = Connectivity.isConnected(context2);
        CharSequence tex3;
        tex3 = "No Idea";
        if(text2 == true)
        {
            tex3 = "Is Connected";
        }
        else
        {
            tex3 = "Is NOT Connected";
        }

        //To give the toast notification
        Context context = getApplicationContext();
        CharSequence text = "Hello Amit Khetan !";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, tex3, duration);
        toast.show();

        //To end the app
        finish();
          }

   /* public void switch_network(View view)
    {
        private TelephonyManager telephonyManager;
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        CurrentNetworkType = telephonyManager.getNetworkType();

        //   public static final int NETWORK_TYPE_1xRTT
        //   Since: API Level 4
        //   Current network is 1xRTT
        //   Constant Value: 7 (0x00000007)
//
//   public static final int NETWORK_TYPE_CDMA
//   Since: API Level 4
//   Current network is CDMA: Either IS95A or IS95B
//   Constant Value: 4 (0x00000004)
//
//   public static final int NETWORK_TYPE_EDGE
//   Since: API Level 1
//   Current network is EDGE
//   Constant Value: 2 (0x00000002)
//
//   public static final int NETWORK_TYPE_EHRPD
//   Since: API Level 11
//   Current network is eHRPD
//   Constant Value: 14 (0x0000000e)
//
//   public static final int NETWORK_TYPE_EVDO_0
//   Since: API Level 4
//   Current network is EVDO revision 0
//   Constant Value: 5 (0x00000005)
//
//   public static final int NETWORK_TYPE_EVDO_A
//   Since: API Level 4
//   Current network is EVDO revision A
//   Constant Value: 6 (0x00000006)
//
//   public static final int NETWORK_TYPE_EVDO_B
//   Since: API Level 9
//   Current network is EVDO revision B
//   Constant Value: 12 (0x0000000c)
//
//   public static final int NETWORK_TYPE_GPRS
//   Since: API Level 1
//   Current network is GPRS
//   Constant Value: 1 (0x00000001)
//
//   public static final int NETWORK_TYPE_HSDPA
//   Since: API Level 5
//   Current network is HSDPA
//   Constant Value: 8 (0x00000008)
//
//   public static final int NETWORK_TYPE_HSPA
//   Since: API Level 5
//   Current network is HSPA
//   Constant Value: 10 (0x0000000a)
//
//   public static final int NETWORK_TYPE_HSPAP
//   Since: API Level 13
//   Current network is HSPA+
//   Constant Value: 15 (0x0000000f)
//
//   public static final int NETWORK_TYPE_HSUPA
//   Since: API Level 5
//   Current network is HSUPA
//   Constant Value: 9 (0x00000009)
//
//   public static final int NETWORK_TYPE_IDEN
//   Since: API Level 8
//   Current network is iDen
//   Constant Value: 11 (0x0000000b)
//
//   public static final int NETWORK_TYPE_LTE
//   Since: API Level 11
//   Current network is LTE
//   Constant Value: 13 (0x0000000d)
//
//   public static final int NETWORK_TYPE_UMTS
//   Since: API Level 1
//   Current network is UMTS
//   Constant Value: 3 (0x00000003)
//
//   public static final int NETWORK_TYPE_UNKNOWN
//   Since: API Level 1
//   Network type is unknown
//   Constant Value: 0 (0x00000000)
    }*/

    /**
     * Check device's network connectivity and speed
     * @author emil http://stackoverflow.com/users/220710/emil
     *
     */
    public static class Connectivity {

        /**
         * Get the network info
         * context
         * return
         */
        public static NetworkInfo getNetworkInfo(Context context){
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo();
        }

        /**
         * Check if there is any connectivity
         * context
         * return
         */
        public static boolean isConnected(Context context){
            NetworkInfo info = Connectivity.getNetworkInfo(context);
            return (info != null && info.isConnected());
        }

        /**
         * Check if there is any connectivity to a Wifi network
         * context
         * type
         * return
         */
        public static boolean isConnectedWifi(Context context){
            NetworkInfo info = Connectivity.getNetworkInfo(context);
            return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
        }

        /**
         * Check if there is any connectivity to a mobile network
         * context
         * type
         * return
         */
        public static boolean isConnectedMobile(Context context){
            NetworkInfo info = Connectivity.getNetworkInfo(context);
            return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE);
        }

        /**
         * Check if there is fast connectivity
         * context
         * return
         */
        public static boolean isConnectedFast(Context context){
            NetworkInfo info = Connectivity.getNetworkInfo(context);
            return (info != null && info.isConnected() && Connectivity.isConnectionFast(info.getType(),info.getSubtype()));
        }

        /**
         * Check if the connection is fast
         * type
         * subType
         * return
         */
        public static boolean isConnectionFast(int type, int subType){
            if(type==ConnectivityManager.TYPE_WIFI){
                return true;
            }else if(type==ConnectivityManager.TYPE_MOBILE){
                switch(subType){
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                        return false; // ~ 50-100 kbps
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                        return false; // ~ 14-64 kbps
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                        return false; // ~ 50-100 kbps
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                        return true; // ~ 400-1000 kbps
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                        return true; // ~ 600-1400 kbps
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                        return false; // ~ 100 kbps
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                        return true; // ~ 2-14 Mbps
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                        return true; // ~ 700-1700 kbps
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                        return true; // ~ 1-23 Mbps
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                        return true; // ~ 400-7000 kbps
            /*
             * Above API level 7, make sure to set android:targetSdkVersion
             * to appropriate level to use these
             */
                    case TelephonyManager.NETWORK_TYPE_EHRPD: // API level 11
                        return true; // ~ 1-2 Mbps
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: // API level 9
                        return true; // ~ 5 Mbps
                    case TelephonyManager.NETWORK_TYPE_HSPAP: // API level 13
                        return true; // ~ 10-20 Mbps
                    case TelephonyManager.NETWORK_TYPE_IDEN: // API level 8
                        return false; // ~25 kbps
                    case TelephonyManager.NETWORK_TYPE_LTE: // API level 11
                        return true; // ~ 10+ Mbps
                    // Unknown
                    case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                    default:
                        return false;
                }
            }else{
                return false;
            }
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_direct__switch, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
