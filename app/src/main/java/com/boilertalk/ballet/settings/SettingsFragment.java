package com.boilertalk.ballet.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boilertalk.ballet.R;
import com.boilertalk.ballet.database.RPCUrl;
import com.boilertalk.ballet.toolbox.VariableHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsFragment extends Fragment {

    @BindView(R.id.settings_select_network_subtitle) TextView selectedNetwork;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // ButterKnife
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set active url name
        RPCUrl url = VariableHolder.getInstance().activeUrl();
        selectedNetwork.setText(url.getName());
    }

    // Actions

    @OnClick(R.id.settings_select_network_view)
    void selectNetworkClicked() {
        SettingsSelectNetworkFragment selectNetworkFragment = new SettingsSelectNetworkFragment();

        showFragment(selectNetworkFragment);
    }

    @OnClick(R.id.settings_tracked_tokens_view)
    void trackedTokensClicked() {
        SettingsTrackERC20TokensFragment trackERC20TokensFragment = new SettingsTrackERC20TokensFragment();

        showFragment(trackERC20TokensFragment);
    }

    @OnClick(R.id.settings_change_password_view)
    void changePasswordClicked() {

    }

    // Helpers

    private void showFragment(Fragment fragment) {
        FragmentManager manager = getFragmentManager();
        if (manager == null) {
            return;
        }

        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.detach(this);
        fragmentTransaction.setPrimaryNavigationFragment(fragment);
        fragmentTransaction.add(R.id.navigation_content_view, fragment, "details");
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
