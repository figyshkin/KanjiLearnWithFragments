package com.example.kanjilearn;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class DetailFragment extends Fragment {

    private String value;

    DataCommunication mCallback;

    private WebView webView;

    public DetailFragment() {
        // Required empty public constructor
    }

//    public static DetailFragment getNewInstance(String value){
//        DetailFragment fragment = new DetailFragment();
//        fragment.value = value;
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        // Inflate the layout for this fragment

//        Bundle bundle = this.getArguments();
//        if (bundle != null){
//            String kanji = bundle.getString("kanji");
//            Toast.makeText(getContext(),kanji,Toast.LENGTH_LONG).show();
//        }
        webView = (WebView)view.findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webView.clearCache(true);

//        ImageView myImage = (ImageView) view.findViewById(R.id.imageView2);
//        myImage.setAlpha(55); //value: [0-255]. Where 0 is fully transparent and 255 is fully opaque

        webSettings.setJavaScriptEnabled(true);
        webView.setBackgroundColor(Color.TRANSPARENT);
        if (isNetworkAvailable()) {
            //Online version
//            webView.loadUrl("https://figyshkin.github.io/" + mCallback.getMyVariableX() + ".html");
            webView.loadUrl("file:///android_asset/" + mCallback.getMyVariableX() + ".html");
        }
        else {
            //Offline version
            webView.loadUrl("file:///android_asset/" + mCallback.getMyVariableX() + ".html");
        }
        webView.setWebViewClient(new WebViewClient());

        return view;
    }

    public boolean isNetworkAvailable() {
        // Создаем ConnectivityManager
        ConnectivityManager cm = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        // Получаем информацию о состоянии сети
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();

        // Если сеть недоступна, то она становится = null
        // Проверка на подключение и вывод результата
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String test = mCallback.getMyVariableX();
        Toast.makeText(getContext(),"Вы просматриваете - "+test,Toast.LENGTH_LONG).show();

//        Bundle b = this.getArguments();
//        if (b != null){
//            String s = b.getString("Key");
//            Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
//        }
//        Toast.makeText(getContext(),this.value,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (DataCommunication) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement DataCommunication");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
