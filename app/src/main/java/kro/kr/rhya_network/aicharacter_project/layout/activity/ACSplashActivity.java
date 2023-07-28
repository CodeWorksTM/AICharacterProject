package kro.kr.rhya_network.aicharacter_project.layout.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;

import dev.shreyaspatil.MaterialDialog.BottomSheetMaterialDialog;
import dev.shreyaspatil.MaterialDialog.interfaces.DialogInterface;
import kro.kr.rhya_network.aicharacter_project.R;
import kro.kr.rhya_network.aicharacter_project.util.ExceptionManager;

public class ACSplashActivity extends AppCompatActivity {
    // 오류 관리자
    private ExceptionManager exceptionManager;
    // Google firebase oauth 인증 객체
    private FirebaseAuth mFirebaseAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Exception Manager Init
        exceptionManager = new ExceptionManager(this);

        // Loading UI Object
        ConstraintLayout loadingConstraintLayout = findViewById(R.id.loadingConstraintLayout);

        // UI Animation
        loadingConstraintLayout.animate()
                .setStartDelay(500)
                .alpha(1f)
                .translationY(-50f)
                .setDuration(1000)
                .withEndAction(this::showSignInDialog)
                .start();

        // 인증 객체 선언
        mFirebaseAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions);
    }

    protected void checkIsLoginUser() {
        try {

        }catch (Exception e) {
            exceptionManager.showErrorMessageDialog(exceptionManager.getErrorMessage(e));
        }
        // 기존에 로그인 했던 계정을 확인한다.
        GoogleSignInAccount gsa = GoogleSignIn.getLastSignedInAccount(ACSplashActivity.this);

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
                        }
                    })
                    .setNegativeButton("앱 종료", (dialogInterface, which) -> {
                        dialogInterface.dismiss();

                        // 로그인 취소
                        Toast.makeText(getApplicationContext(), "Login cancelled!", Toast.LENGTH_SHORT).show();
                        // 앱 종료
                        finishAffinity();
                    })
                    .build();

            LottieAnimationView animationView = mBottomSheetDialog.getAnimationView();
            animationView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            animationView.setRepeatMode(LottieDrawable.RESTART);

            // Show Dialog
            mBottomSheetDialog.show();
        }catch (Exception e) {
            exceptionManager.showErrorMessageDialog(exceptionManager.getErrorMessage(e));
        }
    }
}
