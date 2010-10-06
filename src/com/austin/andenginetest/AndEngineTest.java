package com.austin.andenginetest;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.engine.camera.SmoothCamera;
import org.anddev.andengine.engine.options.EngineOptions;
import org.anddev.andengine.engine.options.EngineOptions.ScreenOrientation;
import org.anddev.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.scene.background.ColorBackground;
import org.anddev.andengine.entity.scene.background.SpriteBackground;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.entity.util.FPSLogger;
import org.anddev.andengine.opengl.texture.Texture;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.opengl.texture.region.TextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;
import org.anddev.andengine.ui.activity.BaseGameActivity;

import android.util.DisplayMetrics;


public class AndEngineTest extends BaseGameActivity {
	//CONSTANTS
	private static int CAMERA_WIDTH = 800;
	private static int CAMERA_HEIGHT = 480;
	
	private static float DEMO_VELOCITY = 100.0f;
	
	//FIELDS
	private SmoothCamera mCamera;
	
	private Texture mTextureMap;
	private TextureRegion mBackgroundTextureRegion;
	private TextureRegion mTrayTextureRegion;
	
	private SpriteBackground mBackgroundImage;
	
	//Methods for/form superclass/interfaces
	@Override
	public Engine onLoadEngine(){		
		final DisplayMetrics displayMetrics = new DisplayMetrics();
		
		this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		CAMERA_WIDTH = displayMetrics.widthPixels;
		CAMERA_HEIGHT = displayMetrics.heightPixels;
		
		this.mCamera = new SmoothCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT, 10, 10, 1.0f);
		return new Engine(new EngineOptions(true, ScreenOrientation.LANDSCAPE, 
				new FillResolutionPolicy(), this.mCamera));
		
	}
	
	@Override
	public void onLoadResources(){
		
		this.mTextureMap = new Texture(1024, 1024, TextureOptions.BILINEAR);
		this.mBackgroundTextureRegion = TextureRegionFactory.createFromAsset(mTextureMap,
																			this,
																			"gfx/background.png",
																			2, 2);
		
		this.mTrayTextureRegion = TextureRegionFactory.createFromAsset(mTextureMap,
																		this,
																		"gfx/tray.png",
																		2, 484);

		final Sprite backgroundSprite = new Sprite(0, 0, mBackgroundTextureRegion);
		this.mBackgroundImage = new SpriteBackground(backgroundSprite);
		
		
		this.mEngine.getTextureManager().loadTexture(this.mTextureMap);
		
	}
	
	@Override
	public Scene onLoadScene(){
		this.mEngine.registerUpdateHandler(new FPSLogger());
		
		final Scene scene = new Scene(1);
		//scene.setBackground(new ColorBackground(0.09804f, 0.6274f, 0.8784f));
		
		
		scene.setBackground(mBackgroundImage);
		
		final Sprite traySprite = new Sprite((CAMERA_WIDTH - this.mTrayTextureRegion.getWidth() - 20),
									(CAMERA_HEIGHT - this.mTrayTextureRegion.getHeight()), this.mTrayTextureRegion);
		scene.getTopLayer().addEntity(traySprite);
		return scene;
	}
	
	@Override
	public void onLoadComplete(){
		
				
	}

	
}