package com.shellware.nativecrashhandler;

import android.content.Context;
import android.content.Intent;

/**
 * Shell M. Shrader - 1/17/16
 * https://github.com/SalomonBrys/__deprecated__Native-Crash-Handler
 * All Rights Reserved
 */
public class NativeCrashHandler {
    Context ctx;

    public NativeCrashHandler() {
    }

    private void makeCrashReport(String reason, StackTraceElement[] stack, int threadID) {
        if(stack != null) {
            NativeError.natSt = stack;
        }

        NativeError e = new NativeError(reason, threadID);
        Intent intent = new Intent(this.ctx, NativeCrashActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("error", e);
        this.ctx.startActivity(intent);
    }

    public void registerForNativeCrash(Context ctx) {
        this.ctx = ctx;
        if(!this.nRegisterForNativeCrash()) {
            throw new RuntimeException("Could not register for native crash as nativeCrashHandler_onLoad was not called in JNI context");
        }
    }

    public void unregisterForNativeCrash() {
        this.ctx = null;
        this.nUnregisterForNativeCrash();
    }

    private native boolean nRegisterForNativeCrash();

    private native void nUnregisterForNativeCrash();
}