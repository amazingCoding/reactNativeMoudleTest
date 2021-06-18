package com.reactnativemoudletest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Create By songhang in 2021/6/18
 */
class ToolsManager extends ReactContextBaseJavaModule implements ActivityEventListener {
    ReactApplicationContext reactContext;
    Callback callback;
    public ToolsManager(@Nullable ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
        this.reactContext.addActivityEventListener(this);
    }

    @NonNull
    @Override
    public String getName() {
        return "ToolsManager";
    }

    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("SUCCESS", 1);
        constants.put("ERROR", 0);
        return constants;
    }

    @ReactMethod
    public void test(){
        Log.d("ToolsManager","test");
    }

    @ReactMethod
    public void testSimple(String name,float number,boolean flag){
        Log.d("ToolsManager",name);
        Log.d("ToolsManager", String.valueOf(number));
        Log.d("ToolsManager", String.valueOf(flag));
    }
    @ReactMethod
    public void testComplex(ReadableArray array, ReadableMap obj){
        Log.d("ToolsManager", String.valueOf(array));
        Log.d("ToolsManager", String.valueOf(obj));
    }
    @ReactMethod
    public void testCallback(Callback callback){
        WritableArray array = Arguments.createArray();
        array.pushString("arr1");
        array.pushString("arr2");
        WritableMap obj = Arguments.createMap();
        obj.putString("k1","v1");
        obj.putString("k2","v2");
        callback.invoke(1,"string",array,obj);
    }
    @ReactMethod
    public void testPromise(Boolean flag,Promise promise){
        if(flag){
            promise.resolve("result");
        }
        else {
            promise.reject("404","error");
        }
    }

    @ReactMethod
    public void testToFile(Callback callback){
        this.callback = callback;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        Uri uri = Uri.parse("/");
        intent.setDataAndType(uri, "*/*");
        Activity activity = getCurrentActivity();
        if(activity != null){
            activity.startActivity(Intent.createChooser(intent, "Open folder"));
        }
    }

    @Override
    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {

    }

    @Override
    public void onNewIntent(Intent intent) {

    }
}
