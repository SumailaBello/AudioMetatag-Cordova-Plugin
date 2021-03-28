/**
 */
package com.example;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import android.os.Environment;

import android.util.Log;

import java.util.Date;

final String MEDIA_PATH = Environment.getExternalStorageDirectory()+"";

public class AudioModel {

    String aPath;
    String aName;
    String aAlbum;
    String aArtist;

    public String getaPath() {
        return aPath;
    }

    public void setaPath(String aPath) {
        this.aPath = aPath;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getaAlbum() {
        return aAlbum;
    }

    public void setaAlbum(String aAlbum) {
        this.aAlbum = aAlbum;
    }

    public String getaArtist() {
        return aArtist;
    }

    public void setaArtist(String aArtist) {
        this.aArtist = aArtist;
    }
}

public class AudioMetadata extends CordovaPlugin {
  private static final String TAG = "AudioMetadata";

  public void initialize(CordovaInterface cordova, CordovaWebView webView) {
    super.initialize(cordova, webView);

    Log.d(TAG, "Initializing AudioMetadata");
  }

  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    if(action.equals("echo")) {
      String phrase = args.getString(0);
      // Echo back the first argument
      Log.d(TAG, phrase);
    } else if(action.equals("getDate")) {
      // An example of returning data back to the web layer
      final PluginResult result = new PluginResult(PluginResult.Status.OK, (new Date()).toString());
      callbackContext.sendPluginResult(result);
    } 
    else if(action.equals("getFiles")) {
      
      public List<AudioModel> getAllAudioFromDevice(final Context context) {

          final List<AudioModel> tempAudioList = new ArrayList<>();

          Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
          String[] projection = {MediaStore.Audio.AudioColumns.DATA, MediaStore.Audio.AudioColumns.ALBUM, MediaStore.Audio.ArtistColumns.ARTIST,};
          Cursor c = context.getContentResolver().query(uri,
                                          projection, 
                                          null, 
                                          null, 
                                          null);

          if (c != null) {
              while (c.moveToNext()) {

                  AudioModel audioModel = new AudioModel();
                  String path = c.getString(0);
                  String album = c.getString(1);
                  String artist = c.getString(2);

                  String name = path.substring(path.lastIndexOf("/") + 1);

                  audioModel.setaName(name);
                  audioModel.setaAlbum(album);
                  audioModel.setaArtist(artist);
                  audioModel.setaPath(path);

                  Log.e("Name :" + name, " Album :" + album);
                  Log.e("Path :" + path, " Artist :" + artist);

                  tempAudioList.add(audioModel);
              }
              c.close();
          }

          // An example of returning data back to the web layer
          final PluginResult result = new PluginResult(PluginResult.Status.OK, (tempAudioList));
          callbackContext.sendPluginResult(result);
          return tempAudioList;
        }

    }
    return true;
  }

}
