package com.github.jcapitanmoreno.view;

import java.io.IOException;

public abstract class Controller {

    public abstract void onOpen(Object input) throws IOException;

    public abstract void onClose(Object output);
}
