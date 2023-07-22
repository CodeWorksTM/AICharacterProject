package kro.kr.rhya_network.aicharacter_project.layout.activity;

import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;

import androidx.appcompat.app.AppCompatActivity;

import com.live2d.sdk.cubism.framework.motion.CubismMotionManager;

import kro.kr.rhya_network.aicharacter_project.R;
import kro.kr.rhya_network.aicharacter_project.core.GLRenderer;
import kro.kr.rhya_network.aicharacter_project.core.LAppDelegate;

public class L2DViewActivity extends AppCompatActivity {
    private GLSurfaceView glSurfaceView;
    private GLRenderer glRenderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        glSurfaceView = new GLSurfaceView(this);
        glSurfaceView.setEGLContextClientVersion(2);       // OpenGL ES 2.0を利用

        glRenderer = new GLRenderer();

        glSurfaceView.setRenderer(glRenderer);
        glSurfaceView.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        setContentView(glSurfaceView);

        /**
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT
                            ? View.SYSTEM_UI_FLAG_LOW_PROFILE
                            : View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            );
        } else {
            getWindow().getInsetsController().hide(WindowInsets.Type.navigationBars() | WindowInsets.Type.statusBars());
            getWindow().getInsetsController().setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        }
        */
    }


    @Override
    protected void onStart() {
        super.onStart();

        LAppDelegate.getInstance().onStart(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        glSurfaceView.onResume();

        /**
        View decor = this.getWindow().getDecorView();
        decor.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
        */
    }

    @Override
    protected void onPause() {
        super.onPause();

        glSurfaceView.onPause();
        LAppDelegate.getInstance().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        LAppDelegate.getInstance().onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        LAppDelegate.getInstance().onDestroy();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LAppDelegate.getInstance().onTouchBegan(pointX, pointY);
                break;
            case MotionEvent.ACTION_UP:
                LAppDelegate.getInstance().onTouchEnd(pointX, pointY);
                break;
            case MotionEvent.ACTION_MOVE:
                LAppDelegate.getInstance().onTouchMoved(pointX, pointY);
                break;
        }
        return super.onTouchEvent(event);
    }
}
