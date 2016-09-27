package com.sport;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DingweiFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DingweiFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private MapView mMapView=null;
    private BaiduMap mBaiduMap;
    private LocationClient mlocationClient;
    private MylocationListener mlistener1;
    private Context context;
    public TextView text;
    public TextView text1;
    public TextView text2;
    private double mLatitude;
    private double mLongitude;
    private float mCurrentX;
    public LocationManager locationManager;
    private ImageButton mGetMylocationBN;
    //所有点的集合
    List<LatLng> latLngPolygon = new ArrayList<LatLng>();
    public float distance=0;
    private BitmapDescriptor mIconLocation;
    public Button button;
    public Button button1;
    public DateFormat df;
    private MyOrientationListener myOrientationListener;
    private MyLocationConfiguration.LocationMode locationMode;
    //开始时间
    public Date d1;
    //结束时间
    public Date d2;
    public long minute;
    public int i=1;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DingweiFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DingweiFragment newInstance(String param1, String param2) {
        DingweiFragment fragment = new DingweiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;

    }

    public DingweiFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        SDKInitializer.initialize(getActivity().getApplicationContext());
        View view= inflater.inflate(R.layout.fragment_dingwei, container, false);
        context=getActivity().getApplicationContext();
        Log.i("message","开始");
        mMapView= (MapView) view.findViewById(R.id.bmapView);
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        text=(TextView) view.findViewById(R.id.text);
        text1=(TextView) view.findViewById(R.id.text1);
        text2=(TextView) view.findViewById(R.id.text2);
        mBaiduMap=mMapView.getMap();
        MapStatusUpdate msu= MapStatusUpdateFactory.zoomTo(18.0f);
        mBaiduMap.setMapStatus(msu);

        initLocation();
        return view;
    }
    private void initLocation() {
        locationMode= MyLocationConfiguration.LocationMode.NORMAL;
        mlocationClient=new LocationClient(context);
        mlistener1=new MylocationListener();
        mlocationClient.registerLocationListener(mlistener1);
        LocationClientOption mOption=new LocationClientOption();
        //设置精度
        mOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //设置坐标系
        mOption.setCoorType("bd09ll");
        mOption.setIsNeedAddress(true);
        mOption.disableCache(true);
        mOption.setOpenGps(true);
        //设置请求间隔
        int span=1000;
        mOption.setScanSpan(span);
        mlocationClient.setLocOption(mOption);
        //图标
        mIconLocation= BitmapDescriptorFactory
                .fromResource(R.drawable.location_marker);
        myOrientationListener=new MyOrientationListener(context);
        myOrientationListener.setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
            @Override
            public void onOrientationChanged(float x) {
                mCurrentX = x;
            }
        });
        if(!mlocationClient.isStarted())
        {
            mlocationClient.start();
        }
        myOrientationListener.start();


    }


    public class MylocationListener implements BDLocationListener
    {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (bdLocation.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                if (i > 4) {
                    mLatitude = bdLocation.getLatitude();
                    mLongitude = bdLocation.getLongitude();
                    MyLocationData data = new MyLocationData.Builder()
                            .direction(mCurrentX)//设定图标方向
                            .accuracy(bdLocation.getRadius())//getRadius 获取定位精度,默认值0.0f
                            .latitude(mLatitude)//百度纬度坐标
                            .longitude(mLongitude)//百度经度坐标
                            .build();
                    mBaiduMap.setMyLocationData(data);
                    //显示图标
                    MyLocationConfiguration configuration
                            = new MyLocationConfiguration(locationMode, true, mIconLocation);
                    mBaiduMap.setMyLocationConfigeration(configuration);
                    LatLng latLng = new LatLng(mLatitude, mLongitude);
                    MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
                    mBaiduMap.animateMapStatus(msu);
                    if (latLngPolygon.size() == 0) {
                        latLngPolygon.add(latLng);
                        Calendar c = Calendar.getInstance();
                        try {
                            d1 = df.parse(c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.DAY_OF_MONTH) + " " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else {
                        distance = distance + getDistance(latLngPolygon.get(latLngPolygon.size() - 1).latitude, latLngPolygon.get(latLngPolygon.size() - 1).longitude, mLatitude, mLongitude);
                        // distance=distance+ DistanceUtil.getDistance(latLngPolygon.get(latLngPolygon.size() - 1),latLng);
                        Calendar c = Calendar.getInstance();
                        try {
                            d2 = df.parse(c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.DAY_OF_MONTH) + " " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND));
                            long diff = d2.getTime() - d1.getTime();
                            minute = diff / 1000;
                            text1.setText(String.valueOf(minute) + "秒");
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        text.setText(String.valueOf(distance) + "米");
                        text2.setText(String.valueOf(distance / minute) + "米/秒");
                        latLngPolygon.add(latLng);
                        drawMyRoute(latLngPolygon);

                    }
                }
                else{i++;}
                mMapView.refreshDrawableState();
                mMapView.invalidate();
            }
            else{
                Toast.makeText(context, "请打开gps或到室外进行定位", Toast.LENGTH_LONG);
            }
        }

    }

    //停止定位
public void stopLocation(){
    mlocationClient.stop();
    myOrientationListener.stop();
}
    //划线
    protected void drawMyRoute(List<LatLng> points2) {
        OverlayOptions options = new PolylineOptions().color(0xAAFF0000)
                .width(5).points(points2);
        mBaiduMap.addOverlay(options);
    }

    //获得两点距离
    public float getDistance(double lat1, double lon1, double lat2, double lon2) {
        float[] results=new float[1];
        Location.distanceBetween(lat1, lon1, lat2, lon2, results);
        return results[0];

    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
