import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ColorSwitcher extends JPanel implements ChangeListener {

    static JColorChooser chooser;
    static Jard jard;
    JLabel blockSizeLabel = new JLabel("Block size");
    JSpinner numSelect ;
    Integer numSelected = 40;
    Integer min = 2;
    Integer max = 400;
    Integer inc = 2;
    JButton gridLinesBtn;
    JButton Hey;
    JFrame personalGreeting;
    JPanel personalGreetingPanel;

    public static void connect(Jard jd){
        jard = jd;
    }

    ColorSwitcher() {
        this.setBackground(Color.LIGHT_GRAY);
        chooser=  new JColorChooser();
        chooser.getSelectionModel().addChangeListener(this);
        chooser.setPreviewPanel(new JPanel());
        SpinnerNumberModel model = new SpinnerNumberModel(numSelected,min,max,inc);
        numSelect = new JSpinner(model);

        numSelect.addChangeListener(e ->{
            int n = (int) model.getNumber();
           // System.out.println(n);
            jard.changeblockSize(n);
        });

        gridLinesBtn = new JButton("Grid");
        gridLinesBtn.addActionListener(e->{
            if(!jard.gridLines){
                jard.gridLines = true;
            }else{
                jard.gridLines = false;
            }
        });

        Hey = new JButton("Hey");
        personalGreetingPanel = new JPanel();
        personalGreeting = new JFrame();
        personalGreeting.setTitle("Happy chrismas");

        Hey.addActionListener(e->{

            personalGreeting.add(personalGreetingPanel);
            JOptionPane.showMessageDialog(personalGreeting,"Helllooo ");
           // jard.changeblockSize(40);

            //  jard.saveFile(jard.blks, "jardT.ser");
            jard.blks = jard.loadFile("jardT.ser");
        });




        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.add(chooser);
        this.add(blockSizeLabel);
        this.add(numSelect);
        this.add(gridLinesBtn);
        this.add(Box.createRigidArea(new Dimension(10,200)));
        this.add(Hey);

    }

    @Override
    public void stateChanged(ChangeEvent changeEvent) {
        //System.out.println(changeEvent);
        jard.setBlockColor = chooser.getColor();

    }
}
