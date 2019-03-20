/*
* View Holder
* */

package com.bignerdranch.android.criminalintent;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CrimeListFragment extends Fragment {

    private RecyclerView mCrimeRecyclerView;  //Se crea un RecyclerView
    private CrimeAdapter mAdapter; //para el adaptador en la vista
    private Button mContPoliButton;
    private ImageView mSolvedImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();//para que se muestre la actualizacion del UI

        return view;
    }

    //para actualizar los datos
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }


    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }

    }

    //creamos el viewHolder
    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private Crime mCrime;

        public CrimeHolder(LayoutInflater inflater, ViewGroup parent) {
            // r=getItemViewType(mCrime.getId());
            super(inflater.inflate(R.layout.list_item_crime, parent, false));
            itemView.setOnClickListener(this);//hace el onclic del contenedor del crimen
            mTitleTextView = (TextView) itemView.findViewById(R.id.crime_title);
            mDateTextView = (TextView) itemView.findViewById(R.id.crime_date);
            //mContPoliButton = (Button) itemView.findViewById(R.id.contact_police_button);
        }


        //actualiza informacion del crimen
        public void bind(Crime crime) {

            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedImageView = (ImageView) itemView.findViewById(R.id.crime_solved);
            mSolvedImageView.setVisibility(crime.isSolved() ? View.VISIBLE : View.GONE);

        }

        //hace el evento del toast al dar clic sobre un crimen
        @Override
        public void onClick(View view) {
            //comenzamos el activity
            //Intent intent = new Intent(getActivity(), MainActivity.class);
            //se llama al MainActivity con los datos del crimen con cierto id
            Intent intent = MainActivity.newIntent(getActivity(), mCrime.getId());
            startActivity(intent);

            /*Toast.makeText(getActivity(),
                    mCrime.getTitle() + " clicked!", Toast.LENGTH_SHORT)
                    .show();*/
        }

    }//fin crimeHolder

    //creamos el adaptador
    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder> {
        private List<Crime> mCrimes;
        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @NonNull
        @Override
        public CrimeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CrimeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CrimeHolder holder, int position) {
            //la ligereza de este metodo nos permite que el desplazamiento sea rapido
            Crime crime = mCrimes.get(position);
            holder.bind(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }


        //mContPoliButton = (Button) findViewById(R.id.contact_police_button);
        @Override
        public int getItemViewType(int position) {
            Crime crime = mCrimes.get(position);
            if (crime.isRequiresPolice()) {
                return R.layout.list_item_crime_police;

            } else {
                return R.layout.list_item_crime;
            }
        }

    }//fin crimeAdapter

}
