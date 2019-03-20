//CrimeActivity

package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;//para añadir el fragmento
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class MainActivity extends  SingleFragmentActivity{

    private static final String EXTRA_CRIME_ID =
            "com.bignerdranch.android.criminalintent.crime_id";
    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, MainActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected Fragment createFragment() {

        //se hace una nueva instancvia del fragmento con su paquete de atributos
        UUID crimeId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_CRIME_ID);
        return CrimeFragment.newInstance(crimeId);
        //return new CrimeFragment();
    }




  /*
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
