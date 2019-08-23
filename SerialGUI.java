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
	public static int o=0;
	 Thread t;
	 static final public int TIMEOUT_READ_BLOCKING = 0x00000010;

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
		
		label = new JLabel("Please scan your card");
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
	String commPort = "/dev/tty.usbmodem1421";
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
				//System.out.println("len of bytes avail:"+bytesAvailable);
				byte[] newData = new byte[bytesAvailable];
				int numRead=0;
				
				int temp=comPort.readBytes(newData, bytesAvailable);
				
					
					numRead = temp;
					
				
				System.out.println("Read " + numRead + " bytes");
				
				if (numRead > 0) {
					
					for (int i = 0; i < newData.length; ++i) {
						System.out.println((char)(newData[i]));
						if (((char)newData[i]=='\n'||(char)newData[i]=='\r')) {
							readline=readline.trim();
							
							if (readline.length()>0) 
								{
									System.out.println("value of o="+o);
									if(o==1 || o==0)
									{
										o++;
										receive(readline);
									}
								
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
		System.out.println(line+"value of o"+o);
		if (line.startsWith("aRFID TAG ID")) { // check for a value (string starting with 'Card UID')
			try {
				System.out.println("entered trimming");
				cuid(line.substring(10).trim());	
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // show it in the label in the userinterface
		}
	}
	
	/**
	 * Send text to the serial connection.
	 * @param line
	 */
	public void send(String s) {
		comPort.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
		comPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 100, 0);
		try{Thread.sleep(5);} catch(Exception e){}
		PrintWriter pout = new PrintWriter(comPort.getOutputStream());
		pout.print(s);
		pout.flush();
	}
	
	/**
	 * This method will be called with the id if the SerialComm finds the keyword "Card UID"
	 * @param id
	 * @throws InterruptedException 
	 */
	public void cuid(String id) throws InterruptedException {
		System.out.println("cuid="+id+".");
		String welcome = "Welcome, ";
		
		String command = "/Users/puranjay/Desktop/base.py";
		if (id.equals("ID:F2 7A 4E 43 >>")) 
		{
			o++;
			System.out.println("***Code Begins***");
			try {
				
				ProcessBuilder pb = new ProcessBuilder("/Library/Frameworks/Python.framework/Versions/3.5/bin/python3", command);
				Process p = pb.start();
				int exitCode = p.waitFor();
				System.out.println("exit code:"+exitCode);
				command="/Users/puranjay/Desktop/email1.py";
				ProcessBuilder pb1 = new ProcessBuilder("/Library/Frameworks/Python.framework/Versions/3.5/bin/python3", command);
				Process p1 = pb1.start();
				int exitCode1 = p1.waitFor();
				System.out.println("exit code:"+exitCode);
				int len;
				if ((len = p.getErrorStream().available()) > 0) {
				  byte[] buf = new byte[len]; 
				  p.getErrorStream().read(buf); 
				  System.err.println("Command error:\t\""+new String(buf)+"\""); 
				}
				BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line = "";
				causedelay delay=new causedelay();
				t=new Thread(delay);
				t.start();
				System.out.println("System back ");
			} catch (IOException e) {
				e.printStackTrace();
			}			
			
			
		}
		}
	public SerialGUI(int val)
	{
		o=0;
		System.out.println("o is set to zero");
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
			e.printStackTrace();
		}
		System.out.println("setting o to 0");
		SerialGUI s=new SerialGUI(0);
		return;		
	}	
}