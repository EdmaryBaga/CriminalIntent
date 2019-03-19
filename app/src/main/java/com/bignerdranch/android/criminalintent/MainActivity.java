//CrimeActivity

package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;//para añadir el fragmento
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends  SingleFragmentActivity{

    @Override
    protected Fragment createFragment() {
        return new CrimeFragment();
    }




  /**
   * Esto ahora se llama desde singleFragment Activity y para volcver a usar esta neceditas extends de AppCompatActivity
   * @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        //añadimos el fragment
        FragmentManager fm = getSupportFragmentManager();

        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = new CrimeFragment();//llama a nuestro CrimeFragment
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit(); }

    }*/
}
