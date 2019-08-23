import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;



public class SerialGUI extends JFrame {

	private JPanel contentPane;
	private JLabel label;
	private int o=0;
	 Thread t;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SerialGUI frame = new SerialGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SerialGUI() {
		setTitle("RFID reader");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		label = new JLabel("Please scan your badge");
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(240, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(label, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(220, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		// start serial commmunication:
		initializeSerialPort();
	}
	
	// used for serial communication:
	String readline;
	SerialPort comPort;
	String commPort = "COM7";
	int baudrate = 9600;
	
	void initializeSerialPort() {
		System.out.println("Connecting to "+commPort+" with speed "+baudrate);
		comPort = SerialPort.getCommPort(commPort);
		comPort.openPort();
		comPort.setBaudRate(baudrate);
		comPort.addDataListener(new SerialPortDataListener() {
			@Override
			public int getListeningEvents() {
				return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
			}
			@Override
			public void serialEvent(SerialPortEvent event) {
				if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
					System.err.println("No data on SerialPort");
					return;
				}
				int bytesAvailable = comPort.bytesAvailable();
				if (bytesAvailable<1) {
					System.err.println("Can not read from SerialPort");
					return;
				}
				byte[] newData = new byte[bytesAvailable];
				int numRead = comPort.readBytes(newData, newData.length);
				 System.out.println("Read " + numRead + " bytes");
				if (numRead > 0) {
					o++;
					for (int i = 0; i < newData.length; ++i) {
						if (((char)newData[i]=='\n'||(char)newData[i]=='\r')) {
							readline=readline.trim();
							if (readline.length()>0) 
								{
								System.out.println("Entered recieve");
								receive(readline);
								
								
								}
							readline="";
							break;
						}
						else
							readline=readline+(char)newData[i];
					}
				}
			}
		});
	}
	
	/**
	 * Called by eventhandler when a line of text is received via the serial connection.
	 * @param line
	 */
	public void receive(String line) {
		if (line==null) return;
		System.out.println(line);
		if (line.startsWith("RFID TAG ID")) { // check for a value (string starting with 'Card UID')
			try {
				cuid(line.substring(10).trim());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
	/**
	 * This method will be called with the id if the SerialComm finds the keyword "Card UID"
	 * @param id
	 * @throws InterruptedException 
	 */
	public void cuid(String id) throws InterruptedException {
		System.out.println("cuid="+id+".");
		String welcome = "Welcome, ";
		
		String true_command = "python C:\\Users\\MUJ\\Desktop\\TechSys\\backend\\base.py puranjay 1";
		String false_command = "pythonC:\\Users\\MUJ\\Desktop\\TechSys\\backend\\base.py abc 0";
		if (id.equals("D:F3 50 D0 2D")) 
		{
			o++;
			System.out.println("***Code Begins***");
			try {
				
			    Process p = Runtime.getRuntime().exec(true_command);			
				BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line = "";
				p.waitFor();
				p.destroy();
				
				System.out.println("System back ");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			causedelay cause=new causedelay();
			   t = new Thread(cause);
			  t.start();
		}
		else {
			try {
			Process p = Runtime.getRuntime().exec(false_command);
			}
			catch(IOException e) {
				
			}
			
		}
		}
	public SerialGUI(int val)
	{
		o=val;
	}
	}
class causedelay implements Runnable
{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("setting o to 0");
		SerialGUI s=new SerialGUI(0);
		return;
		
	}
}