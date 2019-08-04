package com.dacompsc.general;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.dacompsc.general.base.BaseSharedActivity;
import com.dacompsc.general.util.DebugClass;
import com.dacompsc.general.util.Tipo;

public class MainActivity extends BaseSharedActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DebugClass.printTLog("Hola", Tipo.DEBUG,"Hola");
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }
}
