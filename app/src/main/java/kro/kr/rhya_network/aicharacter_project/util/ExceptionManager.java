package kro.kr.rhya_network.aicharacter_project.util;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import dev.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog;
import dev.shreyaspatil.MaterialDialog.interfaces.DialogInterface;
import kro.kr.rhya_network.aicharacter_project.R;

public class ExceptionManager {
    private final Activity activity;

    public interface ExceptionCallback {
        void callback();
    }

    public ExceptionManager(Activity activity) {
        this.activity = activity;
    }

    public String getErrorMessage(Exception ex) {
        return String.format("오류 발생! (%s)", ex.getMessage());
    }

    public void showErrorMessageDialog(String message) {
        showErrorMessageDialog(message, "확인", () -> {
            // Do Nothing
        });
    }

    public void showErrorMessageDialog(String message, String buttonText, ExceptionCallback callback) {
        try {
            BottomSheetMaterialDialog mBottomSheetDialog = new BottomSheetMaterialDialog.Builder(activity)
                    .setTitle("오류 발생!")
                    .setMessage(message)
                    .setCancelable(false)
                    .setAnimation(R.raw.lottie_anim_error)
                    .setPositiveButton(buttonText, new BottomSheetMaterialDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            try {
                                callback.callback();
                            }catch (Exception ignored) {}

                            dialogInterface.dismiss();
                        }
                    })
                    .setNegativeButton("앱 종료", new BottomSheetMaterialDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            dialogInterface.dismiss();

                            // 앱 종료
                            activity.finishAffinity();
                        }
                    })
                    .build();

            LottieAnimationView animationView = mBottomSheetDialog.getAnimationView();
            animationView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            animationView.setRepeatCount(1);

            // Show Dialog
            mBottomSheetDialog.show();
        }catch (Exception e) {
            // 예외 처리
            Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
