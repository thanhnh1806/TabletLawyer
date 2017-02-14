package tabletlawyer.uk.tabletlawyer;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentActivity;

public class ConnectionDetector {

	public ConnectionDetector(Context activity) {
	}

	/**
	 * Checking for all possible internet providers
	 * **/
	public static boolean isConnectingToInternet(Context context) {
		if (context == null) {
			throw new IllegalArgumentException("context can not be null.");
		}
		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}

		}
		return false;
	}
}