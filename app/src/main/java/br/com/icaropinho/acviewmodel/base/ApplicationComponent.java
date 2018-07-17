package br.com.icaropinho.acviewmodel.base;

import javax.inject.Singleton;

import br.com.icaropinho.acviewmodel.networking.NetworkModule;
import dagger.Component;

/**
 * Created by icaro on 11/07/2018.
 */

@Singleton
@Component(modules = {
        NetworkModule.class
})
public interface ApplicationComponent {
}
