package com.tangela;

import java.lang.instrument.Instrumentation;

public final class NopPreMain {

    private NopPreMain() {
        // No instances
    }

    public static void premain(final String opts, final Instrumentation inst) {
    }

}
