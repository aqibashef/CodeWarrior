package com.technext.cwc;

import com.technext.cwc.fragments.LoginFragment;
import com.technext.cwc.fragments.RegistrationFragment;
import com.technext.cwc.fragments.SocialNetworkChooserFragment;

import android.graphics.Color;
import android.os.Bundle;
import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;

public class MockActivity extends MaterialNavigationDrawer {

	@Override
	public void init(Bundle savedInstanceState) {
		MaterialAccount account = new MaterialAccount(this.getResources(),"NeoKree","neokree@gmail.com", R.drawable.ic_launcher, R.drawable.profile_bg);
        this.addAccount(account);

        // create sections
        this.addSection(newSection("Login", LoginFragment.newInstance(1)));
        this.addSection(newSection("Registaration",RegistrationFragment.newInstance(2)));
        this.addSection(newSection("Share",R.drawable.camera,SocialNetworkChooserFragment.newInstance(3)).setSectionColor(Color.parseColor("#9c27b0")));

        // create bottom section
//        this.addBottomSection(newSection("Bottom Section",R.drawable.ic_settings_black_24dp,new Intent(this,Settings.class)));
	}

}
