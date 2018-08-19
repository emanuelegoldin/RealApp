import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

class Main extends Context {
    private static final String TAG = "MyActivity";
    // Instantiate the RequestQueue.
    public void main() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://www.google.com";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d(TAG, "Response is: " + response.substring(0, 500));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "That didn't work!");
            }
        });

        queue.add(stringRequest);
    }

    public AssetManager getAssets() {
        return null;
    }

    public Resources getResources() {
        return null;
    }

    public PackageManager getPackageManager() {
        return null;
    }

    public ContentResolver getContentResolver() {
        return null;
    }

    public Looper getMainLooper() {
        return null;
    }

    public Context getApplicationContext() {
        return null;
    }

    public void setTheme(int i) {

    }

    public Resources.Theme getTheme() {
        return null;
    }

    public ClassLoader getClassLoader() {
        return null;
    }

    public String getPackageName() {
        return null;
    }

    public ApplicationInfo getApplicationInfo() {
        return null;
    }

    public String getPackageResourcePath() {
        return null;
    }

    public String getPackageCodePath() {
        return null;
    }

    public SharedPreferences getSharedPreferences(String s, int i) {
        return null;
    }

    public boolean moveSharedPreferencesFrom(Context context, String s) {
        return false;
    }

    public boolean deleteSharedPreferences(String s) {
        return false;
    }

    public FileInputStream openFileInput(String s) throws FileNotFoundException {
        return null;
    }

    public FileOutputStream openFileOutput(String s, int i) throws FileNotFoundException {
        return null;
    }

    public boolean deleteFile(String s) {
        return false;
    }

    public File getFileStreamPath(String s) {
        return null;
    }

    public File getDataDir() {
        return null;
    }

    public File getFilesDir() {
        return null;
    }

    public File getNoBackupFilesDir() {
        return null;
    }

    @Nullable
    public File getExternalFilesDir(@Nullable String s) {
        return null;
    }

    public File[] getExternalFilesDirs(String s) {
        return new File[0];
    }

    public File getObbDir() {
        return null;
    }

    public File[] getObbDirs() {
        return new File[0];
    }

    public File getCacheDir() {
        return null;
    }

    public File getCodeCacheDir() {
        return null;
    }

    @Nullable
    public File getExternalCacheDir() {
        return null;
    }

    public File[] getExternalCacheDirs() {
        return new File[0];
    }

    public File[] getExternalMediaDirs() {
        return new File[0];
    }

    public String[] fileList() {
        return new String[0];
    }

    public File getDir(String s, int i) {
        return null;
    }

    public SQLiteDatabase openOrCreateDatabase(String s, int i, SQLiteDatabase.CursorFactory cursorFactory) {
        return null;
    }

    public SQLiteDatabase openOrCreateDatabase(String s, int i, SQLiteDatabase.CursorFactory cursorFactory, @Nullable DatabaseErrorHandler databaseErrorHandler) {
        return null;
    }

    public boolean moveDatabaseFrom(Context context, String s) {
        return false;
    }

    public boolean deleteDatabase(String s) {
        return false;
    }

    public File getDatabasePath(String s) {
        return null;
    }

    public String[] databaseList() {
        return new String[0];
    }

    public Drawable getWallpaper() {
        return null;
    }

    public Drawable peekWallpaper() {
        return null;
    }

    public int getWallpaperDesiredMinimumWidth() {
        return 0;
    }

    public int getWallpaperDesiredMinimumHeight() {
        return 0;
    }

    public void setWallpaper(Bitmap bitmap) throws IOException {

    }

    public void setWallpaper(InputStream inputStream) throws IOException {

    }

    public void clearWallpaper() throws IOException {

    }

    public void startActivity(Intent intent) {

    }

    public void startActivity(Intent intent, @Nullable Bundle bundle) {

    }

    public void startActivities(Intent[] intents) {

    }

    public void startActivities(Intent[] intents, Bundle bundle) {

    }

    public void startIntentSender(IntentSender intentSender, @Nullable Intent intent, int i, int i1, int i2) throws IntentSender.SendIntentException {

    }

    public void startIntentSender(IntentSender intentSender, @Nullable Intent intent, int i, int i1, int i2, @Nullable Bundle bundle) throws IntentSender.SendIntentException {

    }

    public void sendBroadcast(Intent intent) {

    }

    public void sendBroadcast(Intent intent, @Nullable String s) {

    }

    public void sendOrderedBroadcast(Intent intent, @Nullable String s) {

    }

    public void sendOrderedBroadcast(@NonNull Intent intent, @Nullable String s, @Nullable BroadcastReceiver broadcastReceiver, @Nullable Handler handler, int i, @Nullable String s1, @Nullable Bundle bundle) {

    }

    public void sendBroadcastAsUser(Intent intent, UserHandle userHandle) {

    }

    public void sendBroadcastAsUser(Intent intent, UserHandle userHandle, @Nullable String s) {

    }

    public void sendOrderedBroadcastAsUser(Intent intent, UserHandle userHandle, @Nullable String s, BroadcastReceiver broadcastReceiver, @Nullable Handler handler, int i, @Nullable String s1, @Nullable Bundle bundle) {

    }

    public void sendStickyBroadcast(Intent intent) {

    }

    public void sendStickyOrderedBroadcast(Intent intent, BroadcastReceiver broadcastReceiver, @Nullable Handler handler, int i, @Nullable String s, @Nullable Bundle bundle) {

    }

    public void removeStickyBroadcast(Intent intent) {

    }

    public void sendStickyBroadcastAsUser(Intent intent, UserHandle userHandle) {

    }

    public void sendStickyOrderedBroadcastAsUser(Intent intent, UserHandle userHandle, BroadcastReceiver broadcastReceiver, @Nullable Handler handler, int i, @Nullable String s, @Nullable Bundle bundle) {

    }

    public void removeStickyBroadcastAsUser(Intent intent, UserHandle userHandle) {

    }

    @Nullable
    public Intent registerReceiver(@Nullable BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        return null;
    }

    @Nullable
    public Intent registerReceiver(@Nullable BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, int i) {
        return null;
    }

    @Nullable
    public Intent registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, @Nullable String s, @Nullable Handler handler) {
        return null;
    }

    @Nullable
    public Intent registerReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, @Nullable String s, @Nullable Handler handler, int i) {
        return null;
    }

    public void unregisterReceiver(BroadcastReceiver broadcastReceiver) {

    }

    @Nullable
    public ComponentName startService(Intent intent) {
        return null;
    }

    @Nullable
    public ComponentName startForegroundService(Intent intent) {
        return null;
    }

    public boolean stopService(Intent intent) {
        return false;
    }

    public boolean bindService(Intent intent, @NonNull ServiceConnection serviceConnection, int i) {
        return false;
    }

    public void unbindService(@NonNull ServiceConnection serviceConnection) {

    }

    public boolean startInstrumentation(@NonNull ComponentName componentName, @Nullable String s, @Nullable Bundle bundle) {
        return false;
    }

    @Nullable
    public Object getSystemService(@NonNull String s) {
        return null;
    }

    @Nullable
    public String getSystemServiceName(@NonNull Class<?> aClass) {
        return null;
    }

    @SuppressLint("WrongConstant")
    public int checkPermission(@NonNull String s, int i, int i1) {
        return 0;
    }

    @SuppressLint("WrongConstant")
    public int checkCallingPermission(@NonNull String s) {
        return 0;
    }

    @SuppressLint("WrongConstant")
    public int checkCallingOrSelfPermission(@NonNull String s) {
        return 0;
    }

    @SuppressLint("WrongConstant")
    public int checkSelfPermission(@NonNull String s) {
        return 0;
    }

    public void enforcePermission(@NonNull String s, int i, int i1, @Nullable String s1) {

    }

    public void enforceCallingPermission(@NonNull String s, @Nullable String s1) {

    }

    public void enforceCallingOrSelfPermission(@NonNull String s, @Nullable String s1) {

    }

    public void grantUriPermission(String s, Uri uri, int i) {

    }

    public void revokeUriPermission(Uri uri, int i) {

    }

    public void revokeUriPermission(String s, Uri uri, int i) {

    }

    @SuppressLint("WrongConstant")
    public int checkUriPermission(Uri uri, int i, int i1, int i2) {
        return 0;
    }

    @SuppressLint("WrongConstant")
    public int checkCallingUriPermission(Uri uri, int i) {
        return 0;
    }

    @SuppressLint("WrongConstant")
    public int checkCallingOrSelfUriPermission(Uri uri, int i) {
        return 0;
    }

    @SuppressLint("WrongConstant")
    public int checkUriPermission(@Nullable Uri uri, @Nullable String s, @Nullable String s1, int i, int i1, int i2) {
        return 0;
    }

    public void enforceUriPermission(Uri uri, int i, int i1, int i2, String s) {

    }

    public void enforceCallingUriPermission(Uri uri, int i, String s) {

    }

    public void enforceCallingOrSelfUriPermission(Uri uri, int i, String s) {

    }

    public void enforceUriPermission(@Nullable Uri uri, @Nullable String s, @Nullable String s1, int i, int i1, int i2, @Nullable String s2) {

    }

    public Context createPackageContext(String s, int i) throws PackageManager.NameNotFoundException {
        return null;
    }

    public Context createContextForSplit(String s) throws PackageManager.NameNotFoundException {
        return null;
    }

    public Context createConfigurationContext(@NonNull Configuration configuration) {
        return null;
    }

    public Context createDisplayContext(@NonNull Display display) {
        return null;
    }

    public Context createDeviceProtectedStorageContext() {
        return null;
    }

    public boolean isDeviceProtectedStorage() {
        return false;
    }
}