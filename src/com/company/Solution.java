package com.company;

import javax.swing.*;
import java.awt.*;

public class Solution extends JPanel
{

    static int[][] tower; // the three towers' disks as stack
    static int[] top;//top of the three stacks
    static int from, to;//moving 'from' tower number 'to' tower number
    static int diskInAir;//number of disk moved (1 to n)
    static int n, l, b, u;
    static Color[] colors = {Color.BLUE, Color.CYAN, Color.GREEN, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED,
            Color.YELLOW,Color.GREEN};

    public Solution() {
        tower = new int[3][n];
        top = new int[3];
    }

    static void push(int to, int diskNo) {
        // Putting the disks on the tower
        tower[to-1][++top[to-1]] = diskNo;
    }

    static int pop(int from) {
        // Take the topmost disk from the tower
        return(tower[from-1][top[from-1]--]);
    }

    Color getColor(int diskNo) {
        return colors[diskNo % 8];
    }

    void drawStill(Graphics g) {
        int j, i, disk;
        g.clearRect(0, 0, getWidth(), getHeight());
        for(j = 1; j <= 3; j++) {
            // Draw tower
            g.setColor(Color.GRAY);
            g.fillRoundRect(j*l, u, 5, b-u, 1,1);

            // Draw all the disks on the tower
            for (i=0; i <= top[j-1]; i++) {
                disk = tower[j-1][i];
                g.setColor(getColor(disk));
                g.fillRect(j * l - 15 - disk * 5,b - (i+1) * 10, 35 + disk * 10,10);
            }
        }
    }

    void drawFrame(Graphics g,int x,int y) {
        try {
            drawStill(g);
            g.setColor(getColor(diskInAir));
            g.fillRect(x - 15 - diskInAir * 5, y - 10, 35 + diskInAir * 10,10);
            Thread.sleep(60);
        } catch(InterruptedException ignored) { }
    }

    /** Function to animate disks moving from one tower to another**/
    void animator(Graphics g) {
        int x, y, dif, sign;
        diskInAir = pop(from);
        x = from * l;
        y = b - (top[from-1] +1) * 10;

        // Moving a disk upwards from the tower
        for ( ; y > u - 20; y -= 8)
            drawFrame(g, x, y);

        y = u - 20;
        dif = to * l - x;
        sign = dif / Math.abs(dif);

        // Moving a disk towards a target tower
        for( ; Math.abs(x-to*l) >= 24; x += sign * 12)
            drawFrame(g, x, y);
        x=to*l;

        // Placing disk (downwards) on a target tower
        for( ; y < b-(top[to-1]+1) * 10; y += 8)
            drawFrame(g, x, y);

        push(to,diskInAir);
        drawStill(g);
    }

    /** Move a certain number of disks form the top to a target tower
     * @param n Number of disks
     * @param a Origin Tower
     * @param c Target tower
     * @param b Tower used for swapping
     * **/
    void moveTopN(Graphics g, int n, int a, int b, int c) throws InterruptedException {
        if (n >= 1) {
            moveTopN(g, n - 1, a, c, b);
            drawStill(g);
            Thread.sleep(700);

            from = a;
            to = c;

            // Animating the move
            animator(g);
            moveTopN(g, n-1, b, a, c);
        }
    }

    public static void solve() throws InterruptedException {
        int i;
        String s = JOptionPane.showInputDialog("Enter number of disks");
        n = Integer.parseInt(s);
        Solution solution = new Solution();

        // Setting all tower empty
        for(i = 0; i < 3; i++)
            top[i] = -1;

        // Putting all disks on first tower
        for(i = n ; i > 0; i--) {
            push(1, i);
        }

        JFrame fr = new JFrame();
        fr.setLayout(new BorderLayout());
        fr.setSize(640, 360);
        fr.add(solution);
        solution.setSize(fr.getSize());
        fr.setVisible(true);

        l = solution.getWidth() / 4;
        b = solution.getHeight() - 50;
        u = b - n * 12;


        solution.moveTopN(solution.getGraphics(), n, 1, 2, 3);
    }
}

