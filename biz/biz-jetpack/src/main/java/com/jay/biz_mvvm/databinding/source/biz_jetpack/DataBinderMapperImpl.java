//package com.jay.biz_jetpack.databinding.source.biz_jetpack;
//
//import android.util.SparseArray;
//import android.util.SparseIntArray;
//import android.view.View;
//
//import androidx.databinding.DataBinderMapper;
//import androidx.databinding.DataBindingComponent;
//import androidx.databinding.ViewDataBinding;
//
//import com.jay.biz_jetpack.databinding.source.biz_jetpack.databinding.BizJetpackActivityDataBindingBindingImpl;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class DataBinderMapperImpl extends DataBinderMapper {
//  private static final int LAYOUT_BIZJETPACKACTIVITYDATABINDING = 1;
//
//  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(1);
//
//  static {
//    INTERNAL_LAYOUT_ID_LOOKUP.put(com.jay.biz_jetpack.R.layout.biz_jetpack_activity_data_binding_use, LAYOUT_BIZJETPACKACTIVITYDATABINDING);
//  }
//
//  @Override
//  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
//    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
//    if(localizedLayoutId > 0) {
//      final Object tag = view.getTag();
//      if(tag == null) {
//        throw new RuntimeException("view must have a tag");
//      }
//      switch(localizedLayoutId) {
//        case  LAYOUT_BIZJETPACKACTIVITYDATABINDING: {
//          if ("layout/biz_jetpack_activity_data_binding_0".equals(tag)) {
//            return new BizJetpackActivityDataBindingBindingImpl(component, view);
//          }
//          throw new IllegalArgumentException("The tag for biz_jetpack_activity_data_binding is invalid. Received: " + tag);
//        }
//      }
//    }
//    return null;
//  }
//
//  @Override
//  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
//    if(views == null || views.length == 0) {
//      return null;
//    }
//    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
//    if(localizedLayoutId > 0) {
//      final Object tag = views[0].getTag();
//      if(tag == null) {
//        throw new RuntimeException("view must have a tag");
//      }
//      switch(localizedLayoutId) {
//      }
//    }
//    return null;
//  }
//
//  @Override
//  public int getLayoutId(String tag) {
//    if (tag == null) {
//      return 0;
//    }
//    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
//    return tmpVal == null ? 0 : tmpVal;
//  }
//
//  @Override
//  public String convertBrIdToString(int localId) {
//    String tmpVal = InnerBrLookup.sKeys.get(localId);
//    return tmpVal;
//  }
//
//  @Override
//  public List<DataBinderMapper> collectDependencies() {
//    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(2);
//    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
//    result.add(new com.chad.library.DataBinderMapperImpl());
//    return result;
//  }
//
//  private static class InnerBrLookup {
//    static final SparseArray<String> sKeys = new SparseArray<String>(2);
//
//    static {
//      sKeys.put(0, "_all");
//      sKeys.put(1, "user");
//    }
//  }
//
//  private static class InnerLayoutIdLookup {
//    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(1);
//
//    static {
//      sKeys.put("layout/biz_jetpack_activity_data_binding_0", com.jay.biz_jetpack.R.layout.biz_jetpack_activity_data_binding_use);
//    }
//  }
//}
