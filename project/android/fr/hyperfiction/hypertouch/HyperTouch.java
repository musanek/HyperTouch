package fr.hyperfiction.hypertouch;

import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;
import org.haxe.lime.GameActivity;

/**
 * ...
 * @author shoe[box]
 */

public class HyperTouch implements View.OnTouchListener{

	public static GLSurfaceView mSurface;

	private ArrayList<View.OnTouchListener> gestures;

	private static String TAG = "HyperTouch";

	// -------o constructor
		
		/**
		* constructor
		*
		* @param	
		* @return	void
		*/
		public HyperTouch() {
			trace("constructor");
			gestures = new ArrayList<View.OnTouchListener>( );
			enable( );
		}
	
	// -------o public
				
		/**
		* <code>SimpleOnGestureListener</code> onTouch Generic Method
		* 
		* @public
		* @return	void
		*/
		public boolean onTouch( View v , final MotionEvent ev) {
			
			boolean res = false;
			for( View.OnTouchListener g : gestures ){	
				res = g.onTouch( v , ev );
				if( res )
					break;
			}

			return false;
		}

		/**
		* 
		* 
		* @public
		* @return	void
		*/
		public void add_gesture( View.OnTouchListener g ) {
			trace("add_gesture ::: "+g);
			gestures.add( g );

			// Collections.sort(gestures, new Comparator<Object>(){
			//      public int compare(Object o1, Object o2){
			//          if(o1.prio == o2.prio)
			//              return 0;
			//          return o1.prio < o2.prio ? -1 : 1;
			//      }
			// });
		}

		/**
		* 
		* 
		* @public
		* @return	void
		*/
		public void enable( ){
			trace("enable ::: ");
			trace("GameActivity ::: "+GameActivity.getInstance( ));
			trace("focus ::: "+GameActivity.getInstance( ).getCurrentFocus( ));
			mSurface = ( GLSurfaceView ) GameActivity.getInstance( ).getCurrentFocus( );	
			GameActivity.getInstance( ).getCurrentFocus( ).setOnTouchListener( this );
		}

		/**
		* 
		* 
		* @public
		* @return	void
		*/
		public void disable( ){
			trace("disable");
		}

	// -------o protected
	

	// -------o misc
		
		/**
		* 
		* 
		* @public
		* @return	void
		*/
		static public void trace( String s ){
			Log.i( TAG , s );
		}

		/**
		* 
		* 
		* @public
		* @return	void
		*/
		public static HyperTouch getInstance( ){
			trace("getInstance");
			if( __instance == null )
				__instance = new HyperTouch( );

			return __instance;
		}

		private static HyperTouch __instance = null;
}