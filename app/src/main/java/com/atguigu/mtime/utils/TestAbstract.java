package com.atguigu.mtime.utils;

import android.view.View;

import java.net.URLConnection;

/**
 * Created by HanFeng on 2015/12/15.
 */
public abstract class TestAbstract {


}

abstract class TestA extends TestAbstract{

}

abstract class TestB implements View.OnClickListener{

}

class B extends TestA{

}