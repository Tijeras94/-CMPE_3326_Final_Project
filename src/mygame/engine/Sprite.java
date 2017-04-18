/*
 * Team Members: Jaime Tijerina, Hector Gonzalez, Alyssa Cantu
 * Final Project(Spring 2017)
 * CSCI/CMPE 3326 
 */
package mygame.engine;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author jaime.tijerina01
 */
public class Sprite extends Rectangle implements ISprite, ICollision {

    public double rotation = 0;

    public double rotationOffset = 0;

    /* Create an ARGB BufferedImage */
    public BufferedImage img;

    private boolean debug_box = false;
    private float alpha = 1.0f;

    public void toggleDebug() {
        debug_box = !debug_box;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
        if (this.alpha <= 0.0f) {
            this.alpha = 0.0f;
        }
        if (this.alpha >= 1.0f) {
            this.alpha = 1.0f;
        }
    }

    public void setRotationOffset(double rotationOffset) {
        this.rotationOffset = rotationOffset;
    }

    public Sprite(String path) {

        try {
            img = ImageIO.read(new FileInputStream(path));

            width = img.getWidth();
            height = img.getHeight();
            this.setBounds(0, 0, width, height);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
        }
        rotation = 0;
    }

    public Sprite(BufferedImage texture) {
        img = texture;
        width = img.getWidth();
        height = img.getHeight();
        this.setBounds(0, 0, width, height);
        rotation = 0;
    }

    @Override
    public void draw(Graphics2D g) {
        AffineTransform backup = g.getTransform();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        AffineTransform trans = new AffineTransform();
        trans.rotate(Math.toRadians(rotation - rotationOffset), x, y); // the points to rotate around (the center in my example, your left side for your problem)
        g.transform(trans);

        g.drawImage(img, x - width / 2, y - height / 2, null);

        g.setTransform(backup); // restore previous transfor
        if (this.debug_box) {
            g.setColor(Color.green);
            g.drawRect(this.x - width / 2, this.y - height / 2, width, height);
        }

    }

    @Override
    public void update() {
    }

    @Override
    public void scale(int dWidth, int dHeight) {
        if (img != null) {
            BufferedImage scaledImage = new BufferedImage(dWidth, dHeight, img.getType());
            Graphics2D graphics2D = scaledImage.createGraphics();
            graphics2D.drawImage(img, 0, 0, dWidth, dHeight, null);
            graphics2D.dispose();
            img = scaledImage;
            width = img.getWidth();
            height = img.getHeight();
        }
    }

    @Override
    public void rotate(double angdeg) {
        this.rotation = angdeg;
    }

    public void destroy() {
        img.flush();
        img = null;
    }

    @Override
    public boolean outOfSigth(int x, int y, int width, int height) {
        return (this.x < x) || (this.x > width) || (this.y < y) || (this.y > height);
    }

    @Override
    public boolean outOfSigth(int width, int height) {
        return outOfSigth(0, 0, width, height);
    }

    public void walkWithinBox(int bx, int by, int bw, int bh) {
        if (this.x - (this.getWidth() / 2) <= bx) {
            this.x = bx + (int) this.getWidth() / 2;
        }

        if (this.y - (this.getHeight() / 2) <= by) {
            this.y = by + (int) this.getHeight() / 2;
        }

        if ((this.x - (this.getWidth() / 2)) >= bw - this.width) {
            this.x = bw - this.width + ((int) this.getWidth() / 2);
        }

        if ((this.y - (this.getHeight() / 2)) >= bh - this.height) {
            this.y = bh - this.height + ((int) this.getHeight() / 2);
        }
    }

    public boolean valueInRange(int value, int min, int max) {
        return (value >= min) && (value <= max);
    }

    public boolean rectOverlap(Sprite B) {
        boolean xOverlap = valueInRange(this.x, B.x, B.x + B.width)
                || valueInRange(B.x, this.x, this.x + this.width);

        boolean yOverlap = valueInRange(this.y, B.y, B.y + B.height)
                || valueInRange(B.y, this.y, this.y + this.height);

        return xOverlap && yOverlap;
    }
}
