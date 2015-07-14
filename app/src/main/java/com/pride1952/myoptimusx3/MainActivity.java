package com.pride1952.myoptimusx3;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrixColorFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {

    /**
     * 按下这个按钮进行的颜色过滤
     */
    public final static float[] SELECTED=new float[] {
            1, 0, 0, 0, 0,
            0, 1, 0, 0, 50,
            0, 0, 1, 0, 100,
            0, 0, 0, 1, 0 };

    /**
     * 按钮恢复原状的颜色过滤
     */
    public final static float[] NOT_SELECTED=new float[] {
            1, 0, 0, 0, 0,
            0, 1, 0, 0, 0,
            0, 0, 1, 0, 0,
            0, 0, 0, 1, 0 };

    private ImageButton mBtnRing;
    private ImageButton mBtnContact;
    private ImageButton mBtnMe;
    private ImageButton mBtnPlus;

    private RingFragment mRing;
    private ContactFragment mContact;
    private MeFragment mMe;

    PopupWindow mPopPlusMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnRing=(ImageButton)findViewById(R.id.imgbtn_ring);
        mBtnContact=(ImageButton)findViewById(R.id.imgbtn_contact);
        mBtnMe=(ImageButton)findViewById(R.id.imgbtn_me);
        mBtnPlus=(ImageButton)findViewById(R.id.imgbtn_plus);

        mBtnRing.setOnClickListener(this);
        mBtnContact.setOnClickListener(this);
        mBtnMe.setOnClickListener(this);
        mBtnPlus.setOnClickListener(this);

        setDefaultFragment();
        initPopupWindow();
    }

    private void setDefaultFragment()
    {
        mBtnRing.getBackground().setColorFilter(new ColorMatrixColorFilter(SELECTED));
        mBtnRing.setBackground(mBtnRing.getBackground());
        mBtnContact.getBackground().setColorFilter(new ColorMatrixColorFilter(NOT_SELECTED));
        mBtnContact.setBackground(mBtnContact.getBackground());
        mBtnMe.getBackground().setColorFilter(new ColorMatrixColorFilter(NOT_SELECTED));
        mBtnMe.setBackground(mBtnMe.getBackground());

        FragmentManager fm=getFragmentManager();
        FragmentTransaction trasaction=fm.beginTransaction();
        mRing=new RingFragment();
        trasaction.replace(R.id.body, mRing);
        trasaction.commit();
    }

    private void initPopupWindow()
    {
        View view=this.getLayoutInflater().inflate(R.layout.popupwindow_plusmenu,null);
        mPopPlusMenu=new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopPlusMenu.setOutsideTouchable(true);

        view.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mPopPlusMenu.dismiss();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        FragmentManager fm=getFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();

        switch (v.getId())
        {
            case R.id.imgbtn_ring:
                mBtnRing.getBackground().setColorFilter(new ColorMatrixColorFilter(SELECTED));
                mBtnRing.setBackground(mBtnRing.getBackground());
                mBtnContact.getBackground().setColorFilter(new ColorMatrixColorFilter(NOT_SELECTED));
                mBtnContact.setBackground(mBtnContact.getBackground());
                mBtnMe.getBackground().setColorFilter(new ColorMatrixColorFilter(NOT_SELECTED));
                mBtnMe.setBackground(mBtnMe.getBackground());

                if(mRing==null)
                {
                    mRing=new RingFragment();
                }
                transaction.replace(R.id.body,mRing);
                break;
            case R.id.imgbtn_contact:
                mBtnRing.getBackground().setColorFilter(new ColorMatrixColorFilter(NOT_SELECTED));
                mBtnRing.setBackground(mBtnRing.getBackground());
                mBtnContact.getBackground().setColorFilter(new ColorMatrixColorFilter(SELECTED));
                mBtnContact.setBackground(mBtnContact.getBackground());
                mBtnMe.getBackground().setColorFilter(new ColorMatrixColorFilter(NOT_SELECTED));
                mBtnMe.setBackground(mBtnMe.getBackground());

                if(mContact==null)
                {
                    mContact=new ContactFragment();
                }
                transaction.replace(R.id.body, mContact);
                break;
            case R.id.imgbtn_me:
                mBtnRing.getBackground().setColorFilter(new ColorMatrixColorFilter(NOT_SELECTED));
                mBtnRing.setBackground(mBtnRing.getBackground());
                mBtnContact.getBackground().setColorFilter(new ColorMatrixColorFilter(NOT_SELECTED));
                mBtnContact.setBackground(mBtnContact.getBackground());
                mBtnMe.getBackground().setColorFilter(new ColorMatrixColorFilter(SELECTED));
                mBtnMe.setBackground(mBtnMe.getBackground());

                if(mMe==null)
                {
                    mMe=new MeFragment();
                }
                transaction.replace(R.id.body,mMe);
                break;
            case R.id.imgbtn_plus:
                if(!mPopPlusMenu.isShowing())
                {
                    mPopPlusMenu.showAtLocation(findViewById(R.id.main), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,findViewById(R.id.bottombar).getHeight());
                }
                else
                {
                    mPopPlusMenu.dismiss();
                }
                break;
            default:
                mPopPlusMenu.dismiss();
                break;

        }
        transaction.commit();
    }
}
