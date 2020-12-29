import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Arrays;
import java.util.stream.Stream;

public  class Jard extends JPanel implements MouseListener , ActionListener {

    final   int SCREEN_WIDTH = 800;
    final  int SCREEN_HEIGHT = 800;
    public boolean gridLines = true;
    int blockSize = 40;
    public Color setBlockColor = Color.LIGHT_GRAY;
    // workds because SCALE_F * blocksize = Screen_width && Screen_Height
    Timer timer;
    int DELAY = 100;
    public  block[][] blks = new block[SCREEN_WIDTH/ blockSize][SCREEN_HEIGHT/ blockSize];

    public  void setBlockColor(Color col){
        setBlockColor = col;
    }

    public void changeblockSize(int n){
        n = Math.abs(n);
        if(n%2 != 0){
            n+=1;
        }
        blockSize = n;
        blks = new block[SCREEN_WIDTH/ blockSize][SCREEN_HEIGHT/ blockSize];
        populateGrid();
        //repaint();
    }


    public void populateGrid(){
        for (int i = 0; i < SCREEN_HEIGHT/ blockSize; i+= 1) {
            for (int j = 0; j < SCREEN_WIDTH/ blockSize; j+=1) {
                blks[i][j] = new block(i* blockSize,j* blockSize,Color.LIGHT_GRAY,1);
            }
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    public void draw(Graphics g){

        for (block blk[]: blks ) {
            for (block bk :blk) {

                g.setColor(bk.getCol());
                g.fillRect(bk.getX(),bk.getY(), blockSize, blockSize);
            }
        }
        if(gridLines){
            for (int i = 0; i < SCREEN_WIDTH/blockSize; i++) {
                //xyxy
                g.setColor(Color.BLACK);
                g.drawLine(i*blockSize,0,i*blockSize,SCREEN_HEIGHT); //vert lines
                g.drawLine(0,i*blockSize,SCREEN_WIDTH,i*blockSize); //hoz lines

            }
        }


    }


    Jard(){
        changeblockSize(blockSize);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.addMouseListener(this);
        ColorSwitcher.connect(this);
        start();

    }
    public void start(){
        timer = new Timer(DELAY, this); //becos we exteded ActionListner
        timer.start();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
       // blks = new block[SCREEN_WIDTH/setblockSize][SCREEN_HEIGHT/setblockSize];

        repaint();
      //  System.out.println("event");

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }
    public void prirntArr(){
        for (block[] blokArr: blks) {
            System.out.println(Arrays.toString(blokArr));
        }
    }
    public static void saveFile(block[][] arr, String fname){
        try{
            FileOutputStream fos = new FileOutputStream(fname);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(arr);
            oos.flush();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public block[][] loadFile(String fname){
        block[][] res = null;
        try{
            //FileInputStream streamIN = new FileInputStream(fname);
           // FileInputStream is = (FileInputStream) this.getClass().getResourceAsStream(fname);
            InputStream is = Jard.class.getResourceAsStream("jardT.ser");
             ObjectInputStream objIn = new ObjectInputStream(is);

            res = (block[][]) objIn.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
       // System.out.println("oof");
        for (int i = 0; i < SCREEN_WIDTH/ blockSize; i++) {
            for (int j = 0; j <SCREEN_HEIGHT/ blockSize; j++) {
                // blks[i][j] = new block(i*SCALE_F,j*SCALE_F,Color.BLUE,1);
                if(  ( mouseEvent.getX() >= blks[i][j].getX() ) && (mouseEvent.getX() <=  blks[i][j].getX() + blockSize)  &&
                        (  ( mouseEvent.getY() >= blks[i][j].getY() ) && (mouseEvent.getY() <=  (blks[i][j].getY() + blockSize) )  )){
                    //System.out.println(blks[i][j].getX() + " " +  mouseEvent.getX()  + " " + blks[i][j].getY() + " " + mouseEvent.getY() );
                    //  blks[i][j].setCol(new Color(23,86,155));
                    blks[i][j].setCol(setBlockColor);
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


}
/*
* for the grid
* i = 0, j=0
* i<Sceen with / block size
* j<ScreenHeight / blocksize
*
* */