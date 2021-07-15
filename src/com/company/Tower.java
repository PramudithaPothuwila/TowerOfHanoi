package com.company;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Tower extends JPanel implements MouseListener, MouseMotionListener {

    public static final long serialVersionUID = 0xff;

    private final Stack<Rectangle2D.Double>[] s = new Stack[3];
    private final Stack<Color>[] disk_color = new Stack[3];
    private static Rectangle2D.Double top = null;
    private Color top_color = null;
    private double ax, ay, w, h;
    private boolean draggable = false;

    public Tower(int n) {
        boolean firstTime = true;
        init(n);
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    public void init(int val){
        Color[] c = {Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW, Color.GREEN,Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW, Color.GREEN};


        s[0] = new Stack<Rectangle2D.Double>();
        s[1] = new Stack<Rectangle2D.Double>();
        s[2] = new Stack<Rectangle2D.Double>();

        disk_color[0] = new Stack<Color>();
        disk_color[1] = new Stack<Color>();
        disk_color[2] = new Stack<Color>();

        for (int i = 0; i<val; i++) {
            Rectangle2D.Double r = new Rectangle2D.Double();

            double x = getWidth() / 6.0 ;
            x = (x == 0) ? 109 : x;
            double wr = val * 25-20 * i;
            r.setFrame(x-wr / 2,190 - i * 20,wr,20);
            s[0].push(r);
            disk_color[0].push(c[i]);
        }

        top=null;
        top_color=null;
        ax=0.0; ay=0.0;  w=0.0;  h=0.0;
        draggable=false;
        repaint();
    }

    public void mouseClicked(MouseEvent ev){}

    public void mousePressed(MouseEvent ev){
        Point pos = ev.getPoint();
        int n = current_tower(pos);
        if (!s[n].empty()) {
            top = s[n].peek();
            if (top.contains(pos)) {
                top = s[n].pop();
                top_color = disk_color[n].pop();
                ax = top.getX();
                ay = top.getY();
                w = pos.getX() - ax;
                h = pos.getY() - ay;
                draggable=true;  // Allowing dragging if current mouse position is in top disk
            } else{
                top = null;
                top_color = Color.black;
                draggable = false;
            }
        }
    }

    public void mouseReleased(MouseEvent ev) {
        if (top!=null && draggable) {
            int tower = current_tower(ev.getPoint());
            double x, y;
            if (!s[tower].empty()) {
                if (s[tower].peek().getWidth() > top.getWidth()) {
                    y = s[tower].peek().getY() - 20;  // Calculating ay for dragged disk for placement
                } else {
                    JOptionPane.showMessageDialog(this, "Wrong Move", "Tower Of Hanoi", JOptionPane.ERROR_MESSAGE);
                    tower = current_tower(new Point((int)ax,(int)ay));
                    if (!s[tower].empty()) {
                        y = s[tower].peek().getY() - 20;
                    } else {
                        y = getHeight() - 40;
                    }//return; //cannot put bigger disk on a smaller one
                }
            } else y = getHeight() - 40;  // if no previous disk in tower

            x = (int) (getWidth() / 6 + (getWidth() / 3) * tower - top.getWidth() / 2);
            top.setFrame(x, y, top.getWidth(), top.getHeight());
            s[tower].push(top);
            disk_color[tower].push(top_color);

            top = null;
            top_color = Color.black;
            draggable = false;
            repaint();
        }
    }

    public void mouseEntered(MouseEvent ev){}

    public void mouseExited(MouseEvent ev){}

    public void mouseMoved(MouseEvent ev){}

    public void mouseDragged(MouseEvent ev){
        int cx = ev.getX();   // Getting current mouse position
        int cy = ev.getY();
        if (top!=null && draggable) {
            top.setFrame(cx-w,cy-h,top.getWidth(),top.getHeight());
            repaint();  // Repainting if dragging a disk
        }
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        int holder_x = getWidth() / 6;
        int holder_y1 = getHeight() - 10 * 20;
        int holder_y2 = getHeight() - 20;

        g2d.setColor(Color.gray);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(holder_x, holder_y1, holder_x, holder_y2);
        g2d.drawLine(3 * holder_x, holder_y1,3 * holder_x, holder_y2);
        g2d.drawLine(5 * holder_x, holder_y1,5 * holder_x, holder_y2);
        g2d.drawLine(0, holder_y2, getWidth(), holder_y2);

        g2d.setStroke(new BasicStroke(1));

        g2d.setColor(top_color);

        if(draggable && top!=null) {
            g2d.fill(top);  // Drawing dragged disk
        }

        // Drawing disks of each tower
        drawTower(g2d,0);
        drawTower(g2d,1);
        drawTower(g2d,2);
    }

    private void drawTower(Graphics2D g2d, int n) {
        if (!s[n].empty()) {
            for (int i = 0; i < s[n].size(); i++) {
                g2d.setColor(disk_color[n].get(i));
                g2d.fill(s[n].get(i));
            }
        }
    }

    /** Return current tower position with respect to Point p */
    private int current_tower(Point p) {
        Rectangle2D.Double
                rA=new Rectangle2D.Double(),
                rB=new Rectangle2D.Double(),
                rC=new Rectangle2D.Double();

        rA.setFrame(0, 0, getWidth() / 3.0 ,getHeight());
        rB.setFrame(getWidth() / 3.0, 0, getWidth() / 3.0, getHeight());
        rC.setFrame(2 * getWidth() / 3.0, 0, getWidth() / 3.0, getHeight());

        if (rA.contains(p))
            return 0;
        else if (rB.contains(p))
            return 1;
        else if (rC.contains(p))
            return 2;
        else
            return -1;
    }
}

