package kro.kr.rhya_network.aicharacter_project.layout.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

import dev.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog;
import dev.shreyaspatil.MaterialDialog.interfaces.DialogInterface;
import kro.kr.rhya_network.aicharacter_project.R;

public class ACSplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ConstraintLayout loadingConstraintLayout = findViewById(R.id.loadingConstraintLayout);

        // UI Animation
        loadingConstraintLayout.animate()
                .setStartDelay(500)
                .alpha(1f)
                .translationY(-50f)
                .setDuration(1000)
                .withEndAction(() -> {
                })
                .start();
    }

    protected void showSignInDialog() {
        try {
            BottomSheetMaterialDialog mBottomSheetDialog = new BottomSheetMaterialDialog.Builder(this)
                    .setTitle("Login")
                    .setMessage("구글 계정을 통해 로그인을 진행해주세요.")
                    .setCancelable(false)
                    .setAnimation(R.raw.lottie_anim_login)
                    .setPositiveButton("Google 로그인", new BottomSheetMaterialDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {

                            dialogInterface.dismiss();
                        }
                    })
                    .setNegativeButton("앱 종료", new BottomSheetMaterialDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which) {
                            dialogInterface.dismiss();

                            // 로그인 취소
                            Toast.makeText(getApplicationContext(), "Login cancelled!", Toast.LENGTH_SHORT).show();
                            // 앱 종료
                            finishAffinity();
                        }
                    })
                    .build();

            LottieAnimationView animationView = mBottomSheetDialog.getAnimationView();
            animationView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            animationView.setRepeatMode(LottieDrawable.RESTART);

            // Show Dialog
            mBottomSheetDialog.show();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
