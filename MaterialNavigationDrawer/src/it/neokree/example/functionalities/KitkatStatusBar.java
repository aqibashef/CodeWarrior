package it.neokree.example.functionalities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;
import it.neokree.example.R;
import it.neokree.example.mockedActivity.Settings;
import it.neokree.example.mockedFragments.FragmentButton;
import it.neokree.example.mockedFragments.FragmentIndex;
import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialSectionListener;

/**
 * Created by neokree on 20/01/15.
 */
public class KitkatStatusBar extends MaterialNavigationDrawer  implements MaterialSectionListener {

    @SuppressWarnings("unchecked")
	@Override
    public void init(Bundle savedInstanceState) {
        // add accounts
        MaterialAccount account = new MaterialAccount(this.getResources(),"NeoKree","neokree@gmail.com", R.drawable.photo, R.drawable.bamboo);
        this.addAccount(account);

        MaterialAccount account2 = new MaterialAccount(this.getResources(),"Hatsune Miky","hatsune.miku@example.com",R.drawable.photo2,R.drawable.mat2);
        this.addAccount(account2);

        MaterialAccount account3 = new MaterialAccount(this.getResources(),"Example","example@example.com",R.drawable.photo,R.drawable.mat3);
        this.addAccount(account3);

        // create sections
        this.addSection(newSection("Section 1", new FragmentIndex()));
        this.addSection(newSection("Section 2",new FragmentIndex()));
        this.addSection(newSection("Section 3",R.drawable.ic_mic_white_24dp,new FragmentButton()).setSectionColor(Color.parseColor("#9c27b0")));
        this.addSection(newSection("Section",R.drawable.ic_hotel_grey600_24dp,new FragmentButton()).setSectionColor(Color.parseColor("#03a9f4")));

        // create bottom section
        this.addBottomSection(newSection("Bottom Section",R.drawable.ic_settings_black_24dp,new Intent(this,Settings.class)));
    }
    
    @Override
    public void onClick(MaterialSection section) {
    	super.onClick(section);
    	Toast.makeText(this, section.getTitle(), Toast.LENGTH_LONG).show();
    }
}
