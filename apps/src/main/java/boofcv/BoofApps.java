package boofcv;

import boofcv.app.ApplicationLauncherGui;
import boofcv.app.Help;

/**
 * @author Peter Abeles
 */
public class BoofApps {
    public static void main(String[] args) {
        if( args.length == 0 ) {
            ApplicationLauncherGui.main(args);
        } else {
            Help.main(args);
        }
    }
}
