import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Desktop;

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import javax.swing.JTextArea;
import javax.swing.JProgressBar;

public class PIMCalcGUI {

	private JFrame frame;
	JTextArea textArea = new JTextArea();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PIMCalcGUI window = new PIMCalcGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PIMCalcGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Gary's PIM Calculator");
		frame.setBounds(100, 100, 733, 411);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Calculate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PIMCalc.calculateHits();
			}
		});
		btnNewButton.setBounds(15, 31, 239, 29);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Open Hits File");
		File file1 = new File("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\whereTrans.txt");
		File file2 = new File("C:\\Users\\Gary.Jiang\\Desktop\\PIM Calc\\whereAllTrans.txt");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Desktop.getDesktop().open(file1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(15, 91, 244, 29);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Open Hits_5G File");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop.getDesktop().open(file2);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(15, 152, 250, 29);
		frame.getContentPane().add(btnNewButton_2);
		
		textArea.setBounds(378, 156, 250, 37);
		frame.getContentPane().add(textArea);
		
		JButton button = new JButton("Clear");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
			}
		});
		button.setBounds(15, 225, 250, 29);
		frame.getContentPane().add(button);
		
		PrintStream aPrintStream = new PrintStream(new FilteredStream(new ByteArrayOutputStream()));
		System.setOut(aPrintStream);
		
//		RedirectedFrame outputFrame =
//			     new RedirectedFrame(false, false, null, 700, 600, JFrame.DO_NOTHING_ON_CLOSE);
//			outputFrame.setVisible(true);
		
	}
	
	class FilteredStream extends FilterOutputStream {
        public FilteredStream(OutputStream aStream) {
            super(aStream);
          }

        public void write(byte b[]) throws IOException {
            String aString = new String(b);
            textArea.append(aString);
        }

        public void write(byte b[], int off, int len) throws IOException {
            String aString = new String(b , off , len);
            textArea.append(aString);
        }
    }
}
