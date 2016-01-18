package com.shellware.nativecrashhandler;

/**
 * Shell M. Shrader - 1/17/16
 * https://github.com/SalomonBrys/__deprecated__Native-Crash-Handler
 * All Rights Reserved
 */
public class NativeError extends Error {
    private static final long serialVersionUID = 1L;
    static StackTraceElement[] natSt = new StackTraceElement[0];

    public NativeError(String reason, int threadID) {
        super(reason + " in thread " + threadID);
    }

    public Throwable fillInStackTrace() {
        super.fillInStackTrace();
        StackTraceElement[] st = this.getStackTrace();
        StackTraceElement[] clst = new StackTraceElement[natSt.length + st.length - 1];
        int p = 0;

        int i;
        for(i = 0; i < natSt.length; ++i) {
            clst[p++] = natSt[i];
        }

        for(i = 1; i < st.length; ++i) {
            clst[p++] = st[i];
        }

        this.setStackTrace(clst);
        return this;
    }
}