package com.google.inject.failfast;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.Stage;

/**
 * Created by wyt on 16-10-24.
 */
public class FailFastTest {

    public static void main(String[] args) {
       Injector injector =  Guice.createInjector(Stage.PRODUCTION, new AbstractModule() {
            @Override
            protected void configure() {
                binder().requireExplicitBindings();
                bind(C1.class).in(Singleton.class);
                bind(C2.class).in(Singleton.class);
                bind(C3.class).in(Singleton.class);
            }
        });

        injector.createChildInjector(new AbstractModule() {
            @Override
            protected void configure() {
                binder().requireExplicitBindings();
                bind(C11.class).to(C22.class).in(Singleton.class);
            }
        });

        System.out.println(injector.getInstance(C3.class));
    }

    static class C1 {
        @Inject
        C1() {
            throw new RuntimeException("fail fast!");
        }
    }

    static class C2 {
        @Inject
        C2(C1 c1) {

        }
    }

    static class C3 {
        @Inject
        C3(C1 c1) {

        }
    }

    static class C11 {
    }

    static class C22 extends C11 {

    }
}
