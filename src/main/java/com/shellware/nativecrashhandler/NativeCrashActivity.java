package com.shellware.nativecrashhandler;

import android.app.Activity;
import android.os.Bundle;

/**
 * Shell M. Shrader - 1/17/16
 * https://github.com/SalomonBrys/__deprecated__Native-Crash-Handler
 * All Rights Reserved
 */
public class NativeCrashActivity extends Activity {
    public NativeCrashActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NativeError e = (NativeError)this.getIntent().getSerializableExtra("error");
        throw e;
    }
}
