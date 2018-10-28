package boofcv;

import boofcv.alg.misc.ImageStatistics;
import boofcv.factory.filter.kernel.FactoryKernelGaussian;
import boofcv.gui.image.ShowImages;
import boofcv.struct.convolve.Kernel1D_F32;
import boofcv.struct.image.GrayF32;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.TimerTask;

/**
 * Dynamically rendered BoofCV Logo
 *
 * @author Peter Abeles
 */
public class BoofLogo extends JPanel {

    public static final String NAME = "BOOFCV";
    public static final int MAX_RADIUS = 30;

    int radius = MAX_RADIUS;

    public BoofLogo() {
        setBackground(Color.WHITE);

        setPreferredSize(new Dimension(620,300));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        int order = 1;
        double sigma = FactoryKernelGaussian.sigmaForRadius(radius,order);

        Kernel1D_F32 kerX =  FactoryKernelGaussian.derivativeK(Kernel1D_F32.class,order,sigma,2*radius);
        Kernel1D_F32 kerY = FactoryKernelGaussian.derivativeK(Kernel1D_F32.class,order,sigma,radius);
        GrayF32 kernel = new GrayF32(kerX.width,kerY.width);

        for( int i = 0, index = 0; i < kernel.height; i++ ) {
            for( int j = 0; j < kernel.width; j++ ) {
                kernel.data[ index++ ] = kerY.data[i] * kerX.data[j];
            }
        }

        float max = ImageStatistics.maxAbs(kernel);

        BufferedImage image = new BufferedImage(kernel.width,kernel.height,BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < kernel.height; y++) {
            for (int x = 0; x < kernel.width; x++) {

                float v = kernel.get(x,y);

                int rgb;
                if( v > 0 ) {
                    int inv255 = (int)(200*(1-v/max))+55;
                    rgb = 255 << 16 | inv255 << 8 | inv255;
                } else {
                    int inv255 = (int)(200*(1+v/max))+55;
                    rgb = inv255 << 16 | inv255 << 8 | inv255;
                }
                image.setRGB(x,y,rgb);
            }
        }

        double w = Math.min(getWidth(),getHeight());
        double s = w/(double)kernel.height;
        // if tx not aligned to integer there is a rendering bug in linux
        double tx = (int)((getWidth()-s*kernel.width)/2);
        AffineTransform adjustment = new AffineTransform(s,0,0,s,tx,0);
        g2.drawImage(image,adjustment,null);

        Font font = new Font("Serif", Font.BOLD, (int)(w*0.4));
        FontMetrics metrics = g2.getFontMetrics(font);
        Rectangle2D r = metrics.getStringBounds(NAME,null);
        tx = getWidth()/2 - r.getCenterX();
        double ty = (int)w/2 - r.getCenterY();

        g2.setFont(font);
        g2.setColor(Color.BLACK);
        g2.drawString(NAME,(float)tx,(float)ty);
    }

    public void animate( long period ) {
        radius = 1;
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                radius += 1;
                repaint();
                if( radius >= MAX_RADIUS ) {
                    timer.cancel();
                }
            }
        }, 0, period/MAX_RADIUS);
    }

    public static void main(String[] args) {
        BoofLogo logo = new BoofLogo();
        logo.animate(2000);
        ShowImages.showWindow(logo,"Logo",true);
    }
}
