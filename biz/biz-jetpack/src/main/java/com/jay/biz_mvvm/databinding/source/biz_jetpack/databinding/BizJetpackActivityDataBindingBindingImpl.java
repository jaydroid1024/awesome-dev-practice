//package com.jay.biz_jetpack.databinding.source.biz_jetpack.databinding;
//
//import android.view.View;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.jay.biz_jetpack.BR;
//import com.jay.biz_jetpack.databinding.BizJetpackActivityDataBindingBinding;
//
//@SuppressWarnings("unchecked")
//public class BizJetpackActivityDataBindingBindingImpl extends BizJetpackActivityDataBindingBinding  {
//
//    @Nullable
//    private static final IncludedLayouts sIncludes;
//    @Nullable
//    private static final android.util.SparseIntArray sViewsWithIds;
//    static {
//        sIncludes = null;
//        sViewsWithIds = null;
//    }
//    // views
//    @NonNull
//    private final android.widget.LinearLayout mboundView0;
//    @NonNull
//    private final android.widget.TextView mboundView1;
//    @NonNull
//    private final android.widget.TextView mboundView2;
//    // variables
//    // values
//    // listeners
//    // Inverse Binding Event Handlers
//
//    public BizJetpackActivityDataBindingBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
//        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
//    }
//    private BizJetpackActivityDataBindingBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
//        super(bindingComponent, root, 0
//            );
//        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
//        this.mboundView0.setTag(null);
//        this.mboundView1 = (android.widget.TextView) bindings[1];
//        this.mboundView1.setTag(null);
//        this.mboundView2 = (android.widget.TextView) bindings[2];
//        this.mboundView2.setTag(null);
//        setRootTag(root);
//        // listeners
//        invalidateAll();
//    }
//
//    @Override
//    public void invalidateAll() {
//        synchronized(this) {
//                mDirtyFlags = 0x2L;
//        }
//        requestRebind();
//    }
//
//    @Override
//    public boolean hasPendingBindings() {
//        synchronized(this) {
//            if (mDirtyFlags != 0) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    @Override
//    public boolean setVariable(int variableId, @Nullable Object variable)  {
//        boolean variableSet = true;
//        if (BR.user == variableId) {
//            setUser((com.jay.biz_jetpack.databinding.data.User) variable);
//        }
//        else {
//            variableSet = false;
//        }
//            return variableSet;
//    }
//
//    public void setUser(@Nullable com.jay.biz_jetpack.databinding.data.User User) {
//        this.mUser = User;
//        synchronized(this) {
//            mDirtyFlags |= 0x1L;
//        }
//        notifyPropertyChanged(BR.user);
//        super.requestRebind();
//    }
//
//    @Override
//    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
//        switch (localFieldId) {
//        }
//        return false;
//    }
//
//    @Override
//    protected void executeBindings() {
//        long dirtyFlags = 0;
//        synchronized(this) {
//            dirtyFlags = mDirtyFlags;
//            mDirtyFlags = 0;
//        }
//        String userFirstName = null;
//        com.jay.biz_jetpack.databinding.data.User user = mUser;
//        String userLastName = null;
//
//        if ((dirtyFlags & 0x3L) != 0) {
//
//
//
//                if (user != null) {
//                    // read user.firstName
//                    userFirstName = user.getFirstName();
//                    // read user.lastName
//                    userLastName = user.getLastName();
//                }
//        }
//        // batch finished
//        if ((dirtyFlags & 0x3L) != 0) {
//            // api target 1
//
//            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView1, userFirstName);
//            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView2, userLastName);
//        }
//    }
//    // Listener Stub Implementations
//    // callback impls
//    // dirty flag
//    private  long mDirtyFlags = 0xffffffffffffffffL;
//    /* flag mapping
//        flag 0 (0x1L): user
//        flag 1 (0x2L): null
//    flag mapping end*/
//    //end
//}