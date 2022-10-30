package com.svalero.contador.util;

import java.io.File;
import java.net.URL;

public class R {

    public static URL getUI(String name) {
        return Thread.currentThread().getContextClassLoader().getResource("ui" + File.separator + name);
    }
}
