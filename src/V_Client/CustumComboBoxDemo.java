package V_Client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class CustumComboBoxDemo extends JPanel {
    ImageIcon[] images;
    String[] petStrings = {"En ligne", "Absent", "Occupe"};

    /*
     * Despite its use of EmptyBorder, this panel makes a fine content
     * pane because the empty border just increases the panel's size
     * and is "painted" on top of the panel's normal background.  In
     * other words, the JPanel fills its entire background if it's
     * opaque (which it is by default); adding a border doesn't change
     * that.
     */
    public CustumComboBoxDemo() {
        super(new BorderLayout());

        //Load the pet images and create an array of indexes.
        images = new ImageIcon[petStrings.length];
        Integer[] intArray = new Integer[petStrings.length];
        for (int i = 0; i < petStrings.length; i++) {
            intArray[i] = new Integer(i);
            images[i] = createImageIcon("/Images/"+petStrings[i]+".png");
            if (images[i] != null) {
                images[i].setDescription(petStrings[i]);
            }
        }

        //Create the combo box.
        final JComboBox petList = new JComboBox(intArray);
        petList.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent arg0) {
        		InterfaceClient.sta=petList.getSelectedIndex();
        		InterfaceClient.client.setAdrIPDestination("Tout");
        		if(InterfaceClient.sta==0)
        		InterfaceClient.client.setMsgE1(" Change statut en ligne ");
        		else if(InterfaceClient.sta==1)
            		InterfaceClient.client.setMsgE1(" Change statut en absent ");
        		else
        			InterfaceClient.client.setMsgE1(" Change statut en occupé ");
        		InterfaceClient.client.setTypeMsgE("st"+InterfaceClient.sta);InterfaceClient.client.envoieMessage();
        	}
        });
        petList.setSelectedIndex(0);
        petList.setFont(new Font("Times New Roman", Font.PLAIN, 10));
        ComboBoxRenderer renderer= new ComboBoxRenderer();
        renderer.setPreferredSize(new Dimension(70, 35));
        petList.setRenderer(renderer);
        petList.setMaximumRowCount(3);

        //Lay out the demo.
       add(petList, BorderLayout.PAGE_START);
       //setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = CustumComboBoxDemo.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
                return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        JFrame frame = new JFrame("CustomComboBoxDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new CustumComboBoxDemo();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    class ComboBoxRenderer extends JLabel
                           implements ListCellRenderer {
        private Font uhOhFont;

        public ComboBoxRenderer() {
            setOpaque(true);
            setHorizontalAlignment(CENTER);
            setVerticalAlignment(CENTER);
        }

        /*
         * This method finds the image and text corresponding
         * to the selected value and returns the label, set up
         * to display the text and image.
         */
        public Component getListCellRendererComponent(
                                           JList list,
                                           Object value,
                                           int index,
                                           boolean isSelected,
                                           boolean cellHasFocus) {
            //Get the selected index. (The index param isn't
            //always valid, so just use the value.)
            int selectedIndex = ((Integer)value).intValue();

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            //Set the icon and text.  If icon was null, say so.
            ImageIcon icon = images[selectedIndex];
            String pet = petStrings[selectedIndex];
            setIcon(icon);
            if (icon != null) {
                setText(pet);
                setFont(list.getFont());
            } else {
                setUhOhText(pet + " (no image available)",
                            list.getFont());
            }

            return this;
        }

        //Set the font and text when no image was found.
        protected void setUhOhText(String uhOhText, Font normalFont) {
            if (uhOhFont == null) { //lazily create this font
                uhOhFont = normalFont.deriveFont(Font.ITALIC);
            }
            setFont(uhOhFont);
            setText(uhOhText);
        }
    }

	
}