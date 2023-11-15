// Generated by view binder compiler. Do not edit!
package com.example.proj2_and_2019202021.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.proj2_and_2019202021.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentImageBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final GridView gridView;

  private FragmentImageBinding(@NonNull FrameLayout rootView, @NonNull GridView gridView) {
    this.rootView = rootView;
    this.gridView = gridView;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentImageBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentImageBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_image, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentImageBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.gridView;
      GridView gridView = ViewBindings.findChildViewById(rootView, id);
      if (gridView == null) {
        break missingId;
      }

      return new FragmentImageBinding((FrameLayout) rootView, gridView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
