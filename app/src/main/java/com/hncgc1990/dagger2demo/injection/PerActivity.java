package com.hncgc1990.dagger2demo.injection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Administrator on 2017/4/13.
 */


@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
